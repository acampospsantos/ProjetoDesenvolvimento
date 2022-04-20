package com.br.voluntario.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TRABALHO")
public class TrabalhoModel {
    
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private int qtdVoluntarios;
    @Column(nullable = true)
    private LocalDateTime dataInicio;
    @Column(nullable = true)
    private LocalDateTime dataFim;
    @Column(nullable = false)
    private String informacoesGerais;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    
    }
    public void setId(long id) {
        this.id = id;    
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
