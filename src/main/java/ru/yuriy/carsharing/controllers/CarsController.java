package ru.yuriy.carsharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yuriy.carsharing.models.Car;
import ru.yuriy.carsharing.repository.CarsRepository;
import ru.yuriy.carsharing.service.CarsService;

import java.util.Comparator;

@Controller
@RequestMapping("/car")
public class CarsController
{
    private final CarsService service;

    @Autowired
    public CarsController(CarsService service)
    {
        this.service = service;
    }

    @GetMapping
    public String cars(Model model)
    {
        model.addAttribute("cars", service.findAll().stream().sorted(Comparator.comparingInt(Car::getId)));
        return "cars";
    }

    @GetMapping("/{id}")
    public String car(@PathVariable("id") int id)
    {
        return null;
    }
}