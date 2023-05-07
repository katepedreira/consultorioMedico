package com.example.consultorioMedico.api.controller;

import com.example.consultorioMedico.api.dto.ConsultaDTO;
import com.example.consultorioMedico.api.dto.MedicoDTO;
import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Consulta;
import com.example.consultorioMedico.model.entity.Endereco;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.model.entity.Paciente;
import com.example.consultorioMedico.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/consultas")
@RequiredArgsConstructor

public class ConsultaController {

    private final ConsultaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Consulta> consultas = service.getConsultas();
        return ResponseEntity.ok(consultas.stream().map(ConsultaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Consulta> consulta = service.getConsultaById(id);
        if (!consulta.isPresent()) {
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(consulta.map(ConsultaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ConsultaDTO dto) {
        try {
            Consulta consulta= converter(dto);
            consulta = service.salvar(consulta);
            return new ResponseEntity(consulta, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ConsultaDTO dto) {
        if (!service.getConsultaById(id).isPresent()) {
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Consulta consulta = converter(dto);
            consulta.setId(id);
            service.salvar(consulta);
            return ResponseEntity.ok(consulta);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Consulta> consulta = service.getConsultaById(id);
        if (!consulta.isPresent()) {
            return new ResponseEntity("Consulta não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(consulta.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Consulta converter(ConsultaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Consulta consulta = modelMapper.map(dto, Consulta.class);
        Medico medico = modelMapper.map(dto, Medico.class);
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);

        return consulta;
    }

}
