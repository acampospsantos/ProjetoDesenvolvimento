package com.br.voluntario.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.br.voluntario.models.TrabalhoModel;

public class OrganizacaoDto {
    
    @NotBlank
    private String cnpj;
    @NotBlank
    private String nome;
    @NotBlank
    private String ramo;
    private List<TrabalhoModel> trabalhos = new ArrayList<>();
    
    public String getCnpj() {
        return cnpj;
    }
    
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRamo() {
        return ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    
    
}
