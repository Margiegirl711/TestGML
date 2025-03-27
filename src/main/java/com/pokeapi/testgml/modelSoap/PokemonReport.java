package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "PokemonReport")
@XmlType(propOrder = {
    "id", "pokemonName", "height", "weight", "imageUrl",
    "typeName", "abilityName", "moveName", "movePower",
    "moveDamageArea", "evolvedPokemonId", "evolvedPokemonName"
})
public class PokemonReport {

	private Integer id;
    private String pokemonName;
    private Integer height;
    private Integer weight;
    private String imageUrl;
    private String typeName;
    private String abilityName;
    private String moveName;
    private Integer movePower;
    private String moveDamageArea;
    private Integer evolvedPokemonId;
    private String evolvedPokemonName;

    public PokemonReport() {}
    
    

    public PokemonReport(Integer id, String pokemonName, Integer height, Integer weight, String imageUrl, String typeName,
			String abilityName, String moveName, Integer movePower, String moveDamageArea, Integer integer,
			String evolvedPokemonName) {
		super();
		this.id = id;
		this.pokemonName = pokemonName;
		this.height = height;
		this.weight = weight;
		this.imageUrl = imageUrl;
		this.typeName = typeName;
		this.abilityName = abilityName;
		this.moveName = moveName;
		this.movePower = movePower;
		this.moveDamageArea = moveDamageArea;
		this.evolvedPokemonId = integer;
		this.evolvedPokemonName = evolvedPokemonName;
	}



	@XmlElement
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    @XmlElement
    public String getPokemonName() { return pokemonName; }
    public void setPokemonName(String pokemonName) { this.pokemonName = pokemonName; }

    @XmlElement
    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }

    @XmlElement
    public Integer getWeight() { return weight; }
    public void setWeight(Integer weight) { this.weight = weight; }

    @XmlElement
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    @XmlElement
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }

    @XmlElement
    public String getAbilityName() { return abilityName; }
    public void setAbilityName(String abilityName) { this.abilityName = abilityName; }

    @XmlElement
    public String getMoveName() { return moveName; }
    public void setMoveName(String moveName) { this.moveName = moveName; }

    @XmlElement
    public Integer getMovePower() { return movePower; }
    public void setMovePower(Integer movePower) { this.movePower = movePower; }

    @XmlElement
    public String getMoveDamageArea() { return moveDamageArea; }
    public void setMoveDamageArea(String moveDamageArea) { this.moveDamageArea = moveDamageArea; }

    @XmlElement
    public Integer getEvolvedPokemonId() { return evolvedPokemonId; }
    public void setEvolvedPokemonId(Integer evolvedPokemonId) { this.evolvedPokemonId = evolvedPokemonId; }

    @XmlElement
    public String getEvolvedPokemonName() { return evolvedPokemonName; }
    public void setEvolvedPokemonName(String evolvedPokemonName) { this.evolvedPokemonName = evolvedPokemonName; }
}
