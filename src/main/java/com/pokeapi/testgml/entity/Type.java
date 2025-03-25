package com.pokeapi.testgml.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "type")
@Getter
@Setter
public class Type {

    @Id
    private int id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    public Type() {}

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

}