package brian.example.boot.azureloginbackendgateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        System.out.println("securityWebFilterChain init #############" );
        http
                .cors().disable()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .pathMatchers("/api/heroes").permitAll()
                        .pathMatchers("/api/vigilante/*").authenticated()
                        .anyExchange().permitAll())
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}

//@Configuration
////@EnableWebSecurity
//@EnableWebFluxSecurity
//public class SecurityConfig //extends WebSecurityConfigurerAdapter
//{
//
//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
//    private String issuerUri;
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        System.out.println("Security Filter Chain init===================");
////        http
////            .authorizeHttpRequests()
////                .antMatchers("/api/heroes")
////                .permitAll()
////                .antMatchers("/api/vigilante/*")
////                .authenticated()
////                .and()
////                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
//////            .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())));
//////          .oauth2ResourceServer(oauth2ResourceServer ->
//////                  oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
//////          );
//////                .oauth2ResourceServer(oauth2 -> oauth2
//////                        .jwt(withDefaults())
//////                );
//////            .oauth2ResourceServer(oauth2ResourceServer ->
//////                    oauth2ResourceServer.jwt(jwtConfigurer ->
//////                            jwtConfigurer.decoder(jwtDecoder()))
//////            );
////
////
////    }
//
////    @Bean
////    public JwtAuthenticationConverter jwtAuthenticationConverter() {
////        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
////        // Customization of the converter if needed
////        return jwtAuthenticationConverter;
////    }
////
//////    @Bean
////    public JwtDecoder jwtDecoder() {
////        System.out.println("jwtDecoder Init ########################");
////        // Example for a custom decoder. Adjust the decoder setup as needed.
//////        String jwkSetUri = "https://login.windows.net/common/discovery/keys";
//////        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
////        return JwtDecoders.fromIssuerLocation(issuerUri);
////    }
//
////    @Bean
////    public ReactiveJwtDecoder jwtDecoder() {
////        System.out.println("ReactiveJwtDecoder Init ########################");
////        return ReactiveJwtDecoders.fromIssuerLocation(issuerUri);
////    }
//
//    @Bean
//    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
//        System.out.println("securityFilterChain init===================");
//        return http
//                .authorizeExchange()
//                .pathMatchers("/api/heroes")
//                .permitAll()
//                .pathMatchers("/api/vigilante/*")
//                .authenticated()
////                .anyExchange().authenticated() // All other URLs require authentication
//                .and()
//                .csrf().disable() // Disable CSRF protection for simplicity
//                .build();
//    }
//
//    @Bean
//    public ReactiveAuthenticationManager authenticationManager(ReactiveUserDetailsService userDetailsService) {
//        return new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
//    }
//}