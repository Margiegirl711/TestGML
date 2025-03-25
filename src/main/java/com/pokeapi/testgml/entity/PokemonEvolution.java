package com.pokeapi.testgml.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_evolution",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"base_pokemon_id", "evolved_pokemon_id"})})
@Getter
@Setter
@NoArgsConstructor
public class PokemonEvolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_pokemon_id", referencedColumnName = "id", nullable = false)
    private Pokemon basePokemon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evolved_pokemon_id", referencedColumnName = "id", nullable = false)
    private Pokemon evolvedPokemon;

    @Column(name = "evolution_trigger", length = 100)
    private String evolutionTrigger;

    @Column(name = "level_required")
    private Integer levelRequired;

    public PokemonEvolution(Pokemon basePokemon, Pokemon evolvedPokemon, String evolutionTrigger, Integer levelRequired) {
        this.basePokemon = basePokemon;
        this.evolvedPokemon = evolvedPokemon;
        this.evolutionTrigger = evolutionTrigger;
        this.levelRequired = levelRequired;
    }
}