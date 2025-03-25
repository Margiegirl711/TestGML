package com.pokeapi.testgml.service;

import java.util.Map;

import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.PokemonSaveRequest;
import com.pokeapi.testgml.modelSoap.PokemonSaveResponse;

public interface IPokeApiService {
    Map<String, Object> getPokemons(int limit, int offset);
    
    PokemonDetailsResponse getPokemonDetails(int id);
    
    PokemonDetailsResponse getPokemonDetailsByName(String name);
    
    PokemonSaveResponse savePokemonDetails(PokemonSaveRequest request);
}