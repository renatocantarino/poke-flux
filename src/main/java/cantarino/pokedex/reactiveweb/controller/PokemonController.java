package cantarino.pokedex.reactiveweb.controller;


import cantarino.pokedex.reactiveweb.model.Pokemon;
import cantarino.pokedex.reactiveweb.model.PokemonEvent;
import cantarino.pokedex.reactiveweb.repository.PokemonRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;


@CrossOrigin
@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private PokemonRepository repository;
    public PokemonController(PokemonRepository repository)
    {
        this.repository = repository;
    }

    @GetMapping
    public Flux<Pokemon> getAll()
    {
        return repository.findAll();
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val ->
                        new PokemonEvent(val, "Product Event")
                );
    }


}
