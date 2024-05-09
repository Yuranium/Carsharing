package ru.yuriy.carsharing.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.repository.CarsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarsService
{
    CarsRepository repository;

    @Autowired
    public CarsService(CarsRepository repository)
    {
        this.repository = repository;
    }

    public List<Car> findAll()
    {
        return (List<Car>) repository.findAll();
    }

    public Car findById(int id)
    {
        Optional<Car> car = repository.findById(id);
        return car.orElseThrow(() -> new EntityNotFoundException("vehicle with this ID was not found in the database!"));
    }
}