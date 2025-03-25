package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "PokemonDetailsRequest", namespace = "http://pokeapi.com/pokemon")
@XmlType(propOrder = { "id", "name" })
public class PokemonDetailsRequest {
    
    private Integer id;
    private String name;

    public PokemonDetailsRequest() {}

    public PokemonDetailsRequest(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}