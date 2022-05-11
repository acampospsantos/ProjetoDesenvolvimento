package com.br.voluntario.dtos;

import javax.validation.constraints.NotBlank;

public class OrganizacaoDto {
    
    @NotBlank
    private String cnpj;
    @NotBlank
    private String nome;
    @NotBlank
    private String ramo;    
    
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
