package com.example.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   public void onStartup(ServletContext servletContext) throws ServletException {
      super.onStartup(servletContext);
   }

   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[] { SecurityConfig.class, ComponentConfig.class };
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] { AppConfig.class };
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] { "/" };
   }

   @Override
   protected Filter[] getServletFilters() {
      return new Filter[] { new DelegatingFilterProxy("springSecurityFilterChain") };
   }

}