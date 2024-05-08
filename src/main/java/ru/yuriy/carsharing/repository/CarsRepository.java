package ru.yuriy.carsharing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yuriy.carsharing.models.Car;

import java.util.Optional;

@Repository
public interface CarsRepository extends CrudRepository<Car, Integer>
{
    Optional<Car> findById(int id);
}