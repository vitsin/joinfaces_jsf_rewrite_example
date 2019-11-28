package com.synaptic.core.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @Component
public class MyFilter implements Filter {

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException 
	{
		log.debug("MyFilter doFilter() is invoked");
	    Enumeration<String> params = req.getParameterNames();
	    
	    while (params.hasMoreElements()) {
	    	String param=params.nextElement();
	    	log.debug("Parameter:"+param+"\tValue:"+req.getParameter(param));
	    }
	    
	    chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {}

	@Override
	public void destroy() {}
}