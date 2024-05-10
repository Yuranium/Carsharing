package ru.yuriy.carsharing.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.models.Client;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientsRepository extends CrudRepository<Client, Integer>
{
    Optional<Client> findByName(String name);

    Optional<Client> findByNameAndEmail(String name, String email);

    @Transactional
    @Modifying
    @Query("UPDATE Client c SET c.name = :name, c.age = :age, c.password = :password, c.email = :email," +
            "c.drivingExperience = :drivingExperience WHERE c.id = :id")
    void updateClientById(int id, String name, int age, String password, String email, int drivingExperience);

    void deleteById(int id);
}