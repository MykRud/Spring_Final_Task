package com.spring_final.controller;

import com.spring_final.model.Authority;
import com.spring_final.model.User;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Registration {

    @Autowired
    UserService service;

    @RequestMapping("/registration")
    public String registration(@ModelAttribute("user")User user){
        if(user.getFirstName() == null || user.getLastName() == null ||
        user.getUsername() == null || user.getPassword() == null ||
        user.getAge() == 0 || user.getGender() == null ||
        user.getContact() == null)
            return "registration";

        User foundUser = service.getUser(user.getUsername());

        if(foundUser != null)
            return "registration";

        service.addUser(user);

        return "redirect:/login";
    }

}
