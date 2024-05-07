package ru.yuriy.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.ClientsRepository;

import java.util.Optional;

@Service
public class ClientsService /*implements UserDetailsService*/
{
    private final ClientsRepository repository;

    @Autowired
    public ClientsService(ClientsRepository repository)
    {
        this.repository = repository;
    }

    public Client findClient(String name)
    {
        return repository.findByName(name).orElse(null);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Client> client = repository.findByName(username);
        if (client.isEmpty())
            throw new UsernameNotFoundException("Такой пользователь не найден!");
        System.out.println("Успешно  " + client.get());
        return client.get();
    }
}