package com.notescollab.notescollab.config;

import com.notescollab.notescollab.services.UserInfoDetailSevice;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.config.SecurityConfig");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers ("/api/auth/**","/api/greeting").permitAll ()
                        .anyRequest ()
                        .authenticated ())
                .httpBasic (Customizer.withDefaults () )
                .formLogin (form ->  form
                        .loginPage ("/login")
                        .failureHandler ( customAuthenticationFailureHandler () )).csrf ( AbstractHttpConfigurer::disable )
                .exceptionHandling (exception -> exception.authenticationEntryPoint (
                        (request, response, authException) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//                            response.setContentType("application/json");
                            response.getWriter().write("Authentication Failed: Username/Password is invalid");
                        }
                ));

        return http.build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        logger.info("UserDetailsService:: userDetailsService");
        return new UserInfoDetailSevice ();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        logger.info("PasswordEncoder:: start password encoding");
        return new BCryptPasswordEncoder ();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler(){
        return new CustomAuthenticationFailureHandler();
    }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        logger.info("AuthenticationProvider:: start authentication");

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider ();
        authenticationProvider.setUserDetailsService ( userDetailsService () );
        authenticationProvider.setPasswordEncoder ( passwordEncoder () );
        return authenticationProvider;

    }
}
