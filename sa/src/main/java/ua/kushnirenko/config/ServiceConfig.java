package ua.kushnirenko.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ua.kushnirenko.service")
public class ServiceConfig {
}
