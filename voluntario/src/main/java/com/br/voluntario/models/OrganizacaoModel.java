package com.br.voluntario.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZACAO")
public class OrganizacaoModel {
    
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true, length = 100)
    private String cnpj;
    @Column(nullable = false, length = 100)
    private String nome;
    @Column(nullable = false, length = 100)
    private String ramo;

    @OneToMany(mappedBy = "organizacaoModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrabalhoModel> trabalhos = new ArrayList<>();

    
    
    public List<TrabalhoModel> getTrabalhos() {
        return trabalhos;
    }

    public void setTrabalhos(List<TrabalhoModel> trabalhos) {
        this.trabalhos = trabalhos;
    }

    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }

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
