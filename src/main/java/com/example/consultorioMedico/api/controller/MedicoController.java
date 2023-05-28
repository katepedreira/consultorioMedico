package com.example.consultorioMedico.api.controller;

import com.example.consultorioMedico.api.dto.MedicoDTO;
import com.example.consultorioMedico.model.entity.Endereco;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.service.EnderecoService;
import com.example.consultorioMedico.service.MedicoService;
import com.example.consultorioMedico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/medicos")
@RequiredArgsConstructor
@CrossOrigin
public class MedicoController {

    private final MedicoService service;
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity get() {
        List<Medico> medicos = service.getMedicos();
        return ResponseEntity.ok(medicos.stream().map(MedicoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Medico> medico = service.getMedicoById(id);
        if (!medico.isPresent()) {
            return new ResponseEntity("Médico não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(medico.map(MedicoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody MedicoDTO dto) {
        try {
            Medico medico = converter(dto);
            Endereco endereco = enderecoService.salvar(medico.getEndereco());
            medico.setEndereco(endereco);
            medico = service.salvar(medico);
            return new ResponseEntity(medico, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody MedicoDTO dto) {
        if (!service.getMedicoById(id).isPresent()) {
            return new ResponseEntity("Médico não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Medico medico = converter(dto);
            Endereco endereco = enderecoService.salvar(medico.getEndereco());
            medico.setEndereco(endereco);
            service.salvar(medico);
            return ResponseEntity.ok(medico);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Medico> medico = service.getMedicoById(id);
        if (!medico.isPresent()) {
            return new ResponseEntity("Médico não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(medico.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Medico converter(MedicoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Medico medico = modelMapper.map(dto, Medico.class);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        medico.setEndereco(endereco);

        return medico;
    }
}

