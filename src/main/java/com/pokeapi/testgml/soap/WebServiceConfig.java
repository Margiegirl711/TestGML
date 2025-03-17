package com.pokeapi.testgml.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {
	
	 @Bean
	    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
	        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
	        servlet.setApplicationContext(context);
	        servlet.setTransformWsdlLocations(true);
	        return new ServletRegistrationBean<>(servlet, "/ws/*");
	    }

	    @Bean
	    public XsdSchema pokemonSchema() {
	        return new SimpleXsdSchema(new org.springframework.core.io.ClassPathResource("pokemon.xsd"));
	    }
	    
	    @Bean(name = "pokemon")
	    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema pokemonSchema) {
	        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
	        wsdl11Definition.setPortTypeName("PokemonPort");
	        wsdl11Definition.setLocationUri("/ws");
	        wsdl11Definition.setTargetNamespace("http://pokeapi.com/pokemon");
	        wsdl11Definition.setSchema(pokemonSchema);
	        return wsdl11Definition;
	    }

}
