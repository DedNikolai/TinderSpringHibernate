package com.tinder.controller;

import com.tinder.entity.User;
import com.tinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(path = "/")
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.getModel().put("user", user);
        model.setViewName("login");
        return model;
    }

    @PostMapping(path = "/", params = {"action=registration"})
    public String registerForm() {
        return "redirect:/registration";
    }

    @PostMapping(path = "/", params = {"action=login"})
    public String getUserProfile(@ModelAttribute User user, Model model) {
        User currentUser = userService.getUserByLogin(user.getLogin());
        if (currentUser != null && currentUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("user", currentUser);
            return "redirect:/user/" + currentUser.getLogin();
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("notAllow", "");
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationNewUser(@ModelAttribute User user, Model model) {
        User checkUser = userService.getUserByLogin(user.getLogin());
        if (checkUser != null) {
            User newUser = new User();
            model.addAttribute("user", newUser);
            model.addAttribute("notAllow", "This login is already exist");
            return "registration";
        }
        userService.addUser(user);
        model.addAttribute("user", user);
        return "redirect:/user/" + user.getLogin();
    }
}
