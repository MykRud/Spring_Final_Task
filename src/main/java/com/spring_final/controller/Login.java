package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.User;
import com.spring_final.service.ActivityService;
import com.spring_final.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Login {

    @Autowired
    UserService service;

    @RequestMapping("/login")
    public String addActivityPost(@ModelAttribute("user") User user, HttpServletRequest request){
        String username = user.getUsername();
        String password = user.getPassword();
        if(username == null || password == null)
            return "login";
        User us = service.getUser(username);

        if(us == null){
            return "login";
        }

        if(!BCrypt.checkpw(password, us.getPassword())){
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("authUser", us);

        return "redirect:home";
    }

}
