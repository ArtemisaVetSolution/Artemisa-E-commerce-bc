package com.riwi.artemisa.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/api/category/user/readAll").permitAll()

                        .requestMatchers("/v1/api/category/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/category/user/**").hasRole("TUTOR")

                        .requestMatchers("/v1/api/media/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/media/user/**").hasRole("TUTOR")

                        .requestMatchers("/v1/api/medication/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/medication/user/**").hasRole("TUTOR")

                        .requestMatchers("/v1/api/medicationInventory/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/medicationInventory/user/**").hasRole("TUTOR")

                        .requestMatchers("/v1/api/productInventory/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/productInventory/user/**").hasRole("TUTOR")

                        .requestMatchers("/v1/api/order/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/order/user/**").hasRole("TUTOR")

                        .requestMatchers("/v1/api/petshopPayments/admin/**").hasRole("ADMIN")
                        .requestMatchers("/v1/api/petshopPayments/user/**").hasRole("TUTOR")
                        .anyRequest().authenticated()

                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(AbstractHttpConfigurer::disable)

                .formLogin(AbstractHttpConfigurer::disable)

                .authenticationProvider(jwtAuthenticationProvider); // Registra el proveedor

        http.addFilterBefore(new JwtRequestFilter(jwtService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}