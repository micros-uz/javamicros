package uz.micros;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("uz.micros")
public class AppConfig {

    @Bean(name="renDerer")
    public Renderer getRenderer(){
        Renderer res =  new Renderer();

        //res.

        return res;
    }
}
