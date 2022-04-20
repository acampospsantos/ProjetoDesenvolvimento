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

    
    public boolean existsBycpf(String cpf) {
        return voluntarioRepository.existsBycpf(cpf);
    }

    public boolean existsByemail(String email) {
        return voluntarioRepository.existsByemail(email);
    }

    public boolean existsBynumero(String numero) {
        return voluntarioRepository.existsBynumero(numero);
    }    

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
