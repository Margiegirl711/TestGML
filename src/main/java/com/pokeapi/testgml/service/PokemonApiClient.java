package com.pokeapi.testgml.service;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PokemonApiClient {
	private final WebClient webClient;

  
    
    public PokemonApiClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Map<String, Object> fetchData(String url) {
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); 
    }
}
