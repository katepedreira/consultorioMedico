package com.example.consultorioMedico.model.repository;

import com.example.consultorioMedico.model.entity.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {

}
