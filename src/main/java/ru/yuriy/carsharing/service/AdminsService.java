package ru.yuriy.carsharing.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.enums.ClientRole;
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
}