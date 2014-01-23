package com.gmind7.bakery.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

public class WebAppInitializer extends AbstractWebAppInitializer {

	@Override
	protected Filter[] getServletFilters() {
		
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		
		DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
		
		return new javax.servlet.Filter[] { encodingFilter, 
											springSecurityFilterChain,
			                                new HiddenHttpMethodFilter(), 
			                                new ShallowEtagHeaderFilter() };
	}

}
