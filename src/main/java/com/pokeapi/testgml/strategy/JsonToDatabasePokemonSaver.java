package com.pokeapi.testgml.strategy;

import com.pokeapi.testgml.entity.Ability;
import com.pokeapi.testgml.entity.Move;
import com.pokeapi.testgml.entity.Pokemon;
import com.pokeapi.testgml.entity.PokemonAbility;
import com.pokeapi.testgml.entity.PokemonMove;
import com.pokeapi.testgml.entity.PokemonType;
import com.pokeapi.testgml.entity.Type;
import com.pokeapi.testgml.repository.AbilityRepository;
import com.pokeapi.testgml.repository.MoveRepository;
import com.pokeapi.testgml.repository.PokemonAbilityRepository;
import com.pokeapi.testgml.repository.PokemonEvolutionRepository;
import com.pokeapi.testgml.repository.PokemonMoveRepository;
import com.pokeapi.testgml.repository.PokemonRepository;
import com.pokeapi.testgml.repository.PokemonTypeRepository;
import com.pokeapi.testgml.repository.TypeRepository;
import com.pokeapi.testgml.service.PokemonDataExtractor;
import com.pokeapi.testgml.soap.PokemonApiClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class JsonToDatabasePokemonSaver implements PokemonDataSaver {
    private final PokemonRepository pokemonRepository;
    private final TypeRepository typeRepository;
    private final PokemonTypeRepository pokemonTypeRepository;
    private final AbilityRepository abilityRepository;
    private final PokemonAbilityRepository pokemonAbilityRepository;
    private final MoveRepository moveRepository;
    private final PokemonMoveRepository pokemonMoveRepository;
    private final PokemonEvolutionRepository pokemonEvolutionRepository;
    private final PokemonApiClient pokemonApiClient;
    private final PokemonDataExtractor pokemonDataExtractor;

    public JsonToDatabasePokemonSaver(
            PokemonRepository pokemonRepository, TypeRepository typeRepository,
            PokemonTypeRepository pokemonTypeRepository, AbilityRepository abilityRepository,
            PokemonAbilityRepository pokemonAbilityRepository, MoveRepository moveRepository,
            PokemonMoveRepository pokemonMoveRepository, PokemonEvolutionRepository pokemonEvolutionRepository,
            PokemonApiClient pokemonApiClient, PokemonDataExtractor pokemonDataExtractor) {
        this.pokemonRepository = pokemonRepository;
        this.typeRepository = typeRepository;
        this.pokemonTypeRepository = pokemonTypeRepository;
        this.abilityRepository = abilityRepository;
        this.pokemonAbilityRepository = pokemonAbilityRepository;
        this.moveRepository = moveRepository;
        this.pokemonMoveRepository = pokemonMoveRepository;
        this.pokemonEvolutionRepository = pokemonEvolutionRepository;
        this.pokemonApiClient = pokemonApiClient;
        this.pokemonDataExtractor= pokemonDataExtractor;
    }

    @Override
    @Transactional
    public void savePokemonData(Map<String, Object> apiResponse) {
        try {
            if (apiResponse == null || apiResponse.isEmpty()) {
                throw new IllegalArgumentException("La respuesta de la API es nula o vacía.");
            }

            int id = pokemonDataExtractor.extractInt(apiResponse, "id");
            String name = pokemonDataExtractor.extractString(apiResponse, "name");
            int weight = pokemonDataExtractor.extractInt(apiResponse, "weight");
            String imageUrl = pokemonDataExtractor.extractImageUrl(apiResponse);
            int height = pokemonDataExtractor.extractInt(apiResponse, "height");

            Pokemon pokemon = new Pokemon(id, name, height, weight, imageUrl, 0);
            pokemonRepository.save(pokemon);

            saveTypes(apiResponse, pokemon);
            saveAbilities(apiResponse, pokemon);
            saveMoves(apiResponse, pokemon);
            saveEvolutions(apiResponse, pokemon);

        } catch (Exception e) {
            System.err.println("Error al guardar datos del Pokémon: " + e.getMessage());
            e.printStackTrace();
        }
    }



    private void saveTypes(Map<String, Object> apiResponse, Pokemon pokemon) {
        try {
            List<Map<String, Object>> types = (List<Map<String, Object>>) apiResponse.get("types");
            if (types != null) {
                for (Map<String, Object> typeEntry : types) {
                    Map<String, String> typeInfo = (Map<String, String>) typeEntry.get("type");
                    int typeId = extractPokemonIdFromUrl(typeInfo.get("url"));
                    String typeName = typeInfo.get("name");

                    // Intentar guardar el tipo en la tabla "type"
                    Type type = new Type(typeId, typeName);
                    try {
                        typeRepository.save(type);
                    } catch (Exception e) {
                        System.err.println("El tipo con ID " + typeId + " y nombre '" + typeName + "' ya existe en la base de datos.");
                    }

                    // Intentar guardar la relación en la tabla "pokemon_type"
                    try {
                        pokemonTypeRepository.save(new PokemonType(pokemon, typeName));
                    } catch (Exception e) {
                        System.err.println("La relación entre Pokémon   y el tipo '" + typeName + "' ya existe.");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error al guardar tipos de Pokémon: " + e.getMessage());
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
	private void saveAbilities(Map<String, Object> apiResponse, Pokemon pokemon) {
        try {
            List<Map<String, Object>> abilities = (List<Map<String, Object>>) apiResponse.get("abilities");
            if (abilities != null) {
                for (Map<String, Object> abilityEntry : abilities) {
                    Map<String, String> abilityInfo = (Map<String, String>) abilityEntry.get("ability");
                    int abilityId = extractPokemonIdFromUrl(abilityInfo.get("url"));
                    Ability ability = abilityRepository.findById(abilityId).orElseGet(() -> {
                        Ability newAbility = new Ability(abilityId, abilityInfo.get("name"), "Descripción no disponible");
                        return abilityRepository.save(newAbility);
                    });
                    pokemonAbilityRepository.save(new PokemonAbility(pokemon, ability));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al guardar habilidades del Pokémon: " + e.getMessage());
        }
    }

	private void saveMoves(Map<String, Object> apiResponse, Pokemon pokemon) {
	    try {
	        List<Map<String, Object>> moves = (List<Map<String, Object>>) apiResponse.get("moves");
	        if (moves != null) {
	            for (Map<String, Object> moveEntry : moves) {
	                Map<String, String> moveInfo = (Map<String, String>) moveEntry.get("move");
	                int moveId = extractPokemonIdFromUrl(moveInfo.get("url"));

	                // Llamada a la API para obtener detalles del movimiento
	                Map<String, Object> moveData = pokemonApiClient.fetchData(moveInfo.get("url"));

	                // Extraer datos con manejo seguro de valores nulos
	                String moveName = moveInfo.getOrDefault("name", "Desconocido");
	                int power = pokemonDataExtractor.extractInt(moveData, "power");
	                int accuracy = pokemonDataExtractor.extractInt(moveData, "accuracy");
	                int timesOfUse = pokemonDataExtractor.extractInt(moveData, "pp");
	                String damageArea = pokemonDataExtractor.extractString(moveData, "damage_class", "name");
	                String description = pokemonDataExtractor.extractFlavorText(moveData);

	                // Obtener el tipo del movimiento
	                Type moveType = null;
	                if (moveData.containsKey("type")) {
	                    Map<String, String> typeInfo = (Map<String, String>) moveData.get("type");
	                    int typeId = extractPokemonIdFromUrl(typeInfo.get("url"));
	                    moveType = typeRepository.findById(typeId).orElseGet(() -> {
	                        Type newType = new Type(typeId, typeInfo.get("name"));
	                        return typeRepository.save(newType);
	                    });
	                }

	                // Crear y guardar el movimiento
	                Move move = new Move(moveId, moveName, moveType, power, accuracy, timesOfUse, damageArea, description);
	                moveRepository.save(move);
	                pokemonMoveRepository.save(new PokemonMove(pokemon, move));
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Error al guardar movimientos del Pokémon: " + e.getMessage());
	    }
	}

	private void saveEvolutions(Map<String, Object> apiResponse, Pokemon pokemon) {
	  
	        
	}


}