Thymeleaf fork of spring-social-examples from pkainulainen
======================

This is a fork from [https://github.com/pkainulainen/spring-social-examples](https://github.com/pkainulainen/spring-social-examples "https://github.com/pkainulainen/spring-social-examples"). It is the source to the blog post "Adding Social Sign In to a Spring MVC Web Application: Registration and Login" from Petri Kainulainen (see at [http://www.petrikainulainen.net/programming/spring-framework/adding-social-sign-in-to-a-spring-mvc-web-application-registration-and-login/](http://www.petrikainulainen.net/programming/spring-framework/adding-social-sign-in-to-a-spring-mvc-web-application-registration-and-login/ "http://www.petrikainulainen.net/programming/spring-framework/adding-social-sign-in-to-a-spring-mvc-web-application-registration-and-login/")).

The application use JSP (Java Server Pages). Now, I replace jsp with thymeleaf in my fork.

It is easy to use thymeleaf instead of jsp. Look on the source.

Please tell me if you find a failure or a better way.

At the following you can see a part of differences between jsp and thymeleaf. The complete differences you can see at the source files.

## Differences between jsp and thymeleaf ##

**Setup**

JSP:

    private static final String VIEW_RESOLVER_PREFIX = "/WEB-INF/jsp/";
    private static final String VIEW_RESOLVER_SUFFIX = ".jsp";

    @Bean
    public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix(VIEW_RESOLVER_PREFIX);
    viewResolver.setSuffix(VIEW_RESOLVER_SUFFIX);
    
    return viewResolver;
    }


Thymeleaf (this is only a part):

    @Bean
    public ServletContextTemplateResolver templateResolverServlet() {
    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/html/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("LEGACYHTML5");
    templateResolver.setOrder(2);
    templateResolver.setCacheable(false);
    return templateResolver;
    }

See the rest on [https://github.com/lightszentip/spring-social-examples-thymeleaf/blob/master/sign-in/spring-mvc-normal/src/main/java/net/petrikainulainen/spring/social/signinmvc/config/ThymeleafView.java](https://github.com/lightszentip/spring-social-examples-thymeleaf/blob/master/sign-in/spring-mvc-normal/src/main/java/net/petrikainulainen/spring/social/signinmvc/config/ThymeleafView.java)


**Layout**

For layout in thymeleaf, I have used [thymeleaf-layout-dialect](https://github.com/ultraq/thymeleaf-layout-dialect) instead sitemesh.

JSP sitemesh layout:

    <div id="view-holder">
    <sitemesh:write property="body">
    </div>

Thymeleaf layout:

    <div id="view-holder">
    <div layout:fragment="body" />
    </div>

and at the thymeleaf page

    layout:fragment="body"


**isAuthenticated**

JSP:

    <sec:authorize access="isAuthenticated()"

Thymeleaf:

     <p sec:authorize="isAuthenticated()" 


**Link**

JSP:

    <c:url value="/auth/twitter"

Thymeleaf

    th:href="@{/auth/facebook}"

**Username**

JSP:

     <sec:authentication property="principal.username"/>

Thymeleaf:

    <span sec:authentication="principal.username"></span>

