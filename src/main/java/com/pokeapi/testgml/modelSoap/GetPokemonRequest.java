package com.pokeapi.testgml.modelSoap;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetPokemonRequest", namespace = "http://pokeapi.com/pokemon")
@XmlType(namespace = "http://pokeapi.com/pokemon" , propOrder = { "limit", "offset" })
public class GetPokemonRequest {
	
	 private int limit;
	    private int offset;

	    @XmlElement
	    public int getLimit() {
	        return limit;
	    }

	    public void setLimit(int limit) {
	        this.limit = limit;
	    }

	    @XmlElement
	    public int getOffset() {
	        return offset;
	    }

	    public void setOffset(int offset) {
	        this.offset = offset;
	    }
}
