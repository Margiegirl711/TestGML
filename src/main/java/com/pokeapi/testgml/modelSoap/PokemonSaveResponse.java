package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "PokemonSaveResponse", namespace = "http://pokeapi.com/pokemon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    namespace = "http://pokeapi.com/pokemon",
    propOrder = { "id", "message" }
)
public class PokemonSaveResponse {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "message", required = true)
    private String message;

    public PokemonSaveResponse() {}

    public PokemonSaveResponse(Integer id, String message) {
        this.id = id;
        this.message = message;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
