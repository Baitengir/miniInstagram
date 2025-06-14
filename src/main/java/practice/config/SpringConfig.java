package practice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("practice")
@PropertySource("classpath:applicationProperties.properties")
public class SpringConfig {
}
