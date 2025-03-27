package com.pokeapi.testgml.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pokeapi.testgml.modelSoap.PokemonReport;
import com.pokeapi.testgml.repository.PokemonReportRepository;

@Service
public class PokemonReportService implements IPokemonReportService {

	private final PokemonReportRepository repository;
	
	
	public PokemonReportService(PokemonReportRepository repository) {
        this.repository = repository;
    }

	
	@Override
	public List<PokemonReport> getPokemonReports(String pokemonName, String typeName, String abilityName,
			String moveName) {
		
		List<com.pokeapi.testgml.entity.PokemonReport> entityReports = 
		            repository.findPokemonReports(pokemonName, typeName, abilityName, moveName);

		 
		return entityReports.stream()
		        .map(entity -> new com.pokeapi.testgml.modelSoap.PokemonReport(
		            entity.getPokemonId(),
		            entity.getPokemonName(),
		            entity.getHeight(),
		            entity.getWeight(),
		            entity.getImageUrl(),
		            entity.getTypeName(),
		            entity.getAbilityName(),
		            entity.getMoveName(),
		            entity.getMovePower(),
		            entity.getMoveDamageArea(),
		            entity.getEvolvedPokemonId(),
		            entity.getEvolvedPokemonName()
		        )).toList(); 
	}

}
