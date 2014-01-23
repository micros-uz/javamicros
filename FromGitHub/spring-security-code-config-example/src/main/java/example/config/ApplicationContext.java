package example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MvcContext.class, SecurityContext.class})
public class ApplicationContext {
}
