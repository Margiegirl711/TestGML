package com.pokeapi.testgml.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.pokeapi.testgml.modelSoap.Ability;
import com.pokeapi.testgml.modelSoap.Evolution;
import com.pokeapi.testgml.modelSoap.Move;
import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.Type;
import com.pokeapi.testgml.soap.PokemonApiClient;

@Component
public class PokemonDetailsMapper {
    private final PokemonApiClient pokemonApiClient;

    public PokemonDetailsMapper(PokemonApiClient pokemonApiClient) {
        this.pokemonApiClient = pokemonApiClient;
    }

    public PokemonDetailsResponse mapToPokemonDetails(Map<String, Object> apiResponse) {
        int id = determinePokemonId(apiResponse);
        String name = (String) apiResponse.get("name");
        String imageUrl = extractImageUrl(apiResponse);
        int weight = (int) apiResponse.get("weight");
        
        List<Type> types = extractTypes(apiResponse);
        List<Ability> abilities = extractAbilities(apiResponse);
        List<Move> moves = extractMoves(apiResponse);
        List<Evolution> evolutions = extractEvolutions(apiResponse);

        return PokemonDetailsResponse.builder()
                .id(id)
                .name(name)
                .imageUrl(imageUrl)
                .weight(weight)
                .types(types)
                .abilities(abilities)
                .moves(moves)
                .evolutions(evolutions)
                .build();
    }

    private int determinePokemonId(Map<String, Object> apiResponse) {
        if (apiResponse.containsKey("id")) {
            return (int) apiResponse.get("id");
        }
        return extractPokemonIdFromForms(apiResponse);
    }

    private int extractPokemonIdFromForms(Map<String, Object> apiResponse) {
        List<Map<String, Object>> forms = (List<Map<String, Object>>) apiResponse.get("forms");
        if (forms != null && !forms.isEmpty()) {
            String url = (String) forms.get(0).get("url");
            return extractPokemonIdFromUrl(url);
        }
        return -1;
    }

    private String extractImageUrl(Map<String, Object> apiResponse) {
        return Optional.ofNullable((Map<String, String>) apiResponse.get("sprites"))
                .map(sprites -> sprites.get("front_default"))
                .orElse("No Image Available");
    }

    private List<Type> extractTypes(Map<String, Object> apiResponse) {
        return Optional.ofNullable((List<Map<String, Object>>) apiResponse.get("types"))
                .orElse(Collections.emptyList())
                .stream()
                .map(typeEntry -> {
                    Map<String, String> typeInfo = (Map<String, String>) typeEntry.get("type");
                    int typeId = extractPokemonIdFromUrl(typeInfo.get("url"));
                    return new Type(typeId, typeInfo.get("name"));
                })
                .toList();
    }

    private List<Ability> extractAbilities(Map<String, Object> apiResponse) {
        List<Map<String, Object>> abilitiesList = (List<Map<String, Object>>) apiResponse.get("abilities");
        if (abilitiesList == null) {
            return Collections.emptyList();
        }
        
        List<Ability> abilities = new ArrayList<>();
        for (Map<String, Object> abilityEntry : abilitiesList) {
            Map<String, String> abilityInfo = (Map<String, String>) abilityEntry.get("ability");
            int abilityId = extractPokemonIdFromUrl(abilityInfo.get("url"));
            String abilityName = abilityInfo.get("name");
            
            // Consultar la API para obtener la descripción en inglés
            Map<String, Object> abilityDetails = pokemonApiClient.fetchData(abilityInfo.get("url"));
            String abilityDescription = extractAbilityEffect(abilityDetails);
            
            abilities.add(new Ability(abilityId, abilityName, abilityDescription));
        }
        return abilities;
    }

    private String extractAbilityEffect(Map<String, Object> abilityDetails) {
        List<Map<String, Object>> effectEntries = (List<Map<String, Object>>) abilityDetails.get("effect_entries");
        if (effectEntries != null) {
            for (Map<String, Object> entry : effectEntries) {
                Map<String, String> language = (Map<String, String>) entry.get("language");
                if ("en".equals(language.get("name"))) {
                    return (String) entry.get("effect");
                }
            }
        }
        return "No description available";
    }

