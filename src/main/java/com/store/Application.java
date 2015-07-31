package com.store;

import com.store.dao.GameDAO;
import com.store.ws.rest.config.JerseyConfig;
import com.store.ws.service.GamesServiceImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource({ "classpath:META-INF/cxf/cxf.xml" })
public class Application extends SpringBootServletInitializer {

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // Replaces the need for web.xml
    @Bean
    public ServletRegistrationBean servletRegistrationBean(ApplicationContext context) {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new CXFServlet(), "/services/*");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean jerseyServlet() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/restservices/*");
        registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, JerseyConfig.class.getName());
        registration.setLoadOnStartup(1);
        return registration;
    }

    // Replaces cxf-servlet.xml
    @Bean
    // <jaxws:endpoint id="helloWorld" implementor="demo.spring.service.HelloWorldImpl" address="/HelloWorld"/>
    public EndpointImpl gameService() {
        Bus bus = (Bus) applicationContext.getBean(Bus.DEFAULT_BUS_ID);

        GamesServiceImpl gamesService = new GamesServiceImpl();
        gamesService.setGameDAO((GameDAO) applicationContext.getBean("gameDAO"));
        Object implementor = gamesService;

        EndpointImpl endpoint = new EndpointImpl(bus, implementor);
        endpoint.publish("/gameServices");
        endpoint.getServer().getEndpoint().getInInterceptors().add(new LoggingInInterceptor());
        endpoint.getServer().getEndpoint().getOutInterceptors().add(new LoggingOutInterceptor());
        return endpoint;
    }

    // Configure the embedded tomcat to use same settings as default standalone tomcat deploy
    @Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory() {
        // Made to match the context path when deploying to standalone tomcat- can easily be kept in sync w/ properties
        TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory("/ws-server-1.0", 8090);
        return factory;
    }

    // Used when deploying to a standalone servlet container, i.e. tomcat
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
