package uz.micros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Renderer {

    @Autowired
    private  Reader reader;
    @Autowired
    private Parser parser;


/*  @Autowired
    public Renderer(Reader rd, Parser ps) {
        reader = rd;
        parser = ps;
    }*/

    public String Render(String viewName) {

        //Reader reader = new Reader(/*w43r234,234234, 234234, 234234*/);
        //Parser parser = new Parser(/*21342134,2134234,234234*/);

        String tmpl = reader.Read(viewName);
        String html = parser.Parse(tmpl);

        return html;
    }
}
