package com.example.consultorioMedico.model.repository;

import com.example.consultorioMedico.model.entity.Consulta;
import com.example.consultorioMedico.model.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByMedico(Medico medico);

}

