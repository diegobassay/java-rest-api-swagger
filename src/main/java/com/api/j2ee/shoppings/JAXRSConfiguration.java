package com.api.j2ee.shoppings;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import com.api.j2ee.shoppings.rest.StoreRest;
import com.api.j2ee.shoppings.rest.SegmentRest;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import java.util.*;

/**
 * Classe responsavel pelas principais configurações do JAX-RS. 
 * @author Diego Costa
 * @since 1.0.0
 */
@ApplicationPath("resources")
public class JAXRSConfiguration extends Application {
	
	/**
	 * Inicializo o Swagger para console de test e desenvolvimento.
	 */
	public JAXRSConfiguration() {
		BeanConfig conf = new BeanConfig();
		conf.setTitle("Shopping API");
		conf.setDescription("Api com serviços rest para administração de shoppings");
		conf.setVersion("1.0.0");
		conf.setHost("localhost:8080");
		conf.setBasePath("/shoppingapi/resources");
		conf.setSchemes(new String[] { "http" });
		conf.setResourcePackage("com.api.j2ee.shoppings");
		conf.setScan(true);
	}

	/**
	 * Adicionando serviços rest para o contexto do JAX-RS. 
	 */
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new HashSet<>();
		resources.add(StoreRest.class);
		resources.add(SegmentRest.class);
		resources.add(ApiListingResource.class);
		resources.add(SwaggerSerializers.class);
		return resources;
	}
}
