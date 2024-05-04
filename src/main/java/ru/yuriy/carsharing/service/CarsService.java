package ru.yuriy.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.repository.CarsRepository;

import java.util.List;

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
        System.out.println(repository.findAll());
        return (List<Car>) repository.findAll();
    }
}