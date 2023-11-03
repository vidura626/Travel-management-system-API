package lk.ijse.vehicleservice.config;

import jakarta.servlet.http.HttpServletRequest;
import lk.ijse.vehicleservice.filter.CsrfCookieFilter;
import lk.ijse.vehicleservice.filter.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(configurer -> configurer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("lb://api-gateway/"));
                        configuration.setAllowedMethods(List.of("*"));
                        configuration.setAllowedHeaders(List.of("*"));
                        configuration.setMaxAge(3600L);
                        configuration.setAllowCredentials(true);
                        configuration.setExposedHeaders(List.of("Authorization"));
                        return configuration;
                    }
                }))
//                CSRF Configurations
                .csrf(configurer -> {
                    configurer.csrfTokenRequestHandler(requestHandler)
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                })
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(req -> {
                    req
                            .requestMatchers("/api/vehicle/register",
                                    "/api/vehicle/update",
                                    "/api/vehicle/delete/{vehicleId}").hasRole("ADMIN")
                            .requestMatchers("/api/vehicle/all",
                                    "/api/vehicle").permitAll();
                });
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
