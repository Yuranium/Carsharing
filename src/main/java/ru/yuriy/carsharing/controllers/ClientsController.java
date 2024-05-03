package ru.yuriy.carsharing.controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yuriy.carsharing.models.Client;

@Controller
@RequestMapping("/client")
public class ClientsController
{
    @GetMapping("/new")
    public String registration(@ModelAttribute("client") Client client)
    {
        return "registration";
    }

    @PostMapping()
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult result)
    {
        if (result.hasErrors()) {
            System.out.println(client);
            return "registration";
        }
        System.out.println(client);
        return "redirect:/";
    }
}