package com.example.consultorioMedico.api.dto;

import com.example.consultorioMedico.model.entity.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MedicoDTO {

    private String crm;
    private Long id;
    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public static MedicoDTO create(Medico medico) {
        ModelMapper modelMapper = new ModelMapper();
        MedicoDTO dto = modelMapper.map(medico, MedicoDTO.class);
        dto.logradouro = medico.getEndereco().getLogradouro();
        dto.numero = medico.getEndereco().getNumero();
        dto.complemento = medico.getEndereco().getComplemento();
        dto.bairro = medico.getEndereco().getBairro();
        dto.cidade = medico.getEndereco().getCidade();
        dto.uf = medico.getEndereco().getUf();
        dto.cep = medico.getEndereco().getCep();
        return dto;
    }


}



