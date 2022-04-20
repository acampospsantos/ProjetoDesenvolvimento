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
}
