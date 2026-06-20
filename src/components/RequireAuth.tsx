'use client'

import React, { useEffect } from 'react'
import { useRouter } from 'next/navigation'
import { useAuth } from './AuthProvider'

export default function RequireAuth({ children }: { children: React.ReactNode }) {
    const { user } = useAuth()
    const router = useRouter()

    useEffect(() => {
        if (!user) router.push('/')
    }, [user, router])

    if (!user) return null
    return <>{children}</>
}
