package org.example.schroniskodlapsow.service;

import org.example.schroniskodlapsow.entity.breed.BreedEntity;
import lombok.RequiredArgsConstructor;
import org.example.schroniskodlapsow.repository.breed.BreedRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogService {
    private final BreedRepository repository;

    public Page<BreedEntity> getAllBreeds(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public List<BreedEntity> getAllBreeds() {
        return repository.findAll();
    }
    public Optional<BreedEntity> getBreedDetails(int breedId) {
        return repository.findById(breedId);
    }

}

