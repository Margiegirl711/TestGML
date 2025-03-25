package com.pokeapi.testgml.factory;


import com.pokeapi.testgml.strategy.JsonToDatabasePokemonSaver;
import com.pokeapi.testgml.strategy.PokemonDataSaver;
import org.springframework.stereotype.Component;

@Component
public class PokemonDataSaverFactory {
    private final JsonToDatabasePokemonSaver jsonToDatabasePokemonSaver;

    public PokemonDataSaverFactory(JsonToDatabasePokemonSaver jsonToDatabasePokemonSaver) {
        this.jsonToDatabasePokemonSaver = jsonToDatabasePokemonSaver;
    }

    public PokemonDataSaver getSaver(String type) {
        if ("database".equalsIgnoreCase(type)) {
            return jsonToDatabasePokemonSaver;
        }
        throw new IllegalArgumentException("Invalid saver type");
    }
}
