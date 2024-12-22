package org.example.schroniskodlapsow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.schroniskodlapsow.entity.dog.DogEntity;
import java.util.Base64;

@Data
@AllArgsConstructor
public class DogDto {
    String name;
    String breedName;
    String image;

    public static DogDto from(DogEntity entity) {
        return new DogDto(entity.getName(), entity.getBreed().getName(),
                Base64.getEncoder().encodeToString(entity.getImage()));
    }
}
