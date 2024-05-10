package ru.yuriy.carsharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.service.ClientsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    private final ClientsService service;

    @Autowired
    public SecurityConfig(ClientsService service)
    {
        this.service = service;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .anonymous(req -> req.authorities(ClientRole.GUEST.getRole()))
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/", "/about_us", "/car/**", "/login", "/logout", "/registration",
                                "client/new","client/login", "client/authentication")
                        /*.permitAll().anyRequest()*/.hasAnyRole("GUEST", "CLIENT", "ADMIN")
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/pictures/**",
                                "/sql/**", "/templates/**", "/errors/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public UserDetailsService service()
    {
        return service;
    }

    @Bean
    public DaoAuthenticationProvider provider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(service);
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}