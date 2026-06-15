import type { Metadata } from 'next'
import './globals.css'

export const metadata: Metadata = {
  title: 'Consultório',
  description: 'Sistema de gerenciamento de consultório médico',
}

export default function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="pt-BR">
      <body>{children}</body>
    </html>
  )
}
