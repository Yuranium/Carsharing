package ru.yuriy.carsharing.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.validator.ClientValidator;

@Controller
public class SecurityController
{
    @GetMapping("/registration")
    public String registration(@ModelAttribute("client") Client client)
    {
        return "registration";
    }

    @GetMapping("/login")
    public String login()
    {
        return "authentication";
    }
}