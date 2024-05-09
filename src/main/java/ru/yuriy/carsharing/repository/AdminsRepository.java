package ru.yuriy.carsharing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.models.Client;

import java.util.List;

@Repository
public interface AdminsRepository extends CrudRepository<Client, Integer>
{
    List<Client> findByRole(ClientRole role);
}