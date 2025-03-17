package com.pokeapi.testgml.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pokeapi.testgml.modelSoap.GetPokemonRequest;
import com.pokeapi.testgml.modelSoap.GetPokemonResponse;
import com.pokeapi.testgml.modelSoap.Pokemon;
import com.pokeapi.testgml.service.PokeApiService;


@Endpoint
public class PokemonEndpoint {
	
	 private static final String NAMESPACE_URI = "http://pokeapi.com/pokemon";
	    private final PokeApiService pokeApiService;

	    @Autowired
	    public PokemonEndpoint(PokeApiService pokeApiService) {
	        this.pokeApiService = pokeApiService;
	    }

	    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
	    @ResponsePayload
	    public GetPokemonResponse getPokemons(@RequestPayload GetPokemonRequest request) {
	        Map<String, Object> apiResponse = pokeApiService.getPokemons(request.getLimit(), request.getOffset());
	        List<Map<String, String>> results = (List<Map<String, String>>) apiResponse.get("results");

	        GetPokemonResponse response = new GetPokemonResponse();
	        List<Pokemon> pokemonList = results.stream()
	                .map(p -> new Pokemon(p.get("name"), p.get("url")))
	                .toList();

	        response.setPokemons(pokemonList);
	        return response;
	    }

}
