package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import java.util.List;

@XmlRootElement(name = "PokemonReportResponse", namespace = "http://pokeapi.com/pokemon")
@XmlType(propOrder = { "pokemonReports" })
public class PokemonReportResponse {

    private List<PokemonReport> pokemonReports;

    @XmlElementWrapper(name = "pokemonReports") 
    @XmlElement(name = "PokemonReport")  
    public List<PokemonReport> getPokemonReports() {
        return pokemonReports;
    }

    public void setPokemonReports(List<PokemonReport> pokemonReports) {
        this.pokemonReports = pokemonReports;
    }
}


