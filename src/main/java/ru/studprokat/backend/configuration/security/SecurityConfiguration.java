package ru.studprokat.backend.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.PATCH, "/renting/users/{userId}").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/renting/users/{userId}").authenticated()

                .requestMatchers(HttpMethod.POST, "/renting/coupons").authenticated()
                .requestMatchers(HttpMethod.POST, "/renting/users/{userId}/wallet").authenticated()
                .requestMatchers(HttpMethod.POST, "/renting/users/{userId}/transaction").authenticated()
                .requestMatchers(HttpMethod.GET, "/renting/users/{userId}/wallet").authenticated()
                .requestMatchers(HttpMethod.PATCH, "/renting/users/{userId}/wallet").authenticated()

                .requestMatchers(HttpMethod.DELETE, "/renting/products/{productId}").authenticated()
                .requestMatchers(HttpMethod.POST, "/renting/products").authenticated()

                .requestMatchers(HttpMethod.GET, "/auth/success").authenticated()
                .anyRequest().permitAll()
        );
        http.cors(configurer -> configurer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()));
        http.csrf(AbstractHttpConfigurer::disable);
        http.apply(new CustomConfigurer<>());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
