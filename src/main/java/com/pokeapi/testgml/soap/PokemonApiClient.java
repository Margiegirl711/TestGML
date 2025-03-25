package com.pokeapi.testgml.soap;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokemonApiClient {
    private final RestTemplate restTemplate;

    public PokemonApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Map<String, Object> fetchData(String url) {
        return restTemplate.getForObject(url, Map.class);
    }
    
}
