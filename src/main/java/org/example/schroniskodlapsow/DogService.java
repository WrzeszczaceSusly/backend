package org.example.schroniskodlapsow;

import org.example.schroniskodlapsow.entity.*;
import org.example.schroniskodlapsow.repository.*;
import lombok.RequiredArgsConstructor;
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

