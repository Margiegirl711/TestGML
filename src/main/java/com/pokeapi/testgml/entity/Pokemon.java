package com.pokeapi.testgml.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pokemon")
@Getter
@Setter
public class Pokemon {

	    @Id
	    private int id;

	    @Column(nullable = false, length = 100)
	    private String name;

	    private int height;
	    private int weight;

	    @Column(name = "image_url", length = 255)
	    private String imageUrl;

	    @Column(name = "evolution_stage", columnDefinition = "INT DEFAULT 1")
	    private int evolutionStage;

	    public Pokemon() {}

	    public Pokemon(int id, String name, int height, int weight, String imageUrl, int evolutionStage) {
	        this.id = id;
	        this.name = name;
	        this.height = height;
	        this.weight = weight;
	        this.imageUrl = imageUrl;
	        this.evolutionStage = evolutionStage;
	    }

	    
	    
}
