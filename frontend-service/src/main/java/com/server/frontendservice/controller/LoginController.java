package com.server.frontendservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login")
    public void login(Model model) {
        model.addAttribute("showNavigation", false);
    }

    @GetMapping("/logout")
    public void logout(Map<String, Object> model) {
        model.put("logout", Boolean.valueOf(true));
    }
}