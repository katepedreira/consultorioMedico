package com.example.consultorioMedico.api.controller;

import com.example.consultorioMedico.api.dto.MedicoDTO;
import com.example.consultorioMedico.api.dto.PacienteDTO;
import com.example.consultorioMedico.api.dto.SecretariaDTO;
import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Endereco;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.model.entity.Paciente;
import com.example.consultorioMedico.model.entity.Secretaria;
import com.example.consultorioMedico.service.SecretariaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/secretarias")
@RequiredArgsConstructor

public class SecretariaController {

    private final SecretariaService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Secretaria> secretarias = service.getSecretarias();
        return ResponseEntity.ok(secretarias.stream().map(SecretariaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Secretaria> secretaria = service.getSecretariaById(id);
        if (!secretaria.isPresent()) {
            return new ResponseEntity("Secretaria(o) não encontrada(o)", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(secretaria.map(SecretariaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody SecretariaDTO dto) {
        try {
            Secretaria secretaria = converter(dto);
            secretaria = service.salvar(secretaria);
            return new ResponseEntity(secretaria, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody SecretariaDTO dto) {
        if (!service.getSecretariaById(id).isPresent()) {
            return new ResponseEntity("Secretária(o) não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Secretaria secretaria = converter(dto);
            secretaria.setId(id);
            service.salvar(secretaria);
            return ResponseEntity.ok(secretaria);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Secretaria> secretaria = service.getSecretariaById(id);
        if (!secretaria.isPresent()) {
            return new ResponseEntity("Secretária(o) não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(secretaria.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Secretaria converter(SecretariaDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Secretaria secretaria = modelMapper.map(dto, Secretaria.class);
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        secretaria.setEndereco(endereco);

        return secretaria;
    }

}
