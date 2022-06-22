package com.spring_final.controller;

import com.spring_final.model.User;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UpdateUser {

    @Autowired
    UserService service;

    @RequestMapping("/userUpdate")
    public String updateUser(@ModelAttribute("user") User user) {
        if(user.getFirstName() == null || user.getLastName() == null ||
                user.getUsername() == null ||
                user.getAge() == 0 || user.getGender() == null ||
                user.getContact() == null)
            return "WEB-INF/pages/admin/update-user";

        service.updateUser(user);

        return "redirect:/users";
    }
}
