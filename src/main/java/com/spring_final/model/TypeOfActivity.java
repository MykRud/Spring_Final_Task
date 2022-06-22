package com.spring_final.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class TypeOfActivity implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private List<Activity> activities = new ArrayList<>();

    public TypeOfActivity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public TypeOfActivity(String name) {
        this.name = name;
    }

    public TypeOfActivity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString(){
        return name;
    }
}
