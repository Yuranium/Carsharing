package ru.yuriy.carsharing.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.service.ClientsService;
import ru.yuriy.carsharing.validator.ClientValidator;

@Controller
@RequestMapping("/client")
public class ClientsController
{
    private final ClientsService service;

    private final ClientValidator validator;

    private final PasswordEncoder encoder;


    @Autowired
    public ClientsController(ClientsService service, ClientValidator validator, PasswordEncoder encoder)
    {
        this.service = service;
        this.validator = validator;
        this.encoder = encoder;
    }

    @GetMapping("/new")
    public String registration(@ModelAttribute("client") Client client) {
        return "registration";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("client") @Valid Client client, BindingResult result)
    {
        validator.validate(client, result);
        if (result.hasErrors())
            return "registration";
        client.setRole(ClientRole.CLIENT);
        client.setPassword(encoder.encode(client.getPassword()));
        service.save(client);
        System.out.println("Стабильно: " + client);
        return "redirect:/";
    }

    @GetMapping("/authentication")
    public String authentication(@ModelAttribute("client") Client client) {
        return "authentication";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("client") @Valid Client client, BindingResult result)
    {
        if (result.hasErrors()) {
            System.out.println("Ошибка: " + client);
            return "authentication";
        }
        System.out.println("Стабильно: " + client);
        return "redirect:/client_profile";
    }

    @GetMapping("/profile")
    public String profile(Model model)
    {
        Client client = (Client) service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("client", client);
        return "client_profile";
    }

    @DeleteMapping("/delete")
    public String deleteClient(HttpServletRequest request, HttpServletResponse response)
    {
        service.deleteCurrentProfile(request, response);
        return "redirect:/";
    }

    @PatchMapping("/update")
    public String updateAdmin()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = (Client) authentication.getPrincipal();
        service.updateCurrentProfile(client.getId(), client.getCars(), client.getName(), client.getAge(), client.getPassword(),
                client.getEmail(), client.getDrivingExperience());
        return "client_profile";
    }
}