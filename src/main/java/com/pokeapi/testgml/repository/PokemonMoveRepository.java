package com.pokeapi.testgml.repository;

import com.pokeapi.testgml.entity.PokemonMove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonMoveRepository extends JpaRepository<PokemonMove, Integer> {
}