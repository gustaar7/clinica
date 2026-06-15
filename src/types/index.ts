export interface Medico {
  id: number
  nome: string
  crm: string
  email: string
  cpf: string
  senha?: string
}

export interface Paciente {
  id: number
  nome: string
  email: string
  cpf: string
  senha?: string
}

export interface Consulta {
  id: number
  paciente: Paciente
  medico: Medico
  horaData: string
  sala: string
}

export type Section = 'consultas' | 'medicos' | 'pacientes'
