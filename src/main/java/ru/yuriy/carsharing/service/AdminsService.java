package ru.yuriy.carsharing.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.AdminsRepository;

import java.util.List;

@Service
public class AdminsService
{
    private final AdminsRepository repository;

    @Autowired
    public AdminsService(AdminsRepository repository)
    {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Client> findByRole(ClientRole role)
    {
        return repository.findByRole(role);
    }

    @Transactional
    public Client findByName(String name)
    {
        return repository.findByName(name).orElseThrow(() -> new UsernameNotFoundException("Такой пользователь не найден!"));
    }

    @Transactional
    public Client findById(int id)
    {
        return repository.findById(id).orElseThrow(() -> new UsernameNotFoundException("Такой пользователь не найден!"));
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
    public void save(Client client)
    {
        repository.save(client);
    }

    @Transactional
    public void deleteById(int id)
    {
        repository.deleteById(id);
    }

    @Transactional
    public void updateCurrentProfile(int id, String name, int age,
                                     String password, String email, int drivingExperience)
    {
        repository.updateClientById(id, name, age, password, email, drivingExperience);
    }
}