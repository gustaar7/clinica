package com.gustavo.consultorio.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Table(name = "pacientes")
@Entity
public class PacienteEntity extends PessoaEntity{
}
