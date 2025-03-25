package com.pokeapi.testgml.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "move")
@Getter
@Setter
public class Move {

    @Id
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    private int power;
    private int accuracy;

    @Column(name = "times_of_use")
    private int timesOfUse;

    @Column(name = "damage_area", length = 50)
    private String damageArea;

    @Column(length = 255)
    private String description;

    public Move() {}

    public Move(int id, String name, Type type, int power, int accuracy, int timesOfUse, String damageArea, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
        this.timesOfUse = timesOfUse;
        this.damageArea = damageArea;
        this.description = description;
    }

}
