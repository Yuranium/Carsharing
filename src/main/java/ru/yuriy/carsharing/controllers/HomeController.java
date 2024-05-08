package ru.yuriy.carsharing.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController
{

    @GetMapping("/")
    public String homePage(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth", (authentication.getPrincipal().equals("anonymousUser")) ? null : authentication);
        return "home";
    }

    @GetMapping("/about_us")
    public String AboutUs()
    {
        return "about_us";
    }
}