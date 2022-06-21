package com.spring_final.controller;

import com.spring_final.model.User;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class Profile {

    @Autowired
    UserService service;

    @RequestMapping("/profile")
    public ModelAndView profile(HttpSession session){
        User user = (User) session.getAttribute("authUser");
        user = service.getUser(user.getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("/WEB-INF/pages/profile");
        return mv;
    }

}
