package com.example.consultorioMedico.service;

import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Paciente;
import com.example.consultorioMedico.model.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class PacienteService {
    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<Paciente> getPacientes() {
        return repository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return repository.findById(id);
    }

    @Transactional // sempre usar transacao quando for fazer alteracao no estado do  bd
    public Paciente salvar(Paciente paciente) {
        validar(paciente);
        return repository.save(paciente);
    }

    @Transactional
    public void excluir(Paciente paciente) {
        Objects.requireNonNull(paciente.getId());
        repository.delete(paciente);
    }

    public void validar(Paciente paciente) {
        if (paciente.getNome() == null || paciente.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
        if (paciente.getConvenio() == null) {
            throw new RegraNegocioException("Convênio inválido");
        }
    }
}
