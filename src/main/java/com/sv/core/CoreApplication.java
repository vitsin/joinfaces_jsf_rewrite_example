package com.sv.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(CoreApplication.class, args);
	}
	
	/**
	 * Workaround allows FacesServlet to load-on-startup.
	 * Solving: Could not find backup for factory javax.faces.context.FacesContextFactory
	 */
	@Bean
	public com.sun.faces.config.ConfigureListener mojarraConfigureListener() {
	    return new com.sun.faces.config.ConfigureListener();
	}
}
