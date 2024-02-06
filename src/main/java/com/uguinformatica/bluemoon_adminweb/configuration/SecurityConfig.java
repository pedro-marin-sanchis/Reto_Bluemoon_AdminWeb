package com.uguinformatica.bluemoon_adminweb.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(config -> config
                .requestMatchers("/image/**", "/css/**", "/js/**", "/font/**").permitAll()
                .requestMatchers("/auth/logout").authenticated()
                .requestMatchers("/auth/**").permitAll()
                .anyRequest().hasAuthority("ADMIN")
        ).formLogin(form ->
                form
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .failureUrl("/auth/login?error=true")
                        .defaultSuccessUrl("/app/dashboard", true)
                        .permitAll()
        ).logout(logout ->
                logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                        .permitAll()
        );

        return http.build();
    }

}