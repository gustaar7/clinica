import { Consulta, Medico, Paciente } from '@/types'

export const mockMedicos: Medico[] = [

]

export const mockPacientes: Paciente[] = [
]

export const mockConsultas: Consulta[] = [

]

export function getConsultaStatus(horaData: string): 'past' | 'today' | 'future' {
  const date = new Date(horaData)
  const now = new Date()
  const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
  const itemDay = new Date(date.getFullYear(), date.getMonth(), date.getDate())
  if (itemDay < today) return 'past'
  if (itemDay.getTime() === today.getTime()) return 'today'
  return 'future'
}

export function initials(name: string): string {
  return name
    .split(' ')
    .slice(0, 2)
    .map((w) => w[0])
    .join('')
    .toUpperCase()
}

export function formatDateTime(dt: string): string {
  const d = new Date(dt)
  return (
    d.toLocaleDateString('pt-BR') +
    ' · ' +
    d.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' })
  )
}
