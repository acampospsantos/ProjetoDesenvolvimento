package com.br.voluntario.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class TrabalhoDto {
    
    @Min(value = 1)
    private int qtdVoluntarios;
    @FutureOrPresent
    private LocalDateTime dataInicio;    
    @Future
    private LocalDateTime dataFim;
    @NotNull
    private String informacoesGerais;
    
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
