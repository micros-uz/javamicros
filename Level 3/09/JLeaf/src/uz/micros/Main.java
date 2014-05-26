package uz.micros;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        //Renderer renderer = IoC.Get(Renderer.class);
        Renderer renderer = new Renderer(/*new Reader(), new Parser(new X())*/);

        renderer = (Renderer)ctx.getBean("renDerer");

        String html = renderer.Render("home");

        System.out.println(html);
    }
}
