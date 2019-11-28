package com.sv.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import com.synaptic.core.filter.MyFilter;

@EnableWebSecurity
@Configuration
public class CoreSecurityConfig extends WebSecurityConfigurerAdapter {
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		// TODO:
		log.debug("AuthenticationManagerBuilder is invoked");
    }
	
    @Override
    public void configure(WebSecurity webSecurity) 
    {
    	log.debug("FilterChainProxy is invoked");
    	//TODO:
    }

    // Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
    	//@formatter:off
        http
        //TODO: add security
        .addFilterBefore(new MyFilter(), WebAsyncManagerIntegrationFilter.class)
        .httpBasic()
        	.authenticationEntryPoint(loginUrlAuthEntryPoint())
        	.realmName("gui")
       	;
        //@formatter:on
    }
    
    @Bean
    public AuthenticationEntryPoint loginUrlAuthEntryPoint()
    {
        return new LoginUrlAuthenticationEntryPoint("/public");
    }
}

