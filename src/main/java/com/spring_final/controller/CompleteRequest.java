package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.User;
import com.spring_final.service.ActivityRequestService;
import com.spring_final.service.ActivityService;
import com.spring_final.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

@Controller
public class CompleteRequest {

    @Autowired
    ActivityService activityService;
    @Autowired
    ActivityRequestService requestService;
    @Autowired
    UserService userService;

    @RequestMapping("/activityRequestComplete")
    public String completeRequest(@RequestParam("activity_id") int activityId, HttpSession session){
        User user = (User) session.getAttribute("authUser");
        Activity activity = activityService.getActivity(activityId);
        requestService.makeCompleteRequest(user, activity);
        return "redirect:/activities";

        /*User user = (User) session.getAttribute("authUser");
        Activity activity = activityService.getActivity(activityId);
        requestService.makeCompleteRequest(user, activity);

        return "redirect:/activities";*/
    }

}
