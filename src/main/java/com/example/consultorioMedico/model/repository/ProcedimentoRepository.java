package com.example.consultorioMedico.model.repository;

import com.example.consultorioMedico.model.entity.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {


}
