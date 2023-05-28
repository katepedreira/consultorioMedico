package com.example.consultorioMedico.api.controller;

import com.example.consultorioMedico.api.dto.MedicoDTO;
import com.example.consultorioMedico.api.dto.ProcedimentoDTO;
import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.model.entity.Procedimento;
import com.example.consultorioMedico.service.ProcedimentoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/procedimentos")
@RequiredArgsConstructor
@CrossOrigin

public class ProcedimentoController {

    private final ProcedimentoService service;

    @GetMapping()
    public ResponseEntity get() {
        List<Procedimento> procedimentos = service.getProcedimentos();
        return ResponseEntity.ok(procedimentos.stream().map(ProcedimentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Procedimento> procedimento = service.getProcedimentoById(id);
        if (!procedimento.isPresent()) {
            return new ResponseEntity("Procedimento não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(procedimento.map(ProcedimentoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody ProcedimentoDTO dto) {
        try {
            Procedimento procedimento= converter(dto);
            procedimento = service.salvar(procedimento);
            return new ResponseEntity(procedimento, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ProcedimentoDTO dto) {
        if (!service.getProcedimentoById(id).isPresent()) {
            return new ResponseEntity("Procedimento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Procedimento procedimento = converter(dto);
            procedimento.setId(id);
            service.salvar(procedimento);
            return ResponseEntity.ok(procedimento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Procedimento> procedimento = service.getProcedimentoById(id);
        if (!procedimento.isPresent()) {
            return new ResponseEntity("Procedimento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(procedimento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Procedimento converter(ProcedimentoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Procedimento procedimento = modelMapper.map(dto, Procedimento.class);

        return procedimento;
    }

}
