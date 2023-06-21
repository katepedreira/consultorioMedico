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
    private Long idPaciente;
    private Long idMedico;
    private Long idProcedimento;
    private String nomeMedico;
    //private String crmMedico;
    private String nomePaciente;
    //private String convenioPaciente;
    private String nomeProcedimento;

    public static ConsultaDTO create(Consulta consulta) {
        ModelMapper modelMapper = new ModelMapper();
        ConsultaDTO dto = modelMapper.map(consulta, ConsultaDTO.class);
        dto.nomeMedico = consulta.getMedico().getNome();
        dto.nomePaciente = consulta.getPaciente().getNome();
        dto.nomeProcedimento = consulta.getProcedimento().getNome();
        return dto;
    }

}
