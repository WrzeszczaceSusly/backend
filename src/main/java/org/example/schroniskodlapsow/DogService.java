package org.example.schroniskodlapsow;

import lombok.RequiredArgsConstructor;
import org.example.schroniskodlapsow.entity.BreedEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DogService {

    public List<BreedEntity> getAllBreeds() {
        return List.of(BreedEntity.builder().id(1).name("Labrador").build(),
                BreedEntity.builder().id(2).name("Jamnik").build());
    }

}
