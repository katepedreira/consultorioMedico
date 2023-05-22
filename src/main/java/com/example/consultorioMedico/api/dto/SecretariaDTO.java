package com.example.consultorioMedico.api.dto;

import com.example.consultorioMedico.model.entity.Secretaria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class SecretariaDTO {

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

    public static SecretariaDTO create(Secretaria secretaria) {
        ModelMapper modelMapper = new ModelMapper();
        SecretariaDTO dto = modelMapper.map(secretaria, SecretariaDTO.class);
        dto.logradouro = secretaria.getEndereco().getLogradouro();
        dto.numero = secretaria.getEndereco().getNumero();
        dto.complemento = secretaria.getEndereco().getComplemento();
        dto.bairro = secretaria.getEndereco().getBairro();
        dto.cidade = secretaria.getEndereco().getCidade();
        dto.uf = secretaria.getEndereco().getUf();
        dto.cep = secretaria.getEndereco().getCep();
        return dto;
    }
}
