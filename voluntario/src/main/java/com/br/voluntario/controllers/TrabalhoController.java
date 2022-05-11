package com.br.voluntario.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<TrabalhoDto>> getAllTrabalhos() {
        List<TrabalhoDto> trabalhos = new ArrayList<>();
        List<TrabalhoModel> trabalhoModels = trabalhoService.findAll();
        TrabalhoDto trabalhoDto;
        for(int i = 0; i < trabalhoModels.size(); i++) {
            trabalhoDto = new TrabalhoDto();
            trabalhoDto.setDataInicio(trabalhoModels.get(i).getDataInicio());
            trabalhoDto.setDataFim(trabalhoModels.get(i).getDataFim());
            trabalhoDto.setInformacoesGerais(trabalhoModels.get(i).getInformacoesGerais());
            trabalhoDto.setNome(trabalhoModels.get(i).getNome());
            trabalhoDto.setQtdVoluntarios(trabalhoModels.get(i).getQtdVoluntarios());            
            trabalhos.add(trabalhoDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(trabalhos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTrabalho(@PathVariable(value = "id") long id) {
        Optional<TrabalhoModel> trabalhoModelOptional = trabalhoService.findById(id);
        if(!trabalhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O trabalho não foi localizado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(trabalhoModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTrabalho(@PathVariable(value = "id") long id) {
        Optional<TrabalhoModel> trabalhoModelOptional = trabalhoService.findById(id);
        if(!trabalhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O trabalho não foi localizado");
        }
        trabalhoService.delete(trabalhoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("O trabalho foi deletado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTrabalho(@PathVariable(value = "id") long id,
                                    @RequestBody @Valid TrabalhoDto trabalhoDto) {
        Optional<TrabalhoModel> trabalhoModelOptional = trabalhoService.findById(id);
        if(!trabalhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O trabalho não foi localizado");
        }
        var trabalhoModel = trabalhoModelOptional.get();
        trabalhoModel.setNome(trabalhoDto.getNome());
        trabalhoModel.setQtdVoluntarios(trabalhoDto.getQtdVoluntarios());
        trabalhoModel.setDataInicio(trabalhoDto.getDataInicio());
        trabalhoModel.setDataFim(trabalhoDto.getDataFim());
        trabalhoModel.setInformacoesGerais(trabalhoDto.getInformacoesGerais());
        return ResponseEntity.status(HttpStatus.OK).body(trabalhoService.save(trabalhoModel));
    }
}
