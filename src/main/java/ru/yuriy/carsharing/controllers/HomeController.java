package ru.yuriy.carsharing.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping("/")
    public String homePage()
    {
        return "home";
    }

    @GetMapping("/about_us")
    public String AboutUs()
    {
        return "about_us";
    }

    @GetMapping("/test")
    public String test()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        System.out.println(user.getPassword() + "  " + user.getUsername());
        return "home";
    }
}