package br.com.iteam.infrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChain {

    @Bean
    public org.springframework.security.web.SecurityFilterChain filterChain(
            HttpSecurity http,
            CustomBasicAuthFilter customBasicAuthFilter) throws Exception {

        http
                .authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers(
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/h2-console/**"
                    ).permitAll();

                    authorizeConfig.requestMatchers(HttpMethod.GET, "/api/products/**").permitAll();
                    authorizeConfig.requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll();

                    authorizeConfig.requestMatchers(HttpMethod.POST, "/api/products/**").hasAuthority("ROLE_ADMIN");
                    authorizeConfig.requestMatchers(HttpMethod.PUT, "/api/products/**").hasAuthority("ROLE_ADMIN");
                    authorizeConfig.requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAuthority("ROLE_ADMIN");

                    authorizeConfig.anyRequest().authenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(customBasicAuthFilter, BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}