package org.example.schroniskodlapsow.repository;

import com.example.katalogpsow.entity.BreedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BreedRepository extends JpaRepository<BreedEntity, Integer> {

    Optional<BreedEntity> findDogEntityByName(String name);
}