package com.spring_final.service;

import com.spring_final.daos.hibernateImpl.TypesOfActivitiesDao;
import com.spring_final.model.TypeOfActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TypeOfActivityService {

    @Autowired
    private TypesOfActivitiesDao typeDao;

    public void addType(TypeOfActivity type){
        typeDao.addType(type);
    }

    public List<TypeOfActivity> getTypes(){
        return typeDao.getTypes();
    }

    public TypeOfActivity findType(int id){
        return typeDao.getType(id);
    }

    public TypeOfActivity findType(String name){
        return typeDao.getType(name);
    }

    public TypeOfActivity getType(String name) {
        return typeDao.getType(name);
    }
}
