package com.pokeapi.testgml.strategy;

import java.util.Map;

public interface PokemonDataSaver {
    void savePokemonData(Map<String, Object> apiResponse);
}