package com.br.voluntario.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.br.voluntario.dtos.VoluntarioDto;
import com.br.voluntario.models.VoluntarioModel;
import com.br.voluntario.services.VoluntarioService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/voluntarios")
public class VoluntarioController {
    
    final VoluntarioService voluntarioService;
    
    public VoluntarioController(VoluntarioService voluntarioService) {
        this.voluntarioService = voluntarioService;
    }

    @PostMapping
    public ResponseEntity<Object> saveVoluntario(@RequestBody @Valid VoluntarioDto voluntarioDto) {
        

        var voluntarioModel = new VoluntarioModel();
        BeanUtils.copyProperties(voluntarioDto, voluntarioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(voluntarioService.save(voluntarioModel));
    }

    @GetMapping
    public ResponseEntity<List<VoluntarioModel>> getAllVoluntarios() {
        return ResponseEntity.status(HttpStatus.OK).body(voluntarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVoluntario(@PathVariable(value = "id") long id) {
        Optional<VoluntarioModel> voluntarioModelOptional = voluntarioService.findById(id);
        if(!voluntarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O voluntário não foi localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(voluntarioModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVoluntario(@PathVariable(value = "id") long id) {
        Optional<VoluntarioModel> voluntarioModelOptional = voluntarioService.findById(id);
        if(!voluntarioModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O voluntário não foi localizado");
        }
        voluntarioService.delete(voluntarioModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("O voluntário foi deletado");
    }

}
