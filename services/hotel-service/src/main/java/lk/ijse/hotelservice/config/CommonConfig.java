package lk.ijse.hotelservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CommonConfig {
    @LoadBalanced
    @Bean
    public WebClient.Builder getLoadBalanced() {
        return WebClient.builder();
    }
}
