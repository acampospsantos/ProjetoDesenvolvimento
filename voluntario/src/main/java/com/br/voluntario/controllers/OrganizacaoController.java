package com.br.voluntario.controllers;

import javax.validation.Valid;

import com.br.voluntario.dtos.OrganizacaoDto;
import com.br.voluntario.models.OrganizacaoModel;
import com.br.voluntario.services.OrganizacaoService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/organizacoes")
public class OrganizacaoController {
    
    final OrganizacaoService organizacaoService;

    public OrganizacaoController(OrganizacaoService organizacaoService) {
        this.organizacaoService = organizacaoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrganizacao(@RequestBody @Valid OrganizacaoDto organizacaoDto) {
        var organizacaoModel = new OrganizacaoModel();
        BeanUtils.copyProperties(organizacaoDto, organizacaoModel);    
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacaoService.save(organizacaoModel));
    }
}
