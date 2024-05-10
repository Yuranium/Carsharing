package ru.yuriy.carsharing.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.ClientsRepository;

import java.util.List;
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
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<Client> client = repository.findByName(username);
        if (client.isEmpty())
            throw new UsernameNotFoundException("Такой пользователь не найден!");
        return client.get();
    }

    @Transactional
    public Client findById(int id)
    {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Такой пользователь не найден!"));
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

    @Transactional
    public void deleteCurrentProfile(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null)
        {
            Client client = (Client) authentication.getPrincipal();
            repository.deleteById(client.getId());
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @Transactional
    public void updateCurrentProfile(int id, String name, int age,
                                     String password, String email, int drivingExperience)
    {
        repository.updateClientById(id, name, age, password, email, drivingExperience);
    }
}