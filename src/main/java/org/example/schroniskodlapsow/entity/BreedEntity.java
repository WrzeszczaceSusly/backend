package org.example.schroniskodlapsow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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
}

