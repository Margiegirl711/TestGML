package com.pokeapi.testgml.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.PokemonSaveRequest;
import com.pokeapi.testgml.modelSoap.PokemonSaveResponse;
import com.pokeapi.testgml.service.PokeApiService;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

    private final PokeApiService pokeApiService;

    public PokemonRestController(PokeApiService pokeApiService) {
        this.pokeApiService = pokeApiService;
    }

    /**
     * Endpoint para obtener los detalles de un Pokémon por ID o Nombre.
     */
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

    /**
     * Endpoint para guardar un Pokémon.
     */
    @PostMapping("/save")
    public ResponseEntity<PokemonSaveResponse> savePokemon(@RequestBody PokemonSaveRequest request) {
        return ResponseEntity.ok(pokeApiService.savePokemonDetails(request));
    }
    
    
}