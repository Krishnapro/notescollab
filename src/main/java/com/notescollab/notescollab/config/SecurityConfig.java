package com.notescollab.notescollab.config;

import com.notescollab.notescollab.services.UserInfoDetailSevice;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

    private Logger logger = LoggerFactory.getLogger ("com.notescollab.notescollab.config.SecurityConfig");

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers ("/api/auth/**").permitAll ()
                        .requestMatchers ( "/api/greeting" ).permitAll ()
                        .anyRequest ()
                        .authenticated ())
                .httpBasic ( Customizer.withDefaults ())
                .formLogin (Customizer.withDefaults ()).csrf ( AbstractHttpConfigurer::disable );

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
    public AuthenticationProvider authenticationProvider() {
        logger.info("AuthenticationProvider:: start authentication");

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider ();
        authenticationProvider.setUserDetailsService ( userDetailsService () );
        authenticationProvider.setPasswordEncoder ( passwordEncoder () );
        return authenticationProvider;

    }
}
