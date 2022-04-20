package com.br.voluntario.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.br.voluntario.dtos.OrganizacaoDto;
import com.br.voluntario.models.OrganizacaoModel;
import com.br.voluntario.services.OrganizacaoService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        if(organizacaoService.existsBycnpj(organizacaoDto.getCnpj())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("O CNPJ já está sendo utilizado.");
        }

        var organizacaoModel = new OrganizacaoModel();
        BeanUtils.copyProperties(organizacaoDto, organizacaoModel);    
        return ResponseEntity.status(HttpStatus.CREATED).body(organizacaoService.save(organizacaoModel));
    }

    @GetMapping
    public ResponseEntity<List<OrganizacaoModel>> getAllOrganizacoes() {
        return ResponseEntity.status(HttpStatus.OK).body(organizacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrganizacao(@PathVariable(value = "id") long id) {
        Optional<OrganizacaoModel> organizacaoModelOptional = organizacaoService.findById(id);
        if(!organizacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A organização não foi localizada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(organizacaoModelOptional.get());
    }    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteOrganizacao(@PathVariable(value = "id") long id) {
        Optional<OrganizacaoModel> organizacaoModelOptional = organizacaoService.findById(id);
        if(!organizacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A organização não foi localizada");
        }
        organizacaoService.delete(organizacaoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("O voluntário foi deletado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateOrganizacao(@PathVariable(value = "id") long id,
                                    @RequestBody @Valid OrganizacaoDto organizacaoDto) {
        Optional<OrganizacaoModel> organizacaoModelOptional = organizacaoService.findById(id);
        if(!organizacaoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A organização não foi localizada");
        }
        var organizacaoModel = organizacaoModelOptional.get();
        organizacaoModel.setNome(organizacaoDto.getNome());
        organizacaoModel.setRamo(organizacaoDto.getRamo());
        return ResponseEntity.status(HttpStatus.OK).body(organizacaoService.save(organizacaoModel));

    }
}
