package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PokemonSaveRequest", namespace = "http://pokeapi.com/pokemon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    namespace = "http://pokeapi.com/pokemon",
    propOrder = { "id", "name" }
)
public class PokemonSaveRequest {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "name", required = true)
    private String name;

    public PokemonSaveRequest() {}

    public PokemonSaveRequest(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}