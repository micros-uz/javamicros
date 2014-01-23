package com.example.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan({"com.example.controllers"})
public class AppConfig extends WebMvcConfigurerAdapter{
   @Bean
   public InternalResourceViewResolver setupViewResolver() {
      InternalResourceViewResolver resolver = new InternalResourceViewResolver();
      resolver.setPrefix("/WEB-INF/jsp/");
      resolver.setSuffix(".jsp");
      resolver.setViewClass(JstlView.class);
      return resolver;
   }
   
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/css/**").addResourceLocations("/css/");
      registry.addResourceHandler("/js/**").addResourceLocations("/js/");
      registry.addResourceHandler("/fonts/**")
            .addResourceLocations("/fonts/");
   }
   
   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
       configurer.enable();
   }

  @Override
  public void addArgumentResolvers(
        List<HandlerMethodArgumentResolver> argumentResolvers) {
     argumentResolvers.add(pageable());
  }

  @Bean
  public PageableHandlerMethodArgumentResolver pageable() {
     return new PageableHandlerMethodArgumentResolver();
  }
}
