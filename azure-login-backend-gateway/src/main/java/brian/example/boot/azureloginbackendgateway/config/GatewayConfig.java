package brian.example.boot.azureloginbackendgateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Value("${service.first.uri}")
    private String firstServiceURI;


    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        System.out.println("!!!!!!!!!! gatewayRoutes Init");
        return builder.routes()
                .route(r -> r.path("/api/**")
                                .and()
//                                .method(HttpMethod.GET)
                                .uri(firstServiceURI)
//                        .id("employeeModule")
                )
                .build();
    }
}
