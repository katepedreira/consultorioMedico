package com.example.consultorioMedico.service;

import com.example.consultorioMedico.model.entity.Endereco;
import com.example.consultorioMedico.model.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service

public class EnderecoService {
    private EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public List<Endereco> getEnderecos() {
        return repository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Endereco salvar(Endereco endereco) {
        return repository.save(endereco);
    }
}
