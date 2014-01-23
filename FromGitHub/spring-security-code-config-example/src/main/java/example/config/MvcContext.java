package example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

import static freemarker.template.Configuration.SQUARE_BRACKET_TAG_SYNTAX;

@Configuration
@EnableWebMvc
@ComponentScan("example.web.controllers")
public class MvcContext extends WebMvcConfigurerAdapter {

    @Autowired ServletContext context;

    @Bean
    public FreeMarkerConfigurer freeMarkerPreConfigurer() throws IOException {
        FreeMarkerConfigurer config = new FreeMarkerConfigurer();

        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
        cfg.setDirectoryForTemplateLoading(new File(context.getRealPath("/WEB-INF/ftl")));
        cfg.setTagSyntax(SQUARE_BRACKET_TAG_SYNTAX);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setWhitespaceStripping(true);

        config.setConfiguration(cfg);

        return config;
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();

//        viewResolver.setPrefix("/WEB-INF/ftl/");
        viewResolver.setSuffix(".ftl");
        viewResolver.setContentType("text/html;charset=UTF-8");

        viewResolver.setExposeSpringMacroHelpers(true);

        return viewResolver;
    }
}
