package com.example.consultorioMedico.api.dto;

import com.example.consultorioMedico.model.entity.Consulta;
import com.example.consultorioMedico.model.entity.Medico;
import com.example.consultorioMedico.model.entity.Paciente;
import com.example.consultorioMedico.model.entity.Procedimento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ConsultaDTO {
    private Long id;
    private Date data;
    private Paciente paciente;
    private Medico medico;
    private Procedimento procedimento;

    public static ConsultaDTO create(Consulta consulta) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(consulta, ConsultaDTO.class);
    }

}
