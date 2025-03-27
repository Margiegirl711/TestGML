package com.pokeapi.testgml.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;


import com.pokeapi.testgml.modelSoap.GetPokemonRequest;
import com.pokeapi.testgml.modelSoap.GetPokemonResponse;
import com.pokeapi.testgml.modelSoap.Pokemon;
import com.pokeapi.testgml.modelSoap.PokemonDetailsRequest;
import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.PokemonReport;
import com.pokeapi.testgml.modelSoap.PokemonReportRequest;
import com.pokeapi.testgml.modelSoap.PokemonReportResponse;
import com.pokeapi.testgml.modelSoap.PokemonSaveRequest;
import com.pokeapi.testgml.modelSoap.PokemonSaveResponse;
import com.pokeapi.testgml.service.IPokeApiService;
import com.pokeapi.testgml.service.PokemonReportService;

@Endpoint
public class PokemonEndpoint {
    
    private static final String NAMESPACE_URI = "http://pokeapi.com/pokemon";
    private final IPokeApiService pokeApiService; 
    private final PokemonReportService reportService;

    @Autowired
    public PokemonEndpoint(IPokeApiService pokeApiService, PokemonReportService reportService) {
        this.pokeApiService = pokeApiService;
        this.reportService = reportService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetPokemonRequest")
    @ResponsePayload
    public GetPokemonResponse getPokemons(@RequestPayload GetPokemonRequest request) {
        try {
            Map<String, Object> apiResponse = pokeApiService.getPokemons(request.getLimit(), request.getOffset());
            List<Map<String, String>> results = (List<Map<String, String>>) apiResponse.get("results");

            GetPokemonResponse response = new GetPokemonResponse();
            List<Pokemon> pokemonList = results.stream()
                    .map(p -> new Pokemon(p.get("name"), p.get("url")))
                    .toList();

            response.setPokemons(pokemonList);
            return response;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching Pokemon list", e);
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PokemonDetailsRequest")
    @ResponsePayload
    public PokemonDetailsResponse getPokemonDetails(@RequestPayload PokemonDetailsRequest request) {
        try {
            if (request.getId() > 0) {
                return pokeApiService.getPokemonDetails(request.getId());
            } else if (request.getName() != null && !request.getName().isEmpty()) {
                return pokeApiService.getPokemonDetailsByName(request.getName());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Either ID or Name must be provided");
            }
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching Pokemon details", e);
        }
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PokemonSaveRequest")
    @ResponsePayload
    public PokemonSaveResponse savePokemon(@RequestPayload PokemonSaveRequest request) {
        return pokeApiService.savePokemonDetails(request);
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PokemonReportRequest")
    @ResponsePayload
    public PokemonReportResponse getPokemonReports(@RequestPayload PokemonReportRequest request) {
    	try {
            List<PokemonReport> reports = reportService.getPokemonReports(
                request.getPokemonName(), 
                request.getTypeName(), 
                request.getAbilityName(), 
                request.getMoveName()
            );
            PokemonReportResponse response = new PokemonReportResponse();
            response.setPokemonReports(reports);
            return response;
        } catch (Exception e) {
            e.printStackTrace(); 
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing SOAP request", e);
        }
    }
}