package ru.yuriy.carsharing.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.repository.CarsRepository;
import ru.yuriy.carsharing.repository.ClientsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarsService
{
    private final CarsRepository repository;

    private final ClientsRepository clientsRepository;

    @Autowired
    public CarsService(CarsRepository repository, ClientsRepository clientsRepository)
    {
        this.repository = repository;
        this.clientsRepository = clientsRepository;
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

    @Transactional
    public void rentCar(int id)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = (Client) authentication.getPrincipal();
        Car car = findById(id);
//        car.setOwner(client);
//        repository.save(car);
//        client.setCars(List.of(car));
        clientsRepository.save(client);
        //System.out.println(car.getOwner().getCars());
        //client.getCars().add(car);
    }
}