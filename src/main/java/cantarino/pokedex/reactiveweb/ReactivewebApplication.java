package cantarino.pokedex.reactiveweb;

import cantarino.pokedex.reactiveweb.model.Pokemon;
import cantarino.pokedex.reactiveweb.repository.PokemonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class ReactivewebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivewebApplication.class, args);
	}


		@Bean
		CommandLineRunner init (ReactiveMongoOperations operations, PokemonRepository repository) {
			return args -> {
				Flux<Pokemon> pokemonFlux = Flux.just(
						new Pokemon(null, "Electrode", "bomba", "OverGrow", 6.09 , "../assets/100.png"),
						new Pokemon(null, "Pigeon ", "Ave", "Blaze", 90.05 ,  "../assets/108.png"),
						new Pokemon(null, "Pigeout", "Minhoca", "Poeira do Escudo", 2.09 , "../assets/9.png"),
						new Pokemon(null, "Squirtle", "Marisco", "Torrente", 6.09, "../assets/7.png"))
						.flatMap(repository::save);

				pokemonFlux
						.thenMany(repository.findAll())
						.subscribe(System.out::println);
			};
		}

	}


