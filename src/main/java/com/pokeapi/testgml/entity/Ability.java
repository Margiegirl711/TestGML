package com.pokeapi.testgml.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ability")
@Getter
@Setter
public class Ability {

    @Id
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(length = 255)
    private String description;

    public Ability() {}

    public Ability(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
