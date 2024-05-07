package ru.yuriy.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.ClientsRepository;

import java.util.Optional;

@Service
public class ClientsService implements UserDetailsService
{
    private final ClientsRepository repository;

    @Autowired
    public ClientsService(ClientsRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Client> client = repository.findByName(username);
        if (client.isEmpty())
            throw new UsernameNotFoundException("Такой пользователь не найден!");
        return client.get();
    }

    @Transactional
    public void save(Client client)
    {
        repository.save(client);
    }
}