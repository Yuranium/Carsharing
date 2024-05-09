package ru.yuriy.carsharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.service.ClientsService;


@Controller
public class HomeController
{
    private final ClientsService service;

    @Autowired
    public HomeController(ClientsService service)
    {
        this.service = service;
    }

    @GetMapping("/")
    public String homePage(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser"))
            model.addAttribute("auth", null);
        else
        {
            Client client = (Client) service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("auth", client);
        }
        return "home";
    }

    @GetMapping("/about_us")
    public String AboutUs()
    {
        return "about_us";
    }
}