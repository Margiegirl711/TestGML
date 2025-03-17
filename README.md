Para consumir el seervicio el wsdl es http://localhost:8080/ws/pokemon.wsdl

El consumo del servicio soap es 
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:poke="http://pokeapi.com/pokemon">
   <soapenv:Header/>
   <soapenv:Body>
      <poke:GetPokemonRequest>
         <limit>100</limit>
         <offset>20</offset>
      </poke:GetPokemonRequest>
   </soapenv:Body>
</soapenv:Envelope>

limit y offset es para la paginacion.

Los patrones de diseño utilizados son DTO, ya que el request y el response de SOAP tienen una estructura definida, el request para su interpretaciòn y el response para salida.
Y se utilia adapter ya que el consumo de la pokeapi la salida es en formato JSON mientras que soap utiliza un xml, entonces primero se convierte el JSON en un Map<String, Object>
de los que se extare name y url, los cuales se convierten en una lista de objetos pokemon dentro de GetPokemonResponse.


