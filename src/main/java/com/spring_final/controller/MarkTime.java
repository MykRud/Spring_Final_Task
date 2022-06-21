package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.User;
import com.spring_final.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MarkTime {

    @Autowired
    ActivityService service;

    @RequestMapping("/markTime")
    public String markTime(@RequestParam("activity_id") int activityId,
                           @RequestParam("days") int days,
                           @RequestParam("hours") int hours,
                           @RequestParam("minutes") int minutes,
                           HttpSession session){

        User user = (User) session.getAttribute("authUser");
        Activity activity = service.getActivity(activityId);

        boolean canMatkTime = false;
        for(User us : activity.getUsers()){
            if(us.getUsername().equals(user.getUsername())){
                canMatkTime = true;
                break;
            }
        }

        if(!canMatkTime)
            return "WEB-INF/pages/activities";

        int duration = minutes + hours * 60 + days * 24 * 60;

        service.takeActivityTime(activityId, user, duration);

        return "redirect:/activities";
    }

}
