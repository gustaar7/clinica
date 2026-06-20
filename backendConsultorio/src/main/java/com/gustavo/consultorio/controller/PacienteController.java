package com.gustavo.consultorio.controller;

import com.gustavo.consultorio.entity.PacienteEntity;
import com.gustavo.consultorio.service.PacienteService;
import com.gustavo.consultorio.dto.LoginRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public PacienteEntity criarPaciente(@RequestBody PacienteEntity paciente) {
        return pacienteService.criarPaciente(paciente);
    }

    @PostMapping("/login")
    public ResponseEntity<PacienteEntity> login(@RequestBody LoginRequest login) {
        return pacienteService.authenticate(login.getEmail(), login.getSenha())
                .map(p -> ResponseEntity.ok(p))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/{id}")
    public PacienteEntity atualizaPaciente(@PathVariable Long id,
                                           @RequestBody PacienteEntity pacienteAtualizado) {
        return pacienteService.atualizaPaciente(id, pacienteAtualizado);
    }

    @GetMapping
    public List<PacienteEntity> listAllPacientes() {
        return pacienteService.listAllPacientes();
    }

    @GetMapping("/{id}")
    public Optional<PacienteEntity> listaByIdPacientes(@PathVariable Long id) {
        return pacienteService.listByIdPacientes(id);
    }

    @DeleteMapping("/{id}")
    public void deletarPacientes(@PathVariable Long id) {
        pacienteService.deletarPaciente(id);
    }
}
