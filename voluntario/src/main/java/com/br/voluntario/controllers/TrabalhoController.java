package com.br.voluntario.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.br.voluntario.dtos.TrabalhoDto;
import com.br.voluntario.models.TrabalhoModel;
import com.br.voluntario.services.TrabalhoService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<List<TrabalhoModel>> getAllTrabalhos() {
        return ResponseEntity.status(HttpStatus.OK).body(trabalhoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTrabalho(@PathVariable(value = "id") long id) {
        Optional<TrabalhoModel> trabalhoModelOptional = trabalhoService.findById(id);
        if(!trabalhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O trabalho não foi localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(trabalhoModelOptional.get());
    }

    
}
