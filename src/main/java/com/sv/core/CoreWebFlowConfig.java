package com.sv.core;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.faces.config.AbstractFacesFlowConfiguration;
import org.springframework.faces.webflow.FlowFacesContextLifecycleListener;
import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.engine.builder.support.FlowBuilderServices;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;
import org.springframework.webflow.security.SecurityFlowExecutionListener;


/**
 * Config Spring WebFlow
 */
@Configuration
public class CoreWebFlowConfig extends AbstractFacesFlowConfiguration {

	protected final Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public FlowDefinitionRegistry flowRegistry() 
    {
    	log.debug("Now set and init flow registry");
    	
        return getFlowDefinitionRegistryBuilder()
                .setBasePath("classpath:/webapp/WEB-INF/flows")
                .addFlowLocationPattern("/**/*.xml")
                .setFlowBuilderServices(flowBuilderServices())
                .build();
    }

    @Bean
    public FlowExecutor flowExecutor() {
    	log.debug("Now init flow executor");
    	
        return getFlowExecutorBuilder(flowRegistry())
                .addFlowExecutionListener(new FlowFacesContextLifecycleListener())
                .addFlowExecutionListener(new SecurityFlowExecutionListener())
                .build();
    }

    @Bean
    public FlowBuilderServices flowBuilderServices() {
        return getFlowBuilderServicesBuilder()
                .setDevelopmentMode(true)
                .build();
    }
    
	@Bean
	public FlowHandlerMapping flowHandlerMapping() {
		FlowHandlerMapping handlerMapping = new FlowHandlerMapping();
		handlerMapping.setOrder(-1);
		handlerMapping.setFlowRegistry(flowRegistry());
		return handlerMapping;
	}

	@Bean
	public FlowHandlerAdapter flowHandlerAdapter() {
		JsfFlowHandlerAdapter adapter = new JsfFlowHandlerAdapter();
		adapter.setFlowExecutor(flowExecutor());
		return adapter;
	}

}
