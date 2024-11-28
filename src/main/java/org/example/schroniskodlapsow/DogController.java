package org.example.schroniskodlapsow;

import lombok.RequiredArgsConstructor;
import org.example.schroniskodlapsow.entity.BreedEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DogController {

    private final DogService service;

    @GetMapping("/breeds")
    public ResponseEntity<List<BreedEntity>> getBreeds() {
        return ResponseEntity.ok(service.getAllBreeds());
    }
}
