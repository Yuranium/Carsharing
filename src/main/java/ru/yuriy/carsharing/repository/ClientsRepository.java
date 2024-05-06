package ru.yuriy.carsharing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yuriy.carsharing.models.Client;

import java.util.Optional;

@Repository
public interface ClientsRepository extends CrudRepository<Client, Integer>
{
    Optional<Client> findByName(String name);

    Optional<Client> findByPassword(String password);

    Optional<Client> findByEmail(String email);
}