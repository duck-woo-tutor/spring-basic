package io.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/login-page")
    public ModelAndView loginPage() {
        return new ModelAndView("/login");
    }
}