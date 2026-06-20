'use client'

import RequireAuth from '@/components/RequireAuth'
import AgendamentoPage from '@/components/AgendamentoPage'

export default function Agendar() {
    return (
        <RequireAuth>
            <AgendamentoPage />
        </RequireAuth>
    )
}
