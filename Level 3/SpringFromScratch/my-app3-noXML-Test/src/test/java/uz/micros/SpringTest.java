package uz.micros;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import uz.micros.config.AppConfig;

import static org.junit.Assert.assertTrue;

// http://spring.io/blog/2011/06/21/spring-3-1-m2-testing-with-configuration-classes-and-profiles/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class SpringTest {

    @Autowired
    private HelloWorld helloWorld;

    @Test
    public void testHelloWorldGivesAString(){
        Object o = helloWorld.printHelloWorld("Jojn");

        assertTrue(o instanceof String);
    }
}
