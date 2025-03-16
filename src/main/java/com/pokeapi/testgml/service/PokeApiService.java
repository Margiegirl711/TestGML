package com.pokeapi.testgml.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PokeApiService {
	
	 private final RestTemplate restTemplate;
	    private static final String POKEAPI_URL = "https://pokeapi.co/api/v2/pokemon";

	    public PokeApiService(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

	    public Map<String, Object> getPokemons(int limit, int offset) {
	        String url = UriComponentsBuilder.fromHttpUrl(POKEAPI_URL)
	                .queryParam("limit", limit)
	                .queryParam("offset", offset)
	                .toUriString();
	        return restTemplate.getForObject(url, Map.class);
	    }

}
