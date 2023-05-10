package com.example.consultorioMedico.service;

import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Paciente;
import com.example.consultorioMedico.model.entity.Procedimento;
import com.example.consultorioMedico.model.repository.ProcedimentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class ProcedimentoService {

    private ProcedimentoRepository repository;

    public ProcedimentoService(ProcedimentoRepository repository) {
        this.repository = repository;
    }

    public List<Procedimento> getProcedimentos() {
        return repository.findAll();
    }

    public Optional<Procedimento> getProcedimentoById(Long id) {
        return repository.findById(id);
    }


    @Transactional
    public Procedimento salvar(Procedimento procedimento) {
        validar(procedimento);
        return repository.save(procedimento);
    }

    @Transactional
    public void excluir(Procedimento procedimento) {
        Objects.requireNonNull(procedimento.getId());
        repository.delete(procedimento);
    }

    public void validar(Procedimento procedimento) {
        if (procedimento.getNome() == null || procedimento.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }


}
