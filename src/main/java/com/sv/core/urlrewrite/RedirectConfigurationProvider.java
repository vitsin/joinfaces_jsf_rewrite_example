package com.sv.core.urlrewrite;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.servlet.config.Forward;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RedirectConfigurationProvider extends HttpConfigurationProvider {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
    @Override
    public Configuration getConfiguration(final ServletContext context)
    {
    	log.debug("Redirect: invoking all rules");
    	
    	Configuration cb = ConfigurationBuilder.begin()
    		 // Match any incoming URL to alpha-numeric chars only 	
    		.addRule()
    		.when(Direction.isInbound().and(Path.matches("/{myname}")))
    		.perform(Forward.to("/sgui/flows/public?name={myname}"))
    		.otherwise(SendStatus.code(404))
    		.where("myname").matches("[a-zA-Z0-9_]+")
    	;
    	return cb;
    }

	@Override
	public int priority() {
		return 0;
	}
}
