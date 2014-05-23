package uz.micros;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        //Renderer renderer = new Renderer(new Reader(), new Parser());

        Renderer renderer = (Renderer)ctx.getBean(Renderer.class);

        String html = renderer.Render("home");

        Object rr = ctx.getBean(Parser.class);
        System.out.println(html);
    }
}
