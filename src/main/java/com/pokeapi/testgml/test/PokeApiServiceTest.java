package com.pokeapi.testgml.test;



import java.util.Collections;
import java.util.Map;

import org.springframework.web.server.ResponseStatusException;

import com.pokeapi.testgml.mapper.PokemonDetailsMapper;
import com.pokeapi.testgml.modelSoap.PokemonDetailsResponse;
import com.pokeapi.testgml.modelSoap.PokemonSaveRequest;
import com.pokeapi.testgml.modelSoap.PokemonSaveResponse;
import com.pokeapi.testgml.service.PokemonApiClient;
import com.pokeapi.testgml.strategy.PokemonDataSaver;

//@ExtendWith(MockitoExtension.class)
class PokeApiServiceTest {
    
 /*   @Mock
    private PokemonApiClient pokemonApiClient;
    
    @Mock
    private PokemonDetailsMapper pokemonDetailsMapper;
    
    @Mock
    private PokemonDataSaver pokemonDataSaver;
    
    @InjectMocks
    private PokeApiService pokeApiService;
    
    @BeforeEach
    void setUp() {
    }
    
    @Test
    void testGetPokemons_Success() {
        when(pokemonApiClient.fetchData(anyString())).thenReturn(Collections.singletonMap("results", Collections.emptyList()));
        Map<String, Object> result = pokeApiService.getPokemons(10, 0);
        assertNotNull(result);
        assertTrue(result.containsKey("results"));
    }
    
    @Test
    void testGetPokemons_Failure() {
        when(pokemonApiClient.fetchData(anyString())).thenThrow(new RuntimeException("API failure"));
        assertThrows(ResponseStatusException.class, () -> pokeApiService.getPokemons(10, 0));
    }
    
    @Test
    void testGetPokemonDetails_Success() {
        Map<String, Object> mockResponse = Collections.singletonMap("id", 1);
        PokemonDetailsResponse detailsResponse = new PokemonDetailsResponse();
        when(pokemonApiClient.fetchData(anyString())).thenReturn(mockResponse);
        when(pokemonDetailsMapper.mapToPokemonDetails(mockResponse)).thenReturn(detailsResponse);
        
        PokemonDetailsResponse result = pokeApiService.getPokemonDetails(1);
        assertNotNull(result);
    }
    
    @Test
    void testGetPokemonDetails_NotFound() {
        when(pokemonApiClient.fetchData(anyString())).thenThrow(new RuntimeException("Not found"));
        assertThrows(ResponseStatusException.class, () -> pokeApiService.getPokemonDetails(999));
    }
    
    @Test
    void testGetPokemonDetailsByName_Success() {
        Map<String, Object> mockResponse = Collections.singletonMap("name", "pikachu");
        PokemonDetailsResponse detailsResponse = new PokemonDetailsResponse();
        when(pokemonApiClient.fetchData(anyString())).thenReturn(mockResponse);
        when(pokemonDetailsMapper.mapToPokemonDetails(mockResponse)).thenReturn(detailsResponse);
        
        PokemonDetailsResponse result = pokeApiService.getPokemonDetailsByName("pikachu");
        assertNotNull(result);
    }
    
    @Test
    void testSavePokemonDetails_Success() {
        PokemonSaveRequest request = new PokemonSaveRequest();
        request.setId(1);
        request.setName("pikachu");
        
        Map<String, Object> mockResponse = Collections.singletonMap("id", 1);
        when(pokemonApiClient.fetchData(anyString())).thenReturn(mockResponse);
        doNothing().when(pokemonDataSaver).savePokemonData(mockResponse);
        
        PokemonSaveResponse response = pokeApiService.savePokemonDetails(request);
        assertNotNull(response);
        assertEquals("El Pokémon pikachu ha sido guardado exitosamente.", response.getMessage());
    }
    
    @Test
    void testSavePokemonDetails_InvalidRequest() {
        PokemonSaveRequest request = new PokemonSaveRequest();
        PokemonSaveResponse response = pokeApiService.savePokemonDetails(request);
        assertNotNull(response);
        assertEquals("Debe proporcionar un ID o un nombre válido para el Pokémon.", response.getMessage());
    }*/
}
