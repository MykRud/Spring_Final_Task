package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.User;
import com.spring_final.service.ActivityService;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GetUsers {

    @Autowired
    UserService service;

    @RequestMapping("/users")
    public ModelAndView getUsers(){
        ModelAndView mv = new ModelAndView();
        List<User> users = service.getUsers();

        mv.addObject("users", users);
        mv.setViewName("WEB-INF/pages/admin/users");
        return mv;
    }

}
