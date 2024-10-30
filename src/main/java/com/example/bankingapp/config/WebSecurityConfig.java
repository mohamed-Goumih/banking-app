//package com.example.bankingapp.config;
//
//import com.example.bankingapp.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)  // Activer la sécurité basée sur les annotations
//public class WebSecurityConfig {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // Désactiver CSRF, pour des APIs REST stateless
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")  // Accès réservé aux admins
//                        .requestMatchers("/api/client/**").hasRole("CLIENT") // Accès réservé aux clients
//                        .anyRequest().authenticated()  // Toutes les autres requêtes nécessitent une authentification
//                )
//                .httpBasic();  // Utilisation de l'authentification HTTP basique pour le développement
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder)
//                .and().build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
