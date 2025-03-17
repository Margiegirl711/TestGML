package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Pokemon", namespace = "http://pokeapi.com/pokemon")
@XmlType(namespace = "http://pokeapi.com/pokemon")
public class Pokemon {
	
	private String name;
    private String url;

    public Pokemon() {}

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
