package com.gustavo.consultorio.controller;

import com.gustavo.consultorio.entity.MedicoEntity;
import com.gustavo.consultorio.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")

public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    //cria
    @PostMapping
    public MedicoEntity criarMedico(@RequestBody MedicoEntity novoMedico) {
        return medicoRepository.save(novoMedico);
    }


    //atualiza
    @PutMapping("{id}")
    public MedicoEntity atualizarMedico(@PathVariable Long id,
                                        @RequestBody MedicoEntity medicoAtualizado) {
        MedicoEntity medico = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao encontramos medico com esse id"));

        medico.setCrm(medicoAtualizado.getCrm());
        medico.setCpf(medicoAtualizado.getCpf());
        medico.setNome(medicoAtualizado.getNome());
        medico.setEmail(medicoAtualizado.getEmail());
        medico.setSenha(medicoAtualizado.getSenha());

        return medicoRepository.save(medico);
    }

    //lista todos os medicos
    @GetMapping
    public List<MedicoEntity> listAllMedicos(){
        return medicoRepository.findAll();
    }

    //lista medicos por id
    @GetMapping("{id}")
    public Optional<MedicoEntity> listMedicosId(Long id){
        return Optional.of(medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nao encontramos medico com esse id")));
    }

    //deleta medicos
    @DeleteMapping("{id}")
    public void deletaMedico(@PathVariable Long id){
        medicoRepository.deleteById(id);
    }

}
