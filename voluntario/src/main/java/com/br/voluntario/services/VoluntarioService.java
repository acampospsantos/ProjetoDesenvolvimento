package com.br.voluntario.services;

import javax.transaction.Transactional;

import com.br.voluntario.models.VoluntarioModel;
import com.br.voluntario.repositories.VoluntarioRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class VoluntarioService {
    
    final VoluntarioRepository voluntarioRepository;

    public VoluntarioService(VoluntarioRepository voluntarioRepository) {
        this.voluntarioRepository = voluntarioRepository;
    }

    @Transactional
    public VoluntarioModel save(VoluntarioModel voluntarioModel) {
        return voluntarioRepository.save(voluntarioModel);
    }

    /*
    public boolean existsByCPF(String cpf) {
        return voluntarioRepository.existsByCPF(cpf);
    }

    public boolean existsByEmail(String email) {
        return voluntarioRepository.existsByEmail(email);
    }

    public boolean existsByNumero(String numero) {
        return voluntarioRepository.existsByNumero(numero);
    }
    */

    public List<VoluntarioModel> findAll() {
        return voluntarioRepository.findAll();
    }

    public Optional<VoluntarioModel> findById(long id) {
        return voluntarioRepository.findById(id);
    }

    @Transactional
    public void delete(VoluntarioModel voluntarioModel) {
        voluntarioRepository.delete(voluntarioModel);
    }
    
}
