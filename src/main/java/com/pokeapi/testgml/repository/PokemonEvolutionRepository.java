package com.pokeapi.testgml.repository;

import com.pokeapi.testgml.entity.PokemonEvolution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonEvolutionRepository extends JpaRepository<PokemonEvolution, Integer> {
}