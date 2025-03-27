package com.pokeapi.testgml.service;

import java.util.List;

import com.pokeapi.testgml.modelSoap.PokemonReport;

public interface IPokemonReportService {
	
	public List<PokemonReport> getPokemonReports(String pokemonName, String typeName, String abilityName, String moveName);

}
