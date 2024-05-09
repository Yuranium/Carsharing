package ru.yuriy.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.ClientsRepository;

import java.util.Optional;

@Service
public class ClientsService implements UserDetailsService
{
    private final ClientsRepository repository;

    //private final PasswordEncoder encoder;

    @Autowired
    public ClientsService(ClientsRepository repository/*, PasswordEncoder encoder*/)
    {
        this.repository = repository;
        //this.encoder = encoder;
    }

    @Override
    @Transactional(readOnly = true)
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

    @Transactional
    public void deleteUserById(int id)
    {
        repository.deleteById(id);
    }
}