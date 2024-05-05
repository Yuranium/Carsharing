package ru.yuriy.carsharing.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yuriy.carsharing.models.Client;

@Controller
@RequestMapping("/client")
public class ClientsController {
    @GetMapping("/new")
    public String registration(@ModelAttribute("client") Client client) {
        return "registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(client);
            return "registration";
        }
        System.out.println(client);
        return "redirect:/";
    }

    @GetMapping("/authentication")
    public String authentication(@ModelAttribute("client") Client client) {
        return "authentication";
    }

    @PostMapping("/login")
    public String input(@ModelAttribute("client") @Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(client);
            return "authentication";
        }
        System.out.println(client);
        return "redirect:client_profile";
    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable("id") int id)
    {
        return null;
    }
}