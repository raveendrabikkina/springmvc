package com.ravi.springmvc.controller;

import com.ravi.springmvc.entity.User;
import com.ravi.springmvc.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("greeting", "Hello Spring Boot+Spring MVC");

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
