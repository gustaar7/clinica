$myPid = $PID
$exclude = @(4, $myPid)
$pids = Get-NetTCPConnection -State Listen | Select-Object -ExpandProperty OwningProcess | Sort-Object -Unique
foreach ($ownPid in $pids) {
    if ($exclude -contains $ownPid) {
        Write-Output "Skipping PID $ownPid"
        continue
    }
    $proc = Get-Process -Id $ownPid -ErrorAction SilentlyContinue
    if ($null -ne $proc) {
        Write-Output "Killing $($proc.ProcessName) PID $ownPid"
        try {
            Stop-Process -Id $ownPid -Force -ErrorAction Stop
            Write-Output "Killed PID $ownPid ($($proc.ProcessName))"
        } catch {
            Write-Output "Failed to kill PID $ownPid ($($proc.ProcessName)): $($_.Exception.Message)"
        }
    } else {
        Write-Output "No process for PID $ownPid"
    }
}
Write-Output "Done"