    private List<Move> extractMoves(Map<String, Object> apiResponse) {
        List<Map<String, Object>> movesList = (List<Map<String, Object>>) apiResponse.get("moves");
        if (movesList == null) {
            return Collections.emptyList();
        }
        
        List<Move> moves = new ArrayList<>();
        for (Map<String, Object> moveEntry : movesList) {
            Map<String, String> moveInfo = (Map<String, String>) moveEntry.get("move");
            int moveId = extractPokemonIdFromUrl(moveInfo.get("url"));
            String moveName = moveInfo.get("name");
            
            Map<String, Object> moveDetails = pokemonApiClient.fetchData(moveInfo.get("url"));
            int power = moveDetails.get("power") != null ? (int) moveDetails.get("power") : 0;
            int accuracy = moveDetails.get("accuracy") != null ? (int) moveDetails.get("accuracy") : 0;
            String moveDescription = extractMoveEffect(moveDetails);
            
            moves.add(new Move(moveId, moveName, power, accuracy, moveDescription));
        }
        return moves;
    }

    private String extractMoveEffect(Map<String, Object> moveDetails) {
        List<Map<String, Object>> effectEntries = (List<Map<String, Object>>) moveDetails.get("effect_entries");
        if (effectEntries != null) {
            for (Map<String, Object> entry : effectEntries) {
                Map<String, String> language = (Map<String, String>) entry.get("language");
                if ("en".equals(language.get("name"))) {
                    return (String) entry.get("short_effect");
                }
            }
        }
        return "No description available";
    }

    private List<Evolution> extractEvolutions(Map<String, Object> apiResponse) {
        List<Evolution> evolutions = new ArrayList<>();
        try {
            int id = determinePokemonId(apiResponse);
            String evolutionUrl = "https://pokeapi.co/api/v2/evolution-chain/" + id + "/";
            Map<String, Object> evolutionData = pokemonApiClient.fetchData(evolutionUrl);

            if (evolutionData == null || evolutionData.isEmpty()) {
                System.out.println(" No se encontraron datos de evolución para el Pokémon con ID: " + id);
            }

            Map<String, Object> chain = (Map<String, Object>) evolutionData.get("chain");
            if (chain != null) {
                extractEvolutionChain(chain, evolutions, -1);
            } else {
                System.out.println("⚠ Advertencia: No se encontró una cadena de evolución en la API.");
            }
        } catch (Exception e) {
            System.err.println(" Error al extraer evoluciones: " + e.getMessage());
            e.printStackTrace();
        }
        return evolutions;
    }

    private void extractEvolutionChain(Map<String, Object> chain, List<Evolution> evolutions, int baseId) {
        try {
            if (chain == null || chain.isEmpty()) {
            	System.out.println("️ La cadena de evolución está vacía o es nula.");
            }

            int id = extractPokemonIdFromUrl((String) ((Map<String, Object>) chain.get("species")).get("url"));
            String evolutionTrigger = "level-up";
            int levelRequired = 0;

            List<Map<String, Object>> evolutionDetails = (List<Map<String, Object>>) chain.get("evolution_details");
            if (evolutionDetails != null && !evolutionDetails.isEmpty()) {
                Object levelObj = evolutionDetails.get(0).get("min_level");
                if (levelObj instanceof Integer) {
                    levelRequired = (int) levelObj;
                } else {
                    System.out.println(" Advertencia: No se encontró un nivel de evolución válido, se asignará 0.");
                }
            }

            if (baseId != -1) {
                evolutions.add(new Evolution(baseId, id, evolutionTrigger, levelRequired));
            }

            List<Map<String, Object>> evolvesTo = (List<Map<String, Object>>) chain.get("evolves_to");
            if (evolvesTo != null && !evolvesTo.isEmpty()) {
                for (Map<String, Object> evolution : evolvesTo) {
                    extractEvolutionChain(evolution, evolutions, id);
                }
            } else {
                System.out.println(" Este Pokémon no evoluciona más.");
            }
        } catch (Exception e) {
            System.err.println(" Error al procesar la cadena de evolución: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int extractPokemonIdFromUrl(String url) {
        try {
            String[] parts = url.split("/");
            return Integer.parseInt(parts[parts.length - 1]);
        } catch (NumberFormatException e) {
            System.err.println("Error extracting Pokemon ID from URL: " + url);
            return -1;
        }
    }
}
