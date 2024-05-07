package ru.yuriy.carsharing.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminsController
{
    @GetMapping("profile")
    public String profile()
    {
        return "client_profile";
    }
}