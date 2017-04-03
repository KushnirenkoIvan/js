package ua.kushnirenko.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
@ComponentScan(basePackages = "ua.kushnirenko.controller")
public class WebConfig {

    @Bean
    EmbeddedServletContainerFactory servletContainerFactory() {
        return new TomcatEmbeddedServletContainerFactory();
    }

    @Bean
    public WebMvcConfigurerAdapter forwarder() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("forward:/dashboard.html");
            }
        };
    }
}
