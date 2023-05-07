package com.example.consultorioMedico.model.repository;

import com.example.consultorioMedico.model.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}
