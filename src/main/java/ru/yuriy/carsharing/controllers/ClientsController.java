package ru.yuriy.carsharing.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yuriy.carsharing.models.Client;

@Controller
@RequestMapping("/client")
public class ClientsController
{
//    private final ClientsService service;
//
//    @Autowired
//    public ClientsController(ClientsService service)
//    {
//        this.service = service;
//    }

    @GetMapping("/new")
    public String registration(@ModelAttribute("client") Client client) {
        return "registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Ошибка: " + client);
            return "registration";
        }
        System.out.println("Стабильно: " + client);
        return "redirect:/";
    }

    @GetMapping("/authentication")
    public String authentication(@ModelAttribute("client") Client client) {
        return "authentication";
    }

    @PostMapping("/login")
    public String input(@ModelAttribute("client") @Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("Ошибка: " + client);
            return "authentication";
        }
        System.out.println("Стабильно: " + client);
        return "redirect:/client_profile";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") int id)
    {
        System.out.println(id);
        return "client_profile";
    }
}