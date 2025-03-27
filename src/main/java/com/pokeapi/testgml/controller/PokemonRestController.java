package com.pokeapi.testgml.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.PokemonReport;
import com.pokeapi.testgml.modelSoap.PokemonSaveRequest;
import com.pokeapi.testgml.modelSoap.PokemonSaveResponse;
import com.pokeapi.testgml.service.PokeApiService;
import com.pokeapi.testgml.service.PokemonReportService;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

    private final PokeApiService pokeApiService;
    private final PokemonReportService reportService;

    public PokemonRestController(PokeApiService pokeApiService, PokemonReportService reportService) {
        this.pokeApiService = pokeApiService;
        this.reportService  = reportService;
    }


    @GetMapping("/details")
    public ResponseEntity<PokemonDetailsResponse> getPokemonDetails(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name) {
        try {
            if (id != null && id > 0) {
                return ResponseEntity.ok(pokeApiService.getPokemonDetails(id));
            } else if (name != null && !name.isEmpty()) {
                return ResponseEntity.ok(pokeApiService.getPokemonDetailsByName(name));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null); // Puedes personalizar mejor la respuesta
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PostMapping("/save")
    public ResponseEntity<PokemonSaveResponse> savePokemon(@RequestBody PokemonSaveRequest request) {
        return ResponseEntity.ok(pokeApiService.savePokemonDetails(request));
    }
    
    @GetMapping("/reports")
    public ResponseEntity<List<PokemonReport>> getPokemonReports(
            @RequestParam(required = false) String pokemonName,
            @RequestParam(required = false) String typeName,
            @RequestParam(required = false) String abilityName,
            @RequestParam(required = false) String moveName) {
        return ResponseEntity.ok(reportService.getPokemonReports(pokemonName, typeName, abilityName, moveName));
    }
    
    
}