package org.example.schroniskodlapsow.service;

import org.example.schroniskodlapsow.DogDto;
import org.example.schroniskodlapsow.entity.breed.BreedEntity;
import lombok.RequiredArgsConstructor;
import org.example.schroniskodlapsow.entity.dog.DogEntity;
import org.example.schroniskodlapsow.repository.breed.BreedRepository;
import org.example.schroniskodlapsow.repository.dog.DogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DogService {
    private final BreedRepository repository;
    private final DogRepository dogRepository;

    public Page<BreedEntity> getAllBreeds(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public List<DogDto> getAllDogs(Pageable pageable) {
        return dogRepository.findAll(pageable).getContent().stream()
                .map(DogDto::from).toList();
    }
    public List<BreedEntity> getAllBreeds() {
        return repository.findAll();
    }
    public Optional<BreedEntity> getBreedDetails(int breedId) {
        return repository.findById(breedId);
    }

}

