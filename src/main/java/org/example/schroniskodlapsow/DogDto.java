package org.example.schroniskodlapsow;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.schroniskodlapsow.entity.dog.DogEntity;

@Data
@AllArgsConstructor
public class DogDto {
    String name;
    String breedName;

    public static DogDto from(DogEntity entity) {
        return new DogDto(entity.getName(), entity.getBreed().getName());
    }
}
