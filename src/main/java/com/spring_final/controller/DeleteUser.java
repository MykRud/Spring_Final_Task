package com.spring_final.controller;

import com.spring_final.service.ActivityService;
import com.spring_final.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteUser {

    @Autowired
    UserService service;

    @RequestMapping("/userDelete")
    public String deleteUser(@RequestParam("id") int id){

        if(service.getUser(id) == null)
            return "WEB-INF/pages/admin/users";

        service.deleteUser(id);
        return "redirect:/users";
    }


}
