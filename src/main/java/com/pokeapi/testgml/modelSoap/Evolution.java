package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "evolution")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "basePokemonId", "evolvedPokemonId", "evolutionTrigger", "levelRequired" })
public class Evolution {

    @XmlElement(name = "base_pokemon_id", required = true)
    private Integer basePokemonId;

    @XmlElement(name = "evolved_pokemon_id", required = true)
    private Integer evolvedPokemonId;

    @XmlElement(name = "evolution_trigger", required = true)
    private String evolutionTrigger;

    @XmlElement(name = "level_required", required = false)
    private Integer levelRequired;

    public Evolution() {}

    public Evolution(Integer basePokemonId, Integer evolvedPokemonId, String evolutionTrigger, Integer levelRequired) {
        this.basePokemonId = basePokemonId;
        this.evolvedPokemonId = evolvedPokemonId;
        this.evolutionTrigger = evolutionTrigger;
        this.levelRequired = levelRequired;
    }

    public Integer getBasePokemonId() { return basePokemonId; }
    public void setBasePokemonId(Integer basePokemonId) { this.basePokemonId = basePokemonId; }

    public Integer getEvolvedPokemonId() { return evolvedPokemonId; }
    public void setEvolvedPokemonId(Integer evolvedPokemonId) { this.evolvedPokemonId = evolvedPokemonId; }

    public String getEvolutionTrigger() { return evolutionTrigger; }
    public void setEvolutionTrigger(String evolutionTrigger) { this.evolutionTrigger = evolutionTrigger; }

    public Integer getLevelRequired() { return levelRequired; }
    public void setLevelRequired(Integer levelRequired) { this.levelRequired = levelRequired; }
}