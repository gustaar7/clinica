'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'
import { useAuth } from '@/components/AuthProvider'

export default function Home() {
  const { loginPaciente } = useAuth()
  const router = useRouter()
  const [email, setEmail] = useState('')
  const [senha, setSenha] = useState('')
  const [error, setError] = useState('')

  async function handleLogin(e: React.FormEvent) {
    e.preventDefault()
    const ok = await loginPaciente(email, senha)
    if (ok) router.push('/agendar')
    else setError('Credenciais inválidas')
  }

  return (
    <div style={{ minHeight: '100vh', display: 'flex', alignItems: 'center', justifyContent: 'center', background: '#F7F8FA', fontFamily: 'var(--font-sans)' }}>
      <form onSubmit={handleLogin} style={{ width: 420, background: '#fff', padding: 28, borderRadius: 12, border: '0.5px solid #E2E5EB' }}>
        <h2 style={{ margin: 0, fontSize: 18, fontWeight: 700 }}>Entrar</h2>
        <p style={{ marginTop: 6, color: '#6B7280' }}>Acesse sua conta para agendar consultas.</p>
        <div style={{ marginTop: 12, display: 'flex', flexDirection: 'column', gap: 10 }}>
          <input placeholder="E-mail" value={email} onChange={(e) => setEmail(e.target.value)} style={{ padding: '10px 12px', borderRadius: 8, border: '0.5px solid #D1D5DB' }} />
          <input placeholder="Senha" type="password" value={senha} onChange={(e) => setSenha(e.target.value)} style={{ padding: '10px 12px', borderRadius: 8, border: '0.5px solid #D1D5DB' }} />
          {error && <div style={{ color: '#dc2626' }}>{error}</div>}
          <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center', gap: 8, marginTop: 6 }}>
            <button type="button" onClick={() => router.push('/criar-conta')} style={{ padding: '9px 14px', background: '#fff', color: '#2563EB', border: '1px solid #2563EB', borderRadius: 8, cursor: 'pointer' }}>Criar conta</button>
            <button type="submit" style={{ padding: '9px 14px', background: '#2563EB', color: '#fff', border: 'none', borderRadius: 8, cursor: 'pointer' }}>Entrar</button>
          </div>
        </div>
      </form>
    </div>
  )
}
