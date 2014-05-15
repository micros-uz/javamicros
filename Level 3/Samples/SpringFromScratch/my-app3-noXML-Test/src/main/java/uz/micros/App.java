package uz.micros;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import uz.micros.config.AppConfig;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld obj = (HelloWorld) context.getBean("helloBean");

        String st = obj.printHelloWorld("Spring3 Java Config");

        System.out.println(st);
    }
}
