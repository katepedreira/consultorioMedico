package com.example.consultorioMedico.api.dto;

import com.example.consultorioMedico.model.entity.Procedimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class ProcedimentoDTO {

    private String nome;
    private Long id;

    public static ProcedimentoDTO create(Procedimento procedimento) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(procedimento, ProcedimentoDTO.class);
    }





}
