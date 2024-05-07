package ru.yuriy.carsharing.validator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.ClientsRepository;
import ru.yuriy.carsharing.service.ClientsService;

import java.util.Optional;

@Component
public class ClientValidator implements Validator
{
    private final ClientsRepository repository;

    @Autowired
    public ClientValidator(ClientsRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public boolean supports(Class<?> clazz)
    {
        return ClientsService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        Client client = (Client) target;
        Optional<Client> optional = repository.findByNameAndEmail(client.getName(), client.getEmail());
        if (optional.isPresent())
            errors.rejectValue("userFields", "", "Пользователь с такими данными уже есть!");
    }
}