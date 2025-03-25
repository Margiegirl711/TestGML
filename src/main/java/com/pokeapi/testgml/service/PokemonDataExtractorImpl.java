package com.pokeapi.testgml.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PokemonDataExtractorImpl implements PokemonDataExtractor {

    @Override
    public int extractInt(Map<String, Object> data, String key) {
        return Optional.ofNullable(data.get(key))
                .filter(Number.class::isInstance)
                .map(Number.class::cast)
                .map(Number::intValue)
                .orElse(0);
    }

    @Override
    public String extractString(Map<String, Object> data, String key) {
        return Optional.ofNullable(data.get(key))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .orElse("Desconocido");
    }

    @Override
    public String extractString(Map<String, Object> data, String key, String subKey) {
        return Optional.ofNullable(data.get(key))
                .filter(Map.class::isInstance)
                .map(map -> ((Map<?, ?>) map).get(subKey))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .orElse("Desconocido");
    }

    @Override
    public int extractPokemonIdFromUrl(String url) {
        try {
            String[] parts = url.split("/");
            return Integer.parseInt(parts[parts.length - 1]);
        } catch (Exception e) {
            System.err.println("Error al extraer ID de la URL: " + url);
            return -1;
        }
    }

    @Override
    public String extractFlavorText(Map<String, Object> moveData) {
        return (String) Optional.ofNullable(moveData.get("flavor_text_entries"))
                .filter(List.class::isInstance)
                .map(List.class::cast)
                .flatMap(entries -> entries.stream()
                        .filter(entry -> {
                            Map<String, String> language = (Map<String, String>) ((Map<?, ?>) entry).get("language");
                            return "en".equals(language.get("name"));
                        })
                        .findFirst()
                        .map(entry -> (String) ((Map<?, ?>) entry).get("flavor_text")))
                .orElse("Descripción no disponible");
    }

	@Override
	public String extractImageUrl(Map<String, Object> apiResponse) {
		 return Optional.ofNullable((Map<String, String>) apiResponse.get("sprites"))
	                .map(sprites -> sprites.get("front_default"))
	                .orElse("No Image Available");
	}
}
