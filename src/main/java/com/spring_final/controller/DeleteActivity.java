package com.spring_final.controller;

import com.spring_final.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeleteActivity {

    @Autowired
    ActivityService service;

    @RequestMapping("/activityDelete")
    public String deleteActivity(@RequestParam("activity_id") int id){
        service.deleteActivity(id);
        return "redirect:/activities";
    }


}
