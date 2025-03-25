package com.pokeapi.testgml.service;

import java.util.Map;

public interface PokemonDataExtractor {
    int extractInt(Map<String, Object> data, String key);
    String extractString(Map<String, Object> data, String key);
    String extractString(Map<String, Object> data, String key, String subKey);
    int extractPokemonIdFromUrl(String url);
    String extractFlavorText(Map<String, Object> moveData);
    String extractImageUrl(Map<String, Object> apiResponse);
}

