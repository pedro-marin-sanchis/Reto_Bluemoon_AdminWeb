package com.uguinformatica.bluemoon_adminweb.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
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
                // RESOURCES
                .requestMatchers("/image/**", "/css/**", "/js/**", "/font/**").permitAll()

                // AUTH
                .requestMatchers("/auth/logout").authenticated()
                .requestMatchers("/auth/**").permitAll()

                // ADMIN
                .requestMatchers("app/admin/**").hasAuthority("ADMIN")

                // APP
                .requestMatchers("app/**").hasAnyAuthority("USER", "ADMIN")

                // ALL DENIED BY DEFAULT
                .anyRequest().denyAll()

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
        ).csrf(Customizer.withDefaults());

        return http.build();
    }

}