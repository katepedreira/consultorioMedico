package com.example.consultorioMedico.service;

import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.*;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.model.repository.MedicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class MedicoService {
    private MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public List<Medico> getMedicos() {
        return repository.findAll();
    }

    public Optional<Medico> getMedicoById(Long id) {
        return repository.findById(id);
    }

    @Transactional // sempre usar transacao quando for fazer alteracao no estado do  bd
    public Medico salvar(Medico medico) {
        validar(medico);
        return repository.save(medico);
    }

    @Transactional
    public void excluir(Medico medico) {
        Objects.requireNonNull(medico.getId());
        repository.delete(medico);
    }

    public void validar(Medico medico) {
        if (medico.getNome() == null || medico.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (medico.getCrm() == null) {
            throw new RegraNegocioException("CRM inválido");
        }
    }
}
