package ru.yuriy.carsharing.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    public AdminsController(AdminsService service)
    {
        this.adminsService = service;
    }

    @GetMapping("/profile")
    public String profile(Model model)
    {
        model.addAttribute("users", adminsService.findByRole(ClientRole.CLIENT)
                .stream().sorted(Comparator.comparingInt(Client::getDrivingExperience))
                .collect(Collectors.toList()));
        return "client_profile";
    }

    @DeleteMapping("/client/{id}")
    public String deleteClient(@PathVariable("id") int id)
    {
        adminsService.deleteById(id);
        return "redirect:/client_profile";
    }

    @DeleteMapping("/delete")
    public String deleteAdmin(HttpServletRequest request, HttpServletResponse response)
    {
        adminsService.deleteCurrentProfile(request, response);
        return "redirect:/";
    }
}