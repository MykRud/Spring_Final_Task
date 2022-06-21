package com.spring_final.controller;

import com.spring_final.model.ActivityRequest;
import com.spring_final.service.ActivityRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ApproveRequest {

    @Autowired
    ActivityRequestService service;

    @RequestMapping("/activityRequestApprove")
    public String approveRequest(@RequestParam("id") int id){
        ActivityRequest request = service.getRequest(id);

        if(!request.getStatus().equals("Pending")){
            return "redirect:/activitiesRequests";
        }

        if(request.getAction().equals("Add")){
            service.approveRequest(request);
        } else if(request.getAction().equals("Complete")){
            service.completeRequest(request);
        }

        return "redirect:/activitiesRequests";
    }

}
