package com.gustavo.consultorio.controller;

import com.gustavo.consultorio.entity.ConsultaEntity;
import com.gustavo.consultorio.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    public ConsultaEntity criarConsulta(@RequestBody ConsultaEntity novaConsulta) {
        return consultaRepository.save(novaConsulta);
    }

    @PutMapping("{id}")
    public ConsultaEntity atualizarConsulta(@PathVariable Long id,
                                            @RequestBody ConsultaEntity consultaAtualizada) {
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nao encontramos esse id"));

        consulta.setCliente(consultaAtualizada.getCliente());
        consulta.setMedico(consultaAtualizada.getMedico());
        consulta.setHoraData(consultaAtualizada.getHoraData());
        consulta.setSala(consultaAtualizada.getSala());

        return consultaRepository.save(consulta);
    }

    @GetMapping
    public List<ConsultaEntity> listarTodas() {
        return consultaRepository.findAll();
    }

    @GetMapping("{id}")
    public ConsultaEntity buscarPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta nao encontrada"));
    }

    @DeleteMapping("{id}")
    public void deletarConsulta(@PathVariable Long id) {
        consultaRepository.deleteById(id);
    }
}
