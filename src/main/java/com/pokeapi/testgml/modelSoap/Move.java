package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "move")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "name", "power", "accuracy", "description" }) // Garantiza el orden en la serialización
public class Move {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "name", required = true)
    private String name;

    @XmlElement(name = "power", required = false)
    private Integer power;

    @XmlElement(name = "accuracy", required = false)
    private Integer accuracy;

    @XmlElement(name = "description", nillable = true)
    private String description;

    public Move() {}


    public Move(Integer id, String name, Integer power, Integer accuracy, String description) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}