package com.pokeapi.testgml.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pokemon_report") 
public class PokemonReport {

		
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "pokemon_id", nullable = false)
    private Integer pokemonId;

    @Column(name = "pokemon_name")
    private String pokemonName;

    @Column(name = "height")
    private Integer height;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "type_name")
    private String typeName;

    @Column(name = "ability_name")
    private String abilityName;

    @Column(name = "move_name")
    private String moveName;

    @Column(name = "move_power")
    private Integer movePower;

    @Column(name = "move_damage_area")
    private String moveDamageArea;

    @Column(name = "evolved_pokemon_id")
    private Integer evolvedPokemonId;

    @Column(name = "evolved_pokemon_name")
    private String evolvedPokemonName;
	    
	    
        
	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Integer getPokemonId() {
		return pokemonId;
	}

	public void setPokemonId(Integer pokemonId) {
		this.pokemonId = pokemonId;
	}

		public String getPokemonName() {
			return pokemonName;
		}

		public void setPokemonName(String pokemonName) {
			this.pokemonName = pokemonName;
		}

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height = height;
		}

		public Integer getWeight() {
			return weight;
		}

		public void setWeight(Integer weight) {
			this.weight = weight;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		public String getAbilityName() {
			return abilityName;
		}

		public void setAbilityName(String abilityName) {
			this.abilityName = abilityName;
		}

		public String getMoveName() {
			return moveName;
		}

		public void setMoveName(String moveName) {
			this.moveName = moveName;
		}

		public Integer getMovePower() {
			return movePower;
		}

		public void setMovePower(Integer movePower) {
			this.movePower = movePower;
		}

		public String getMoveDamageArea() {
			return moveDamageArea;
		}

		public void setMoveDamageArea(String moveDamageArea) {
			this.moveDamageArea = moveDamageArea;
		}

		public Integer getEvolvedPokemonId() {
			return evolvedPokemonId;
		}

		public void setEvolvedPokemonId(Integer evolvedPokemonId) {
			this.evolvedPokemonId = evolvedPokemonId;
		}

		public String getEvolvedPokemonName() {
			return evolvedPokemonName;
		}

		public void setEvolvedPokemonName(String evolvedPokemonName) {
			this.evolvedPokemonName = evolvedPokemonName;
		}
	    
	    
	}

