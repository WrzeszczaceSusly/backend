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

            // Dodajemy dane o rasach i psach:
            addBreedWithDogs(breedRepository, dogRepository,
                    "Labrador Retriever", List.of("Burek", "Reksio"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "German Shepherd", List.of("Azor", "Max"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Golden Retriever", List.of("Ciapek", "Rocky"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Bulldog", List.of("Bella", "Molly"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Beagle", List.of("Daisy", "Charlie"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Poodle", List.of("Luna", "Cooper"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Boxer", List.of("Buddy", "Bailey"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Dachshund", List.of("Rosie", "Toby"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Siberian Husky", List.of("Roxy", "Sammy"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Great Dane", List.of("Zoe", "Simba"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Shih Tzu", List.of("Duke", "Sadie"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Rottweiler", List.of("Milo", "Jack"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Chihuahua", List.of("Lilly", "Oscar"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Border Collie", List.of("Bruno", "Ruby"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Maltese", List.of("Shadow", "Lucy"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Yorkshire Terrier", List.of("Piper", "Jake"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Pembroke Welsh Corgi", List.of("Ace", "Oliver"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Boston Terrier", List.of("Finn", "Ginger"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Shetland Sheepdog", List.of("Hunter", "Sophie"));
            addBreedWithDogs(breedRepository, dogRepository,
                    "Dalmatian", List.of("Nala", "Benji"));
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
            breed = breedRepository.saveAndFlush(breed);
        }

        for (String dogName : dogNames) {
            // Budujemy ścieżkę do pliku z obrazem
            String breedString = breedName.replace(" ", "") + "/" + dogName + ".png";
            Path imagePath = Path.of("src/main/resources/static/" + breedString);
            byte[] imageData = Files.readAllBytes(imagePath);

            // Tworzymy obiekt DogEntity z danymi właściwymi dla danego psa
            DogEntity dog = createDogEntityWithDetails(breed, dogName, breedName, imageData);

            // Zapis w bazie
            dogRepository.save(dog);
        }
    }

    /**
     * Metoda tworzy DogEntity z przypisaniem konkretnych danych
     * (wiek, płeć, rozmiar, waga) oraz dodatkowych atrybutów,
     * w zależności od rasy i imienia psa.
     */
    private DogEntity createDogEntityWithDetails(
            BreedEntity breed,
            String dogName,
            String breedName,
            byte[] imageData
    ) {
        // Domyślne wartości (nadpisywane w if-else):
        int age = 1;
        String sex = "male";
        String size = "medium";
        double weight = 10.0;

        // Nowe atrybuty
        String description = "Kocha ludzi i długie spacery.";
        String color = "brown";
        boolean vaccinated = true;
        boolean sterilized = false;
        boolean microchipped = false;
        boolean friendlyWithKids = true;
        boolean friendlyWithAnimals = true;

        if (breedName.equals("Labrador Retriever")) {
            if (dogName.equals("Burek")) {
                age = 3;
                sex = "male";
                size = "large";
                weight = 30.0;

                description = "Burek to pełen energii labrador, uwielbia aportować i pływać.";
                color = "chocolate";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Reksio")) {
                age = 4;
                sex = "male";
                size = "large";
                weight = 28.0;

                description = "Reksio jest spokojnym i lojalnym labradorem, zawsze chętnym do zabawy.";
                color = "golden";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("German Shepherd")) {
            if (dogName.equals("Azor")) {
                age = 2;
                sex = "male";
                size = "large";
                weight = 35.0;

                description = "Azor to inteligentny owczarek niemiecki, uwielbia pracować i uczyć się sztuczek.";
                color = "black and tan";
                vaccinated = true;
                sterilized = false;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = false; // np. może być ostrożny wobec innych psów

            } else if (dogName.equals("Max")) {
                age = 5;
                sex = "male";
                size = "large";
                weight = 32.0;

                description = "Max jest opanowanym i wiernym owczarkiem, doskonale sprawdza się jako pies rodzinny.";
                color = "sable";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Golden Retriever")) {
            if (dogName.equals("Ciapek")) {
                age = 2;
                sex = "male";
                size = "large";
                weight = 29.0;

                description = "Ciapek to radosny golden, który kocha towarzystwo ludzi i długie spacery w lesie.";
                color = "golden";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Rocky")) {
                age = 5;
                sex = "male";
                size = "large";
                weight = 34.0;

                description = "Rocky jest łagodny i mądry, świetnie sprawdza się w towarzystwie dzieci.";
                color = "cream";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Bulldog")) {
            if (dogName.equals("Bella")) {
                age = 3;
                sex = "female";
                size = "medium";
                weight = 22.0;

                description = "Bella to urocza buldożka, lubi leniwe popołudnia i przytulanie na kanapie.";
                color = "white and brown";
                vaccinated = true;
                sterilized = true;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Molly")) {
                age = 4;
                sex = "female";
                size = "medium";
                weight = 20.0;

                description = "Molly jest spokojna, ale nie odmówi spaceru ani zabawy z piłką.";
                color = "brindle";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Beagle")) {
            if (dogName.equals("Daisy")) {
                age = 2;
                sex = "female";
                size = "small";
                weight = 10.0;

                description = "Daisy to wesoła beagle, która nie może się oprzeć tropieniu zapachów.";
                color = "tricolor";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Charlie")) {
                age = 3;
                sex = "male";
                size = "small";
                weight = 12.0;

                description = "Charlie jest energiczny i dociekliwy, uwielbia nowe wyzwania i długie spacery.";
                color = "tricolor";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Poodle")) {
            if (dogName.equals("Luna")) {
                age = 3;
                sex = "female";
                size = "medium";
                weight = 14.0;

                description = "Luna to elegancka pudlica, bardzo towarzyska i chętna do nauki nowych trików.";
                color = "white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Cooper")) {
                age = 4;
                sex = "male";
                size = "medium";
                weight = 16.0;

                description = "Cooper jest spokojnym psem, uwielbia wspólne zabawy i kontakt z człowiekiem.";
                color = "apricot";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Boxer")) {
            if (dogName.equals("Buddy")) {
                age = 5;
                sex = "male";
                size = "large";
                weight = 29.0;

                description = "Buddy to pełen energii bokser, zawsze chętny do zabawy i biegania.";
                color = "fawn";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = false;

            } else if (dogName.equals("Bailey")) {
                age = 3;
                sex = "female";
                size = "large";
                weight = 27.0;

                description = "Bailey jest czułą bokserką, uwielbia przytulanie i zabawę w domu.";
                color = "brindle";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Dachshund")) {
            if (dogName.equals("Rosie")) {
                age = 2;
                sex = "female";
                size = "small";
                weight = 7.0;

                description = "Rosie to wesoła jamniczka, uwielbia długie drzemki w ciepłym miejscu.";
                color = "black and tan";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Toby")) {
                age = 5;
                sex = "male";
                size = "small";
                weight = 8.0;

                description = "Toby jest inteligentny i uwielbia eksplorować zakamarki domu oraz ogród.";
                color = "chocolate";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = false;
            }

        } else if (breedName.equals("Siberian Husky")) {
            if (dogName.equals("Roxy")) {
                age = 3;
                sex = "female";
                size = "large";
                weight = 23.0;

                description = "Roxy to piękna husky z niebieskimi oczami, kocha ruch i zimowe wyprawy.";
                color = "gray and white";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Sammy")) {
                age = 4;
                sex = "male";
                size = "large";
                weight = 26.0;

                description = "Sammy potrzebuje aktywnego opiekuna; jest bardzo towarzyski i wesoły.";
                color = "black and white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Great Dane")) {
            if (dogName.equals("Zoe")) {
                age = 6;
                sex = "female";
                size = "large";
                weight = 40.0;

                description = "Zoe to dostojna dog niemiecka, bardzo łagodna i przyjazna wobec ludzi.";
                color = "blue";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Simba")) {
                age = 2;
                sex = "male";
                size = "large";
                weight = 45.0;

                description = "Simba jest jeszcze młody, ale już bardzo duży, kocha biegać po ogrodzie.";
                color = "fawn";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Shih Tzu")) {
            if (dogName.equals("Duke")) {
                age = 3;
                sex = "male";
                size = "small";
                weight = 6.0;

                description = "Duke to elegancki shih tzu, uwielbia być czesany i rozpieszczany.";
                color = "white and gray";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Sadie")) {
                age = 5;
                sex = "female";
                size = "small";
                weight = 5.0;

                description = "Sadie jest łagodna i spokojna, lubi spędzać czas na kolanach opiekuna.";
                color = "golden and white";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Rottweiler")) {
            if (dogName.equals("Milo")) {
                age = 2;
                sex = "male";
                size = "large";
                weight = 35.0;

                description = "Milo to silny, a zarazem bardzo oddany rottweiler, lubi aktywne spędzanie czasu.";
                color = "black and tan";
                vaccinated = true;
                sterilized = false;
                microchipped = true;
                friendlyWithKids = false; // np. niepewny wobec małych dzieci
                friendlyWithAnimals = false;

            } else if (dogName.equals("Jack")) {
                age = 4;
                sex = "male";
                size = "large";
                weight = 38.0;

                description = "Jack jest zrównoważony i posłuszny, potrzebuje doświadczonego opiekuna.";
                color = "black and tan";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = false;
            }

        } else if (breedName.equals("Chihuahua")) {
            if (dogName.equals("Lilly")) {
                age = 3;
                sex = "female";
                size = "small";
                weight = 3.0;

                description = "Lilly to niewielka suczka, ciekawska i pełna temperamentu, kocha tulenie.";
                color = "cream";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = false; // niektóre chihuahuy bywają nerwowe przy dzieciach
                friendlyWithAnimals = true;

            } else if (dogName.equals("Oscar")) {
                age = 5;
                sex = "male";
                size = "small";
                weight = 4.0;

                description = "Oscar jest pewny siebie, uwielbia być w centrum uwagi i domaga się pieszczot.";
                color = "black";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = false;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Border Collie")) {
            if (dogName.equals("Bruno")) {
                age = 4;
                sex = "male";
                size = "medium";
                weight = 18.0;

                description = "Bruno jest bardzo aktywny i inteligentny, potrzebuje dużo ruchu i zadań.";
                color = "black and white";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Ruby")) {
                age = 2;
                sex = "female";
                size = "medium";
                weight = 16.0;

                description = "Ruby to energiczna collie, lubi agility i zabawy z piłką.";
                color = "black and white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Maltese")) {
            if (dogName.equals("Shadow")) {
                age = 4;
                sex = "male";
                size = "small";
                weight = 4.0;

                description = "Shadow jest delikatny i łagodny, dobrze czuje się w spokojnym otoczeniu.";
                color = "white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Lucy")) {
                age = 1;
                sex = "female";
                size = "small";
                weight = 3.0;

                description = "Lucy to maleńka, wesoła suczka, która uwielbia głaskanie i zabawy z zabawkami.";
                color = "white";
                vaccinated = true;
                sterilized = false;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Yorkshire Terrier")) {
            if (dogName.equals("Piper")) {
                age = 2;
                sex = "female";
                size = "small";
                weight = 4.0;

                description = "Piper to rezolutna i odważna suczka, lubi odkrywać nowe miejsca.";
                color = "steel blue and tan";
                vaccinated = true;
                sterilized = true;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Jake")) {
                age = 5;
                sex = "male";
                size = "small";
                weight = 5.0;

                description = "Jake jest spokojniejszy, uwielbia towarzyszyć opiekunowi w codziennych czynnościach.";
                color = "gray and tan";
                vaccinated = true;
                sterilized = false;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Pembroke Welsh Corgi")) {
            if (dogName.equals("Ace")) {
                age = 4;
                sex = "male";
                size = "medium";
                weight = 12.0;

                description = "Ace to przyjazny corgi, który kocha zabawy i bieganie za piłką.";
                color = "red and white";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Oliver")) {
                age = 2;
                sex = "male";
                size = "medium";
                weight = 10.0;

                description = "Oliver jest bystrym i towarzyskim psiakiem, szybko nawiązuje relacje.";
                color = "sable and white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Boston Terrier")) {
            if (dogName.equals("Finn")) {
                age = 3;
                sex = "male";
                size = "small";
                weight = 7.0;

                description = "Finn jest wesoły i ciekawski, uwielbia bawić się w aportowanie.";
                color = "black and white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Ginger")) {
                age = 4;
                sex = "female";
                size = "small";
                weight = 6.0;

                description = "Ginger jest pełna uroku, najbardziej lubi wylegiwanie się na poduszce.";
                color = "seal and white";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Shetland Sheepdog")) {
            if (dogName.equals("Hunter")) {
                age = 5;
                sex = "male";
                size = "medium";
                weight = 12.0;

                description = "Hunter to bystry sheltie, zawsze chętny do nauki nowych komend i sztuczek.";
                color = "sable and white";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Sophie")) {
                age = 3;
                sex = "female";
                size = "medium";
                weight = 10.0;

                description = "Sophie jest czuła i kochana, lubi spędzać czas z całą rodziną.";
                color = "blue merle and white";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }

        } else if (breedName.equals("Dalmatian")) {
            if (dogName.equals("Nala")) {
                age = 2;
                sex = "female";
                size = "large";
                weight = 25.0;

                description = "Nala to wesoła dalmatynka, pełna energii i chętna do zabawy w gonitwy.";
                color = "white with black spots";
                vaccinated = true;
                sterilized = false;
                microchipped = false;
                friendlyWithKids = true;
                friendlyWithAnimals = true;

            } else if (dogName.equals("Benji")) {
                age = 4;
                sex = "male";
                size = "large";
                weight = 27.0;

                description = "Benji uwielbia biegać i odkrywać nowe miejsca, jest psem o wielkim sercu.";
                color = "white with liver spots";
                vaccinated = true;
                sterilized = true;
                microchipped = true;
                friendlyWithKids = true;
                friendlyWithAnimals = true;
            }
        }

        // Tworzymy obiekt z danymi wypełnionymi z powyższych if-else
        return DogEntity.builder()
                .name(dogName)
                .breed(breed)
                .image(imageData)
                .age(age)
                .sex(sex)
                .size(size)
                .weight(weight)

                // Nowe pola
                .description(description)
                .color(color)
                .vaccinated(vaccinated)
                .sterilized(sterilized)
                .microchipped(microchipped)
                .friendlyWithKids(friendlyWithKids)
                .friendlyWithAnimals(friendlyWithAnimals)

                .build();
    }

}
