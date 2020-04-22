package cantarino.pokedex.reactiveweb.repository;

import cantarino.pokedex.reactiveweb.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokemonRepository extends ReactiveMongoRepository<Pokemon , String> {
}
