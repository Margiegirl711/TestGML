package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;



@XmlRootElement(name = "PokemonReportRequest", namespace = "http://pokeapi.com/pokemon")
@XmlType(propOrder = { "pokemonName", "typeName","abilityName", "moveName"})
public class PokemonReportRequest {

   
    private String pokemonName; 
    private String typeName;  
    private String abilityName;  
    private String moveName;

    
    @XmlElement
    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    @XmlElement
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlElement
    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    @XmlElement
    public String getMoveName() {
        return moveName;
    }

    
    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

}
