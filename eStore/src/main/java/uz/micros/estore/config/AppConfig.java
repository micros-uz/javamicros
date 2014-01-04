package uz.micros.estore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import uz.micros.estore.util.DateFormatter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "uz.micros.estore")
@Import({ WebInitializer.class, DispatcherConfig.class})
public class AppConfig {

    @Bean(name = "dateFormatter")
    public DateFormatter getDateFormatter(){
        return new DateFormatter();
    }
}