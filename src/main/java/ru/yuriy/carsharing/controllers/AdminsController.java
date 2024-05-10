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
import ru.yuriy.carsharing.service.AdminsService;
import ru.yuriy.carsharing.validator.ClientValidator;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController
{
    private final AdminsService adminsService;

    private final PasswordEncoder encoder;

    @Autowired
    public AdminsController(AdminsService service, PasswordEncoder encoder)
    {
        this.adminsService = service;
        this.encoder = encoder;
    }

    @GetMapping("/profile")
    public String profile(Model model)
    {
        Client client = adminsService.findByName(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("client", client);
        List<Client> clients = adminsService.findByRole(ClientRole.CLIENT)
                .stream()
                .sorted(Comparator.comparingInt(Client::getDrivingExperience))
                .toList();
        model.addAttribute("users", clients);
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

    @GetMapping("/update")
    public String update(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Client client = (Client) authentication.getPrincipal();
        model.addAttribute("current_profile", adminsService.findById(client.getId()));
        return "profile_update";
    }

    @PatchMapping("/update")
    public String updateAdmin(@ModelAttribute("current_profile") @Valid Client client, BindingResult result, Model model)
    {
        Client temp = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        client.setRole(temp.getRole());
        if (result.hasErrors())
            return "profile_update";
        adminsService.updateCurrentProfile(temp.getId(), client.getName(), client.getAge(), client.getPassword(),
                client.getEmail(), client.getDrivingExperience());
        model.addAttribute("client", adminsService.findById(temp.getId()));
        List<Client> clients = adminsService.findByRole(ClientRole.CLIENT)
                .stream()
                .sorted(Comparator.comparingInt(Client::getDrivingExperience))
                .toList();
        client.setRole(temp.getRole());
        client.setPassword(encoder.encode(client.getPassword()));
        adminsService.updateCurrentProfile(temp.getId(), client.getName(), client.getAge(), client.getPassword(), client.getEmail(), client.getDrivingExperience());
        model.addAttribute("users", clients);
        return "client_profile";
    }
}