'use client'

import { useEffect } from 'react'
import { useRouter } from 'next/navigation'
import Dashboard from '@/components/Dashboard'
import { useAuth } from '@/components/AuthProvider'

export default function AdminPage() {
  const { isAdmin } = useAuth()
  const router = useRouter()

  useEffect(() => {
    if (!isAdmin) router.push('/')
  }, [isAdmin, router])

  if (!isAdmin) return null
  return <Dashboard />
}
