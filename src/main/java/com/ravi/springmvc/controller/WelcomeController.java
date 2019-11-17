package com.ravi.springmvc.controller;

import com.ravi.springmvc.annotation.Loggable;
import com.ravi.springmvc.entity.User;
import com.ravi.springmvc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WelcomeController {

    @Autowired
    private UserRepo userRepo;

    @Loggable
    @RequestMapping("/hello")
    public String sayHello(Model model, @RequestParam String name) {

        model.addAttribute("user", new User());
        model.addAttribute("greeting", "Hello Spring Boot+Spring MVC");
        model.addAttribute("name", name);

        return "welcome";
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String sayHello(@ModelAttribute User user, Model model) {
        User userSaved = userRepo.save(user);
        model.addAttribute("user", userSaved);

        return "welcome";
    }

    @RequestMapping(value = "/deleteuser/{userId}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable long userId, Model model) {
        User userToDelete = userRepo.getOne(userId);
        userRepo.delete(userToDelete);
        model.addAttribute("user", userToDelete);

        return "welcome";
    }

}
