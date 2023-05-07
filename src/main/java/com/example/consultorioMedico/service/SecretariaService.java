package com.example.consultorioMedico.service;

import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Secretaria;
import com.example.consultorioMedico.model.repository.SecretariaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class SecretariaService {
    private SecretariaRepository repository;

    public SecretariaService(SecretariaRepository repository) {
        this.repository = repository;
    }

    public List<Secretaria> getSecretarias() {
        return repository.findAll();
    }

    public Optional<Secretaria> getSecretariaById(Long id) {
        return repository.findById(id);
    }

    @Transactional // sempre usar transacao quando for fazer alteracao no estado do  bd
    public Secretaria salvar(Secretaria secretaria) {
        validar(secretaria);
        return repository.save(secretaria);
    }

    @Transactional
    public void excluir(Secretaria secretaria) {
        Objects.requireNonNull(secretaria.getId());
        repository.delete(secretaria);
    }

    public void validar(Secretaria secretaria) {
        if (secretaria.getNome() == null || secretaria.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }
}
