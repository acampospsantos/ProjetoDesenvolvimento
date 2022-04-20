package com.br.voluntario.repositories;

import com.br.voluntario.models.OrganizacaoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacaoRepository extends JpaRepository<OrganizacaoModel, Long> {
    
    boolean existsBycnpj(String cnpj);
}
