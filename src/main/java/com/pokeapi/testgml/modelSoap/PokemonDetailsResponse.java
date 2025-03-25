package com.pokeapi.testgml.modelSoap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

import java.util.List;

@XmlRootElement(name = "PokemonDetailsResponse", namespace = "http://pokeapi.com/pokemon")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    namespace = "http://pokeapi.com/pokemon",
    propOrder = { "id", "name", "height", "weight", "imageUrl", "types", "abilities", "moves", "evolutions" }
)
public class PokemonDetailsResponse {

    @XmlElement(name = "id", required = true)
    private Integer id;

    @XmlElement(name = "name", required = true)
    private String name;

    @XmlElement(name = "height")
    private Integer height;

    @XmlElement(name = "weight")
    private Integer weight;

    @XmlElement(name = "image_url")
    private String imageUrl;

    @XmlElementWrapper(name = "types")
    @XmlElement(name = "type")
    private List<Type> types;

    @XmlElementWrapper(name = "abilities")
    @XmlElement(name = "ability")
    private List<Ability> abilities;

    @XmlElementWrapper(name = "moves")
    @XmlElement(name = "move")
    private List<Move> moves;

    @XmlElementWrapper(name = "evolutions")
    @XmlElement(name = "evolution")
    private List<Evolution> evolutions;


    public PokemonDetailsResponse() {}

    private PokemonDetailsResponse(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.height = builder.height;
        this.weight = builder.weight;
        this.imageUrl = builder.imageUrl;
        this.types = builder.types;
        this.abilities = builder.abilities;
        this.moves = builder.moves;
        this.evolutions = builder.evolutions;
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

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public List<Evolution> getEvolutions() {
        return evolutions;
    }

    public void setEvolutions(List<Evolution> evolutions) {
        this.evolutions = evolutions;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String name;
        private Integer height;
        private Integer weight;
        private String imageUrl;
        private List<Type> types;
        private List<Ability> abilities;
        private List<Move> moves;
        private List<Evolution> evolutions;

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder height(Integer height) {
            this.height = height;
            return this;
        }

        public Builder weight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder types(List<Type> types) {
            this.types = types;
            return this;
        }

        public Builder abilities(List<Ability> abilities) {
            this.abilities = abilities;
            return this;
        }

        public Builder moves(List<Move> moves) {
            this.moves = moves;
            return this;
        }

        public Builder evolutions(List<Evolution> evolutions) {
            this.evolutions = evolutions;
            return this;
        }

        public PokemonDetailsResponse build() {
            return new PokemonDetailsResponse(this);
        }
    }
}