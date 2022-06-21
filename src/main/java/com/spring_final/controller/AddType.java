package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.TypeOfActivity;
import com.spring_final.service.ActivityService;
import com.spring_final.service.TypeOfActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddType {

    @Autowired
    TypeOfActivityService service;

    @RequestMapping("/typesAdd")
    public String addType(@ModelAttribute("type")TypeOfActivity type){
        if(type.getName() == null)
            return "WEB-INF/pages/admin/add-type";
        if(service.findType(type.getName()) != null)
            return "WEB-INF/pages/admin/add-type";
        service.addType(type);
        return "redirect:/activities";
    }

}
