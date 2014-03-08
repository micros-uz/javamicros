package mikeyp.spikes.configuration;



import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
   protected Class<?>[] getRootConfigClasses() {
           return null;
   }

    @Override
   protected Class<?>[] getServletConfigClasses() {
           return new Class<?>[] { WebMvcConfig.class };
   }

   @Override
   protected String[] getServletMappings() {
           return new String[] { "/*" };
   }

   @Override
   protected void customizeRegistration(Dynamic registration) {
           registration.setAsyncSupported(true);
    }

}