package ru.yuriy.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Client> findByRole(ClientRole role)
    {
        return repository.findByRole(role);
    }
}
