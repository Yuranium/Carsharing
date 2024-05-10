package ru.yuriy.carsharing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.models.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminsRepository extends CrudRepository<Client, Integer>
{
    List<Client> findByRole(ClientRole role);

    void deleteById(int id);

    Optional<Client> findByName(String name);
}