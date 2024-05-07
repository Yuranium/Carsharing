package ru.yuriy.carsharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import ru.yuriy.carsharing.security.ClientProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration
{
    private final ClientProvider provider;

    @Autowired
    public SecurityConfig(ClientProvider provider)
    {
        this.provider = provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/", "/about_us", "/car", "/login", "/registration",
                                "client/new","client/login", "client/authentication").permitAll()
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/pictures/**",
                                "/sql/**", "/templates/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        /*.loginProcessingUrl("/client/login")*/
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll);
//        http
//                .authorizeHttpRequests(c -> c
//                        .requestMatchers("/login").permitAll()
//                        .anyRequest().authenticated())
//                .exceptionHandling(c ->
//                        // основная точка входа
//                        c.authenticationEntryPoint(
//                                (req, res, ex) -> res.sendRedirect("/login")));
        return http.build();
    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    protected void configure(AuthenticationManagerBuilder builder)
    {
        builder.authenticationProvider(provider);
    }
}