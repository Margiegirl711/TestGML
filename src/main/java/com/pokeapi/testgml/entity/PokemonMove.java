package com.pokeapi.testgml.entity;

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
@Table(name = "pokemon_move", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"pokemon_id", "move_id"})
})
@Getter
@Setter
@NoArgsConstructor
public class PokemonMove {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokemon_id", nullable = false)
    private Pokemon pokemon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "move_id", nullable = false)
    private Move move;

    public PokemonMove(Pokemon pokemon, Move move) {
        this.pokemon = pokemon;
        this.move = move;
    }
}