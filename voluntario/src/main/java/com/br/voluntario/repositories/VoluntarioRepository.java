package com.br.voluntario.repositories;

import com.br.voluntario.models.VoluntarioModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoluntarioRepository extends JpaRepository<VoluntarioModel, Long> {
    
        
    
}

