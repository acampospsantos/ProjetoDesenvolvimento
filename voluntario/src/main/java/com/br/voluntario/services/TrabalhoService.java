package com.br.voluntario.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.br.voluntario.models.TrabalhoModel;
import com.br.voluntario.repositories.TrabalhoRepository;

import org.springframework.stereotype.Service;

@Service
public class TrabalhoService {

    final TrabalhoRepository trabalhoRepository;

    public TrabalhoService(TrabalhoRepository trabalhoRepository) {
        this.trabalhoRepository = trabalhoRepository;
    }

    @Transactional
    public TrabalhoModel save(TrabalhoModel trabalhoModel) {
        return trabalhoRepository.save(trabalhoModel);
    }

    public List<TrabalhoModel> findAll() {
        return trabalhoRepository.findAll();
    }

    public Optional<TrabalhoModel> findById(long id) {
        return trabalhoRepository.findById(id);
    }

    @Transactional
    public void delete(TrabalhoModel trabalhoModel) {
        trabalhoRepository.delete(trabalhoModel);
    }
}
