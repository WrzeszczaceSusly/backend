package org.example.schroniskodlapsow.entity.breed;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.example.schroniskodlapsow.entity.dog.DogEntity;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BreedEntity {
    @Id
    @GeneratedValue
    int Id;
    String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "breed", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<DogEntity> dogs;
}
