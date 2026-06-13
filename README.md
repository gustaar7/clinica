# Consultório API

API REST para gerenciamento de consultas médicas, desenvolvida com Spring Boot 4.1 e Java 21.

## Tecnologias

- Java 21
- Spring Boot 4.1
- Spring Data JPA
- Spring Validation
- H2 Database (em memória)
- Lombok
- Maven

## Estrutura do Projeto

```
src/main/java/com/gustavo/consultorio/
├── controller/
│   ├── ConsultaController.java
│   ├── MedicoController.java
│   └── PacienteController.java
├── entity/
│   ├── PessoaEntity.java       ← superclasse abstrata
│   ├── MedicoEntity.java
│   ├── PacienteEntity.java
│   └── ConsultaEntity.java
├── repository/
│   ├── ConsultaRepository.java
│   ├── MedicoRepository.java
│   └── PacienteRepository.java
└── service/
    ├── ConsultaService.java
    ├── MedicoService.java
    └── PacienteService.java
```

## Como Rodar

### Pré-requisitos

- Java 21+
- Maven (ou use o `mvnw` incluído)

### Rodando a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

### H2 Console

Acesse o banco em memória via browser em `http://localhost:8080/h2-console`.

> Configurar em `application.properties`:
> ```properties
> spring.datasource.url=jdbc:h2:mem:consultoriodb
> spring.h2.console.enabled=true
> spring.jpa.show-sql=true
> ```

## Endpoints

### Médicos `/medicos`

| Método | Rota          | Descrição               |
|--------|---------------|-------------------------|
| POST   | `/medicos`    | Cadastrar médico        |
| GET    | `/medicos`    | Listar todos            |
| GET    | `/medicos/{id}` | Buscar por ID         |
| PUT    | `/medicos/{id}` | Atualizar médico      |
| DELETE | `/medicos/{id}` | Remover médico        |

**Exemplo de body (POST/PUT):**
```json
{
  "cpf": 12345678901,
  "nome": "Dr. João Silva",
  "email": "joao@clinica.com",
  "senha": "senha123",
  "crm": "CRM/MG 12345"
}
```

---

### Pacientes `/pacientes`

| Método | Rota              | Descrição               |
|--------|-------------------|-------------------------|
| POST   | `/pacientes`      | Cadastrar paciente      |
| GET    | `/pacientes`      | Listar todos            |
| GET    | `/pacientes/{id}` | Buscar por ID           |
| PUT    | `/pacientes/{id}` | Atualizar paciente      |
| DELETE | `/pacientes/{id}` | Remover paciente        |

**Exemplo de body (POST/PUT):**
```json
{
  "cpf": 98765432100,
  "nome": "Maria Souza",
  "email": "maria@email.com",
  "senha": "senha123"
}
```

---

### Consultas `/consultas`

| Método | Rota              | Descrição               |
|--------|-------------------|-------------------------|
| POST   | `/consultas`      | Agendar consulta        |
| GET    | `/consultas`      | Listar todas            |
| GET    | `/consultas/{id}` | Buscar por ID           |
| PUT    | `/consultas/{id}` | Atualizar consulta      |
| DELETE | `/consultas/{id}` | Cancelar consulta       |

**Exemplo de body (POST/PUT):**
```json
{
  "cliente": "Maria Souza",
  "medico": "Dr. João Silva",
  "horaData": "2025-08-15T14:30:00",
  "sala": "Sala 3"
}
```

> O sistema valida automaticamente conflitos de horário: não permite dois agendamentos para o mesmo médico ou mesma sala no mesmo horário.

## Regras de Negócio

- Médico e paciente precisam de CPF único e e-mail único
- Consultas não podem ter conflito de horário para o mesmo médico
- Consultas não podem ter conflito de horário para a mesma sala
- Nome do cliente na consulta é obrigatório

## Pendências / Melhorias Futuras

- [ ] Corrigir `pom.xml` — artifact IDs das dependências de teste estão incorretos
- [ ] Relacionar `ConsultaEntity` com `MedicoEntity`/`PacienteEntity` via `@ManyToOne`
- [ ] Criar DTOs para não expor senha nos responses
- [ ] Usar `MedicoService` e `PacienteService` nos respectivos controllers
- [ ] Tratamento global de exceções com `@RestControllerAdvice`
- [ ] Corrigir `atualizarConsulta` — verificação de conflito falha na atualização (não exclui o próprio ID)
- [ ] Adicionar `@PathVariable` no `listMedicosId` do `MedicoController`
- [ ] Paginação nas listagens (`Pageable`)
- [ ] Autenticação com Spring Security + JWT
- [ ] Adicionar anotações Lombok em `PacienteEntity`
- [ ] Testes unitários (JUnit 5 + Mockito) e de integração