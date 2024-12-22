package org.example.schroniskodlapsow;

import org.example.schroniskodlapsow.entity.breed.BreedEntity;
import org.example.schroniskodlapsow.entity.dog.DogEntity;
import org.example.schroniskodlapsow.entity.user.MyUser;
import org.example.schroniskodlapsow.repository.breed.BreedRepository;
import org.example.schroniskodlapsow.repository.dog.DogRepository;
import org.example.schroniskodlapsow.repository.user.MyUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;

import java.util.List;


@SpringBootApplication
public class SchroniskoDlaPsowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchroniskoDlaPsowApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            MyUserRepository myUserRepository,
            BreedRepository breedRepository,
            DogRepository dogRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            // Dodanie użytkownika
            String password = passwordEncoder.encode("1234");
            MyUser myUser = MyUser.builder()
                    .firstName("Jan")
                    .lastName("Kowalski")
                    .email("test@gmail.com")
                    .password(password)
                    .build();
            myUserRepository.save(myUser);

            // Lista ras i psów (identyczne dane jak wcześniej)
            addBreedWithDogs(breedRepository, dogRepository, "Labrador Retriever", List.of("Burek", "Reksio"));
            addBreedWithDogs(breedRepository, dogRepository, "German Shepherd", List.of("Azor", "Max"));
            addBreedWithDogs(breedRepository, dogRepository, "Golden Retriever", List.of("Ciapek", "Rocky"));
            addBreedWithDogs(breedRepository, dogRepository, "Bulldog", List.of("Bella", "Molly"));
            addBreedWithDogs(breedRepository, dogRepository, "Beagle", List.of("Daisy", "Charlie"));
            addBreedWithDogs(breedRepository, dogRepository, "Poodle", List.of("Luna", "Cooper"));
            addBreedWithDogs(breedRepository, dogRepository, "Boxer", List.of("Buddy", "Bailey"));
            addBreedWithDogs(breedRepository, dogRepository, "Dachshund", List.of("Rosie", "Toby"));
            addBreedWithDogs(breedRepository, dogRepository, "Siberian Husky", List.of("Roxy", "Sammy"));
            addBreedWithDogs(breedRepository, dogRepository, "Great Dane", List.of("Zoe", "Simba"));
            addBreedWithDogs(breedRepository, dogRepository, "Shih Tzu", List.of("Duke", "Sadie"));
            addBreedWithDogs(breedRepository, dogRepository, "Rottweiler", List.of("Milo", "Jack"));
            addBreedWithDogs(breedRepository, dogRepository, "Chihuahua", List.of("Lilly", "Oscar"));
            addBreedWithDogs(breedRepository, dogRepository, "Border Collie", List.of("Bruno", "Ruby"));
            addBreedWithDogs(breedRepository, dogRepository, "Maltese", List.of("Shadow", "Lucy"));
            addBreedWithDogs(breedRepository, dogRepository, "Yorkshire Terrier", List.of("Piper", "Jake"));
            addBreedWithDogs(breedRepository, dogRepository, "Pembroke Welsh Corgi", List.of("Ace", "Oliver"));
            addBreedWithDogs(breedRepository, dogRepository, "Boston Terrier", List.of("Finn", "Ginger"));
            addBreedWithDogs(breedRepository, dogRepository, "Shetland Sheepdog", List.of("Hunter", "Sophie"));
            addBreedWithDogs(breedRepository, dogRepository, "Dalmatian", List.of("Nala", "Benji"));
        };
    }

    private void addBreedWithDogs(
            BreedRepository breedRepository,
            DogRepository dogRepository,
            String breedName,
            List<String> dogNames
    ) throws IOException {
        // Pobierz istniejącą rasę lub utwórz nową
        BreedEntity breed = breedRepository.findByName(breedName);
        if (breed == null) {
            breed = BreedEntity.builder()
                    .name(breedName)
                    .build();
            breed = breedRepository.saveAndFlush(breed); // Upewnij się, że encja jest zarządzana
        }

        // Wczytaj obraz


        // Twórz psy z przypisaną rasą
        for (String name : dogNames) {
            String breedString = breedName.replace(" ", "") + "/" + name + ".png";
            Path imagePath = Path.of("src/main/resources/static/" + breedString);
            byte[] imageData = Files.readAllBytes(imagePath);
            DogEntity dog = DogEntity.builder()
                    .name(name)
                    .breed(breed) // Przypisanie zarządzanej encji rasy
                    .image(imageData)
                    .build();
            dogRepository.save(dog); // Zapisanie psa
        }
    }
}
