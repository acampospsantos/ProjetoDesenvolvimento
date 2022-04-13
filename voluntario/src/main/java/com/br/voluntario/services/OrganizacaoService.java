package com.br.voluntario.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.br.voluntario.models.OrganizacaoModel;
import com.br.voluntario.repositories.OrganizacaoRepository;

import org.springframework.stereotype.Service;

@Service
public class OrganizacaoService {
    
    final OrganizacaoRepository organizacaoRepository;

    public OrganizacaoService(OrganizacaoRepository organizacaoRepository) {
        this.organizacaoRepository = organizacaoRepository;
    }

    @Transactional
    public OrganizacaoModel save(OrganizacaoModel organizacaoModel) {
        return organizacaoRepository.save(organizacaoModel);
    }

    public List<OrganizacaoModel> findAll() {
        return organizacaoRepository.findAll();
    }

    public Optional<OrganizacaoModel> findById(long id) {
        return organizacaoRepository.findById(id);
    }

    @Transactional
    public void delete(OrganizacaoModel organizacaoModel) {
        organizacaoRepository.delete(organizacaoModel);
    }

}
