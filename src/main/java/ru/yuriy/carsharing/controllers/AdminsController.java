package ru.yuriy.carsharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yuriy.carsharing.enums.ClientRole;
import ru.yuriy.carsharing.models.Client;
import ru.yuriy.carsharing.service.AdminsService;
import ru.yuriy.carsharing.service.ClientsService;

import java.util.Comparator;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminsController
{
    private final AdminsService adminsService;

    private final ClientsService clientsService;

    @Autowired
    public AdminsController(AdminsService service, ClientsService clientsService)
    {
        this.adminsService = service;
        this.clientsService = clientsService;
    }

    @GetMapping("/profile")
    public String profile(Model model)
    {
        model.addAttribute("users", adminsService.findByRole(ClientRole.USER)
                .stream().sorted(Comparator.comparingInt(Client::getDrivingExperience))
                .collect(Collectors.toList()));
        return "client_profile";
    }

    @DeleteMapping("/client/{id}")
    public String deleteClient(@PathVariable("id") int id)
    {
        clientsService.deleteUserById(id);
        return "redirect:/client_profile";
    }
}