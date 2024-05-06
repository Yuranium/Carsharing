package ru.yuriy.carsharing.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
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

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//    {
//        http
//                .authorizeRequests(authorize -> authorize
//                        .anyRequest().authenticated())
//                .formLogin(formLogin -> formLogin
//                        .loginPage("/login")
//                        .loginProcessingUrl("/client/login")
//                        .defaultSuccessUrl("/home", true)
//                        .failureUrl("/login?error"));
//        return http.build();
//    }
//        http.authorizeHttpRequests(authz -> authz
//                .requestMatchers("/authentication/**").permitAll()
//                .requestMatchers("/h2/**").permitAll()
//                .anyRequest().authenticated());

    protected void configure(AuthenticationManagerBuilder builder)
    {
        builder.authenticationProvider(provider);
    }
}


