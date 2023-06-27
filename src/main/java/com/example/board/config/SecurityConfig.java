package com.example.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static javax.management.Query.and;
import static org.springframework.security.config.annotation.web.configurers.PermitAllSupport.permitAll;
import static sun.net.ftp.FtpDirEntry.Permission.USER;

@Configuration
public class SecurityConfig {

//    @Bean
//  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth -> auth
//              .requestMatchers(PathRequest.to("/login"))
//                .anyRequest().permitAll()
//        );
//        return http.build();
// 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http.authorizeHttpRequests().requestMatchers("/login").hasRole(String.valueOf(USER)).and().formLogin();
//         		return http.build();
//         }

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web.ignoring().requestMatchers("/login");
        }







}
