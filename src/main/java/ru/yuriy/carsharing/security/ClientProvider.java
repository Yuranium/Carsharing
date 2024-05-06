package ru.yuriy.carsharing.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.yuriy.carsharing.service.ClientsService;

import java.util.Collections;

@Component
public class ClientProvider implements AuthenticationProvider
{
    private final ClientsService service;

    @Autowired
    public ClientProvider(ClientsService service)
    {
        this.service = service;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException
    {
        String name = authentication.getName();
        UserDetails client = service.loadUserByUsername(name);
        String password = authentication.getCredentials().toString();

        if (!password.equals(client.getPassword()))
            throw new BadCredentialsException("Неверный пароль!");
        return new UsernamePasswordAuthenticationToken(client, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication)
    {
        return true;
    }
}