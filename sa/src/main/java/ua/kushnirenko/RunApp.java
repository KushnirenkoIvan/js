package ua.kushnirenko;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import ua.kushnirenko.config.DbConfig;
import ua.kushnirenko.config.ServiceConfig;
import ua.kushnirenko.config.WebConfig;


@EnableAutoConfiguration
@Import({DbConfig.class,
        ServiceConfig.class,
        WebConfig.class})
@PropertySource("file:conf/application.properties")
public class RunApp {

    public static Logger log = Logger.getLogger(RunApp.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/log4j.properties");
        SpringApplication.run(RunApp.class, args);
    }
}
