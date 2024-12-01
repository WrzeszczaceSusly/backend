package org.example.schroniskodlapsow;

import org.example.schroniskodlapsow.entity.breed.BreedEntity;
import org.example.schroniskodlapsow.entity.user.MyUser;
import org.example.schroniskodlapsow.repository.breed.BreedRepository;
import org.example.schroniskodlapsow.repository.user.MyUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


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

            //breeds
            BreedEntity labradorRetriever = BreedEntity.builder().name("Labrador Retriever").build();
            repository.save(labradorRetriever);

            BreedEntity germanShepherd = BreedEntity.builder().name("German Shepherd").build();
            repository.save(germanShepherd);

            BreedEntity goldenRetriever = BreedEntity.builder().name("Golden Retriever").build();
            repository.save(goldenRetriever);

            BreedEntity bulldog = BreedEntity.builder().name("Bulldog").build();
            repository.save(bulldog);

            BreedEntity beagle = BreedEntity.builder().name("Beagle").build();
            repository.save(beagle);

            BreedEntity poodle = BreedEntity.builder().name("Poodle").build();
            repository.save(poodle);

            BreedEntity boxer = BreedEntity.builder().name("Boxer").build();
            repository.save(boxer);

            BreedEntity dachshund = BreedEntity.builder().name("Dachshund").build();
            repository.save(dachshund);

            BreedEntity siberianHusky = BreedEntity.builder().name("Siberian Husky").build();
            repository.save(siberianHusky);

            BreedEntity greatDane = BreedEntity.builder().name("Great Dane").build();
            repository.save(greatDane);

            BreedEntity shihTzu = BreedEntity.builder().name("Shih Tzu").build();
            repository.save(shihTzu);

            BreedEntity rottweiler = BreedEntity.builder().name("Rottweiler").build();
            repository.save(rottweiler);

            BreedEntity chihuahua = BreedEntity.builder().name("Chihuahua").build();
            repository.save(chihuahua);

            BreedEntity borderCollie = BreedEntity.builder().name("Border Collie").build();
            repository.save(borderCollie);

            BreedEntity maltese = BreedEntity.builder().name("Maltese").build();
            repository.save(maltese);

            BreedEntity yorkshireTerrier = BreedEntity.builder().name("Yorkshire Terrier").build();
            repository.save(yorkshireTerrier);

            BreedEntity pembrokeWelshCorgi = BreedEntity.builder().name("Pembroke Welsh Corgi").build();
            repository.save(pembrokeWelshCorgi);

            BreedEntity bostonTerrier = BreedEntity.builder().name("Boston Terrier").build();
            repository.save(bostonTerrier);

            BreedEntity shetlandSheepdog = BreedEntity.builder().name("Shetland Sheepdog").build();
            repository.save(shetlandSheepdog);

            BreedEntity dalmatian = BreedEntity.builder().name("Dalmatian").build();
            repository.save(dalmatian);
        };
    }
}
