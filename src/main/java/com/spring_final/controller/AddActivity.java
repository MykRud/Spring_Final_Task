package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.TypeOfActivity;
import com.spring_final.service.ActivityService;
import com.spring_final.service.TypeOfActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddActivity {

    @Autowired
    TypeOfActivityService typeService;
    @Autowired
    ActivityService activityService;

    @RequestMapping("/activitiesAdd")
    public ModelAndView addActivityPost(@ModelAttribute("activity") Activity activity){
        ModelAndView mv = new ModelAndView();
        mv.addObject("types", typeService.getTypes());
        if(activity.getName() == null){
            mv.setViewName("WEB-INF/pages/admin/add-activity");
            return mv;
        }
        TypeOfActivity type = typeService.getType(activity.getType().getName());
        if(type != null)
            activity.setType(type);
        activity.setStatus("Pending");
        activityService.addActivity(activity);
        mv.setViewName("redirect:/activities");
        return mv;
    }
}
