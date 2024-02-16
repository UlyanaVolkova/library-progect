package ru.volkova.libraryprogect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.volkova.libraryprogect.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() throws Exception{
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain finalChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable().
                authorizeHttpRequests((authorize) ->
                authorize
                .requestMatchers("/auth/sign_in").permitAll()
                .requestMatchers("/auth/sign_up").hasRole("ADMIN")
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form
                .loginPage("/auth/sign_in")
                .loginProcessingUrl("/process_login")
                .permitAll());
        return http.build();
    }

}
