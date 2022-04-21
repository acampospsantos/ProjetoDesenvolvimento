package com.br.voluntario.controllers;

import javax.validation.Valid;

import com.br.voluntario.dtos.TrabalhoDto;
import com.br.voluntario.models.TrabalhoModel;
import com.br.voluntario.services.TrabalhoService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/trabalhos")
public class TrabalhoController {
    
    final TrabalhoService trabalhoService;

    public TrabalhoController(TrabalhoService trabalhoService) {
        this.trabalhoService = trabalhoService;
    }

    @PostMapping
    public ResponseEntity<Object> saveTrabalho(@RequestBody @Valid TrabalhoDto trabalhoDto) {
        var trabalhoModel = new TrabalhoModel();
        BeanUtils.copyProperties(trabalhoDto, trabalhoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(trabalhoService.save(trabalhoModel));
    }
}
