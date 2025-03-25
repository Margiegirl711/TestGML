package com.pokeapi.testgml.repository;

import com.pokeapi.testgml.entity.PokemonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonType, Integer> {
}
