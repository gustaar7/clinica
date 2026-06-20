package com.gustavo.consultorio.service;

import com.gustavo.consultorio.entity.PacienteEntity;
import com.gustavo.consultorio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public PacienteEntity criarPaciente(PacienteEntity paciente) {
        // hash password before saving
        paciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
        return pacienteRepository.save(paciente);
    }

    public PacienteEntity atualizaPaciente(Long id, PacienteEntity pacienteAtualizado) {
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente nao encontrado"));

        paciente.setCpf(pacienteAtualizado.getCpf());
        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setEmail(pacienteAtualizado.getEmail());
        if (pacienteAtualizado.getSenha() != null && !pacienteAtualizado.getSenha().isBlank()) {
            paciente.setSenha(passwordEncoder.encode(pacienteAtualizado.getSenha()));
        }

        return pacienteRepository.save(paciente);
    }

    public List<PacienteEntity> listAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<PacienteEntity> listByIdPacientes(Long id) {
        return pacienteRepository.findById(id);
    }

    public void deletarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    public Optional<PacienteEntity> authenticate(String email, String rawSenha) {
        return pacienteRepository.findByEmail(email)
                .filter(p -> passwordEncoder.matches(rawSenha, p.getSenha()));
    }
}
