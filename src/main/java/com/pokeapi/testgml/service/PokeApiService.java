package com.pokeapi.testgml.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.pokeapi.testgml.mapper.PokemonDetailsMapper;
import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.PokemonSaveRequest;
import com.pokeapi.testgml.modelSoap.PokemonSaveResponse;
import com.pokeapi.testgml.strategy.PokemonDataSaver;

@Service
public class PokeApiService implements IPokeApiService{
	
	    private final PokemonApiClient pokemonApiClient;
	    private final PokemonDetailsMapper pokemonDetailsMapper;
	    private final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon";
	    private final PokemonDataSaver pokemonDataSaver;
	   
	    public PokeApiService(PokemonApiClient pokemonApiClient, PokemonDetailsMapper pokemonDetailsMapper, PokemonDataSaver pokemonDataSaver) {
	        this.pokemonApiClient = pokemonApiClient;
	        this.pokemonDetailsMapper = pokemonDetailsMapper;
	        this.pokemonDataSaver = pokemonDataSaver;
	    }

	    @Override
	    public Map<String, Object> getPokemons(int limit, int offset) {
	        try {
	            String url = UriComponentsBuilder.fromHttpUrl(POKEAPI_URL)
	                    .queryParam("limit", limit)
	                    .queryParam("offset", offset)
	                    .toUriString();
	            
	            System.out.println("Fetching from URL: " + url);
	            
	            return pokemonApiClient.fetchData(url);
	        } catch (RestClientException e) {
	            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Error fetching Pokémon data", e);
	        }
	    }

	    @Override
	    public PokemonDetailsResponse getPokemonDetails(int id) {
	        try {
	            Map<String, Object> apiResponse = pokemonApiClient.fetchData(POKEAPI_URL + "/" + id + "/");
	            return pokemonDetailsMapper.mapToPokemonDetails(apiResponse);
	        } catch (RestClientException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pokémon not found", e);
	        }
	    }

		@Override
		public PokemonDetailsResponse getPokemonDetailsByName(String name) {
			try {
		        if (name == null || name.isEmpty()) {
		            throw new IllegalArgumentException("El nombre del Pokémon no puede estar vacío.");
		        }

		        Map<String, Object> apiResponse = pokemonApiClient.fetchData(POKEAPI_URL + "/" + name.toLowerCase() + "/");

		        return pokemonDetailsMapper.mapToPokemonDetails(apiResponse);
		    } catch (Exception e) {
		        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró el Pokémon con nombre: " + name, e);
		    }
		}

		@Override
		public PokemonSaveResponse savePokemonDetails(PokemonSaveRequest request) {
		    PokemonSaveResponse response = new PokemonSaveResponse();
		    try {
		    	if (request == null || (request.getId() == null && (request.getName() == null || request.getName().isEmpty()))) {
		                response.setMessage("Debe proporcionar un ID o un nombre válido para el Pokémon.");
		                return response;
		        }
		    	String identifier = (request.getId() != null) ? String.valueOf(request.getId()) : request.getName().toLowerCase();
		        String apiUrl = "https://pokeapi.co/api/v2/pokemon/" + identifier;Map<String, Object> apiResponse = pokemonApiClient.fetchData(apiUrl);

		        if (apiResponse == null || apiResponse.isEmpty()) {
		            response.setId(request.getId() );
		            response.setMessage("No se encontró información para el Pokémon: " + request.getName());
		            return response;
		        }

		        pokemonDataSaver.savePokemonData(apiResponse);
		        response.setId(request.getId());
		        response.setMessage("El Pokémon " + request.getName() + " ha sido guardado exitosamente.");
		        
		    } catch (Exception e) {
		        response.setId(request.getId());
		        response.setMessage("Error al guardar los datos del Pokémon: " + e.getMessage());
		        e.printStackTrace();
		    }
		    return response;
		}

}
