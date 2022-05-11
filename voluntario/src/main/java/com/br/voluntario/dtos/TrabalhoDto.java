package com.br.voluntario.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TrabalhoDto {
    
    @NotNull
    private String nome;
    @Min(value = 1)
    private int qtdVoluntarios;
    @Future
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataInicio;        
    @Future
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataFim;
    @NotNull
    private String informacoesGerais;    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdVoluntarios() {
        return qtdVoluntarios;
    }
    
    public void setQtdVoluntarios(int qtdVoluntarios) {
        this.qtdVoluntarios = qtdVoluntarios;
    }
    
    public LocalDateTime getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public LocalDateTime getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }
    
    public String getInformacoesGerais() {
        return informacoesGerais;
    }
    
    public void setInformacoesGerais(String informacoesGerais) {
        this.informacoesGerais = informacoesGerais;
    }

    
}
