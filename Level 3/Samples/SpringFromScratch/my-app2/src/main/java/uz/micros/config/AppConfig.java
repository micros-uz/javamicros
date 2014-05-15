package uz.micros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uz.micros.HelloWorld;
import uz.micros.HelloWorldImpl;

@Configuration
public class AppConfig {

    @Bean(name = "helloBean")
    public HelloWorld helloWorld() {
        return new HelloWorldImpl();
    }

}
