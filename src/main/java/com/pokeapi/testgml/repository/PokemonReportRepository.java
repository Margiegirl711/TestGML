package com.pokeapi.testgml.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.pokeapi.testgml.entity.PokemonReport;

import java.util.List;

@Repository
public interface PokemonReportRepository extends JpaRepository<PokemonReport, Long> {
    @Query("SELECT p FROM PokemonReport p WHERE (:pokemonName IS NULL OR p.pokemonName = :pokemonName) " +
           "AND (:typeName IS NULL OR p.typeName = :typeName) " +
           "AND (:abilityName IS NULL OR p.abilityName = :abilityName) " +
           "AND (:moveName IS NULL OR p.moveName = :moveName)")
    List<PokemonReport> findPokemonReports(@Param("pokemonName") String pokemonName,
                                           @Param("typeName") String typeName,
                                           @Param("abilityName") String abilityName,
                                           @Param("moveName") String moveName);
}