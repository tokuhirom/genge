package me.geso.genge.plugin.echo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@ConditionalOnProperty("genge.echo.enabled")
public class EchoConfig {
    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public EchoHandler echoHandler() {
        return new EchoHandler();
    }
}
