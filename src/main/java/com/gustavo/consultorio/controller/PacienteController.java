package com.gustavo.consultorio.controller;


import com.gustavo.consultorio.entity.PacienteEntity;
import com.gustavo.consultorio.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    //cria paciente
    @PostMapping
    public PacienteEntity criarPaciente(@RequestBody PacienteEntity paciente) {
        return pacienteRepository.save(paciente);
    }

    //atualiza paciente
    @PutMapping("{id}")
    public PacienteEntity atualizaPaciente(@PathVariable Long id,
                                           @RequestBody PacienteEntity pacienteAtualizado){
        PacienteEntity paciente = pacienteRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Nao foi possivel encontrar paciente com esse id"));

        paciente.setCpf(pacienteAtualizado.getCpf());
        paciente.setNome(pacienteAtualizado.getNome());
        paciente.setEmail(pacienteAtualizado.getEmail());
        paciente.setSenha(pacienteAtualizado.getSenha());

        return pacienteRepository.save(paciente);
    }

    //lista todos os pacientes
    @GetMapping
    public List<PacienteEntity> listAllPacientes(){
        return pacienteRepository.findAll();
    }

    //lista pacientes pelo id
    @GetMapping("{id}")
    public Optional<PacienteEntity> listaByIdPacientes(Long id){
        return pacienteRepository.findById(id);
    }

    //deleta pacientes pelo id
    @DeleteMapping("{id}")
    public void deletarPacientes(@PathVariable Long id){
        pacienteRepository.deleteById(id);
    }
}
