package com.br.voluntario.repositories;

import com.br.voluntario.models.TrabalhoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabalhoRepository extends JpaRepository<TrabalhoModel, Long> {
    
}
