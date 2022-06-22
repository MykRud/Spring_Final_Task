package com.spring_final.controller;

import com.spring_final.model.User;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UpdateProfile {

    @Autowired
    UserService service;

    @RequestMapping("/userProfileUpdate")
    public String updateProfile(@ModelAttribute("user") User user, HttpSession session){
        if(user.getFirstName() == null || user.getLastName() == null ||
                user.getUsername() == null || user.getPassword() == null ||
                user.getAge() == 0 ||
                user.getContact() == null)
            return "WEB-INF/pages/profile-update";

        user.setId(((User) session.getAttribute("authUser")).getId());
        service.updateProfile(user);

        return "redirect:/profile";
    }

}
