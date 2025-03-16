package com.pokeapi.testgml.service.configuration;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetPokemonRequest", namespace = "http://pokeapi.com/pokemon")
@XmlType(namespace = "http://pokeapi.com/pokemon")
public class GetPokemonRequest {
	
	 private int limit;
	    private int offset;
	    
	    public int getLimit() {
	        return limit;
	    }
	    
	    public void setLimit(int limit) {
	        this.limit = limit;
	    }
	    
	    public int getOffset() {
	        return offset;
	    }
	    
	    public void setOffset(int offset) {
	        this.offset = offset;
	    }

}
