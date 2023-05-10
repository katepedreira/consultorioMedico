package com.example.consultorioMedico.service;

import com.example.consultorioMedico.exception.RegraNegocioException;
import com.example.consultorioMedico.model.entity.Consulta;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.model.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
@Service

public class ConsultaService {

    private ConsultaRepository repository;

    public ConsultaService(ConsultaRepository repository) {
        this.repository = repository;
    }

    public List<Consulta> getConsultas() {
        return repository.findAll();
    }

    public Optional<Consulta> getConsultaById(Long id) {
        return repository.findById(id);
    }

    public List<Consulta> getconsultasByMedico(Medico medico) {
        return repository.findByMedico(medico);
    }


    @Transactional
    public Consulta salvar(Consulta consulta) {
        validar(consulta);
        return repository.save(consulta);
    }

    @Transactional
    public void excluir(Consulta consulta) {
        Objects.requireNonNull(consulta.getId());
        repository.delete(consulta);
    }

    public void validar(Consulta consulta) {
        if (consulta.getPaciente() == null ) {
            throw new RegraNegocioException("Paciente inválido");
        }
        if (consulta.getMedico() == null ) {
            throw new RegraNegocioException("Medico inválido");
        }
        if (consulta.getProcedimento() == null ) {
            throw new RegraNegocioException("Procedimento inválido");
        }
    }

}
