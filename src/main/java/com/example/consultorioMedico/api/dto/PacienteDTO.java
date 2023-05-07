package com.example.consultorioMedico.api.dto;

import com.example.consultorioMedico.model.entity.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PacienteDTO {

    private Long id;
    private String nome;
    private Date dataNascimento;
    private String convenio;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public static PacienteDTO create(Paciente paciente) {
        ModelMapper modelMapper = new ModelMapper();
        PacienteDTO dto = modelMapper.map(paciente, PacienteDTO.class);
        dto.logradouro = paciente.getEndereco().getLogradouro();
        dto.numero = paciente.getEndereco().getNumero();
        dto.complemento = paciente.getEndereco().getComplemento();
        dto.bairro = paciente.getEndereco().getBairro();
        dto.cidade = paciente.getEndereco().getCidade();
        dto.uf = paciente.getEndereco().getUf();
        dto.cep = paciente.getEndereco().getCep();
        return dto;
    }



}
