package com.pokeapi.testgml.service.configuration;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetPokemonResponse", namespace = "http://pokeapi.com/pokemon")
@XmlType(namespace = "http://pokeapi.com/pokemon")
public class GetPokemonResponse {
	
	 private List<Pokemon> pokemons;

	    @XmlElement(name = "pokemon")
	    public List<Pokemon> getPokemons() {
	        return pokemons;
	    }

	    public void setPokemons(List<Pokemon> pokemons) {
	        this.pokemons = pokemons;
	    }
}
