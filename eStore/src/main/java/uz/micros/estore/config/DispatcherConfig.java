package uz.micros.estore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import uz.micros.estore.util.Interceptor;

@Configuration
@Import({ThymeleafConfig.class})
public class DispatcherConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
/*        registry.addResourceHandler("/js*//**").addResourceLocations("/js/");
        registry.addResourceHandler("/css*//**").addResourceLocations("/css/");
        registry.addResourceHandler("/html*//**").addResourceLocations("/html/");
        registry.addResourceHandler("/images*//**").addResourceLocations("/images/");*/
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new Interceptor());
    }
}
