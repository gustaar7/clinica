'use client'

import React, { createContext, useContext, useState } from 'react'
import { useRouter } from 'next/navigation'
import { Paciente } from '@/types'

interface AuthContextValue {
    user: Paciente | null
    isAdmin: boolean
    loginPaciente: (email: string, senha: string) => Promise<boolean>
    logout: () => void
    loginAdmin: (email: string, senha: string) => boolean
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined)

const ADMIN_EMAIL = 'consultorioDevTest@gmail.com'
const ADMIN_PASS = 'consultorioTeste'

const API_BASE = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080'

export default function AuthProvider({ children }: { children: React.ReactNode }) {
    const [user, setUser] = useState<Paciente | null>(null)
    const [isAdmin, setIsAdmin] = useState(false)
    const router = useRouter()

    async function loginPaciente(email: string, senha: string) {
        try {
            const url = `${API_BASE.replace(/\/$/, '')}/pacientes/login`
            const res = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, senha }),
            })
            if (!res.ok) {
                console.error('loginPaciente: auth failed', res.status, res.statusText)
                return false
            }
            const paciente: Paciente = await res.json()
            setUser(paciente)
            return true
        } catch (e) {
            console.error('loginPaciente error', e)
            return false
        }
    }

    function loginAdmin(email: string, senha: string) {
        if (email === ADMIN_EMAIL && senha === ADMIN_PASS) {
            setIsAdmin(true)
            return true
        }
        return false
    }

    function logout() {
        setUser(null)
        setIsAdmin(false)
        router.push('/')
    }

    return (
        <AuthContext.Provider value={{ user, isAdmin, loginPaciente, logout, loginAdmin }}>{children}</AuthContext.Provider>
    )
}

export function useAuth() {
    const ctx = useContext(AuthContext)
    if (!ctx) throw new Error('useAuth must be used within AuthProvider')
    return ctx
}
