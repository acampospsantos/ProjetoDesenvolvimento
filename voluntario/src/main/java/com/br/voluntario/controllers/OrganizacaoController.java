package com.br.voluntario.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.br.voluntario.dtos.OrganizacaoDto;
import com.br.voluntario.dtos.TrabalhoDto;
import com.br.voluntario.models.OrganizacaoModel;
import com.br.voluntario.models.TrabalhoModel;
import com.br.voluntario.services.OrganizacaoService;
import com.br.voluntario.services.TrabalhoService;

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
    final TrabalhoService trabalhoService;

    public OrganizacaoController(OrganizacaoService organizacaoService, TrabalhoService trabalhoService) {
        this.organizacaoService = organizacaoService;
        this.trabalhoService = trabalhoService;
    }

    @PostMapping("/{orgId}/trabalho")
    public ResponseEntity<Object> postController(@PathVariable("orgId") Long orgId,
    @RequestBody TrabalhoDto trabalhoDto) {
        Optional<OrganizacaoModel> organizacaoModelOptional = organizacaoService.findById(orgId);
        var trabalhoModel = new TrabalhoModel();
        BeanUtils.copyProperties(trabalhoDto, trabalhoModel);    
        trabalhoModel.setOrganizacaoModel(organizacaoModelOptional.get());
        organizacaoModelOptional.get().getTrabalhos().add(trabalhoModel);        
        
        //var organizacaoModel = organizacaoModelOptional.get();
        //organizacaoService.save(organizacaoModel);
        return ResponseEntity.status(HttpStatus.OK).body(trabalhoService.save(trabalhoModel));
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
    public ResponseEntity<List<OrganizacaoDto>> getAllOrganizacoes() {
        List<OrganizacaoDto> organizacoes = new ArrayList<>();
        List<OrganizacaoModel> organizacaoModels = organizacaoService.findAll();
        OrganizacaoDto organizacaoDto;
        for(int i = 0; i < organizacaoModels.size(); i++) {
            organizacaoDto = new OrganizacaoDto();
            organizacaoDto.setCnpj(organizacaoModels.get(i).getCnpj());
            organizacaoDto.setNome(organizacaoModels.get(i).getNome());
            organizacaoDto.setRamo(organizacaoModels.get(i).getRamo());
            organizacoes.add(organizacaoDto);
        }
        return ResponseEntity.status(HttpStatus.OK).body(organizacoes);
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
