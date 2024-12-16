package org.example.schroniskodlapsow.entity.dog;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.schroniskodlapsow.entity.breed.BreedEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DogEntity {
    @Id
    @GeneratedValue
    int Id;
    String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "breed_id")
    @JsonBackReference
    BreedEntity breed;
}
