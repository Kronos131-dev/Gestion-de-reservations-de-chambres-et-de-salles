package com.example.manager.business.service;

import com.example.manager.persistence.entity.Adresse;
import com.example.manager.persistence.entity.Role;
import com.example.manager.persistence.repository.AdresseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdresseService {

    private AdresseRepository adresseRepository;

    public List<Adresse> findAll() {
        return adresseRepository.findAll();
    }

    public Adresse findById(Long id) {
        return adresseRepository.findById(id).orElse(null);
    }
}
