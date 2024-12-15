package org.example.schroniskodlapsow;

import org.example.schroniskodlapsow.entity.breed.BreedEntity;
import org.example.schroniskodlapsow.entity.dog.DogEntity;
import org.example.schroniskodlapsow.entity.user.MyUser;
import org.example.schroniskodlapsow.repository.breed.BreedRepository;
import org.example.schroniskodlapsow.repository.user.MyUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootApplication
public class SchroniskoDlaPsowApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchroniskoDlaPsowApplication.class, args);
    }

     @Bean
    public CommandLineRunner commandLineRunner(
            MyUserRepository myUserRepository,
            BreedRepository repository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            //user
            String password = passwordEncoder.encode("1234");
            MyUser myUser = MyUser.builder().firstName("Jan").lastName("Kowalski").email("test@gmail.com").password(password).build();
            myUserRepository.save(myUser);

            // Lista nazw psów
            List<String> dogNames1 = List.of("Burek", "Reksio");
            List<String> dogNames2 = List.of("Azor", "Max");
            List<String> dogNames3 = List.of("Ciapek", "Rocky");
            List<String> dogNames4 = List.of("Bella", "Molly");
            List<String> dogNames5 = List.of("Daisy", "Charlie");
            List<String> dogNames6 = List.of("Luna", "Cooper");
            List<String> dogNames7 = List.of("Buddy", "Bailey");
            List<String> dogNames8 = List.of("Rosie", "Toby");
            List<String> dogNames9 = List.of("Roxy", "Sammy");
            List<String> dogNames10 = List.of("Zoe", "Simba");
            List<String> dogNames11 = List.of("Duke", "Sadie");
            List<String> dogNames12 = List.of("Milo", "Jack");
            List<String> dogNames13 = List.of("Lilly", "Oscar");
            List<String> dogNames14 = List.of("Bruno", "Ruby");
            List<String> dogNames15 = List.of("Shadow", "Lucy");
            List<String> dogNames16 = List.of("Piper", "Jake");
            List<String> dogNames17 = List.of("Ace", "Oliver");
            List<String> dogNames18 = List.of("Finn", "Ginger");
            List<String> dogNames19 = List.of("Hunter", "Sophie");
            List<String> dogNames20 = List.of("Nala", "Benji");

            // Tworzenie ras i psów
            addBreedWithDogs(repository, "Labrador Retriever", dogNames1);
            addBreedWithDogs(repository, "German Shepherd", dogNames2);
            addBreedWithDogs(repository, "Golden Retriever", dogNames3);
            addBreedWithDogs(repository, "Bulldog", dogNames4);
            addBreedWithDogs(repository, "Beagle", dogNames5);
            addBreedWithDogs(repository, "Poodle", dogNames6);
            addBreedWithDogs(repository, "Boxer", dogNames7);
            addBreedWithDogs(repository, "Dachshund", dogNames8);
            addBreedWithDogs(repository, "Siberian Husky", dogNames9);
            addBreedWithDogs(repository, "Great Dane", dogNames10);
            addBreedWithDogs(repository, "Shih Tzu", dogNames11);
            addBreedWithDogs(repository, "Rottweiler", dogNames12);
            addBreedWithDogs(repository, "Chihuahua", dogNames13);
            addBreedWithDogs(repository, "Border Collie", dogNames14);
            addBreedWithDogs(repository, "Maltese", dogNames15);
            addBreedWithDogs(repository, "Yorkshire Terrier", dogNames16);
            addBreedWithDogs(repository, "Pembroke Welsh Corgi", dogNames17);
            addBreedWithDogs(repository, "Boston Terrier", dogNames18);
            addBreedWithDogs(repository, "Shetland Sheepdog", dogNames19);
            addBreedWithDogs(repository, "Dalmatian", dogNames20);
        };
     }

    private void addBreedWithDogs(BreedRepository repository, String breedName, List<String> dogNames) {
        // Tworzymy encję rasy
        BreedEntity breedEntity = BreedEntity.builder()
                .name(breedName)
                .build();

        // Dodajemy psy do rasy
        List<DogEntity> dogs = dogNames.stream()
                .map(name -> DogEntity.builder()
                        .name(name)
                        .breed(breedEntity)
                        .build())
                .toList();

        breedEntity.setDogs(dogs);

        // Zapisujemy rasę wraz z psami
        repository.save(breedEntity);
    }

}
