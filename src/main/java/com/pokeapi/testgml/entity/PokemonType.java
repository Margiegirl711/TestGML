package com.pokeapi.testgml.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pokemon_type", schema = "Pokeapi",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"pokemon_id", "type_name"})})
@Getter
@Setter
@NoArgsConstructor
public class PokemonType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @Column(name = "type_name", length = 50, nullable = false)
    private String typeName;

    public PokemonType(Pokemon pokemon, String typeName) {
        this.pokemon = pokemon;
        this.typeName = typeName;
    }
}
