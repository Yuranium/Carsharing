package ru.yuriy.carsharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.service.CarsService;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarsController
{
    private final CarsService service;
    private List<Car> cars;

    @Autowired
    public CarsController(CarsService service)
    {
        this.service = service;
    }

    @GetMapping
    public String cars(Model model)
    {
        if (cars == null)
        {
            cars = service.findAll();
            model.addAttribute("cars", cars.stream().sorted(Comparator.comparingInt(Car::getId)));
        }
        else model.addAttribute("cars", cars);
        return "cars";
    }

    @GetMapping("/{id}")
    public String car(@PathVariable("id") int id, Model model)
    {
        Car car = service.findById(id);
        model.addAttribute("car", car);
        return "car_profile";
    }
}