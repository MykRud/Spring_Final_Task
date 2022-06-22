package com.spring_final.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class ActivityRequest implements Serializable{
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Activity activity;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private String action;
    private String status;

    public ActivityRequest(int id, Activity activity, User user, String action, String status) {
        this.id = id;
        this.activity = activity;
        this.user = user;
        this.action = action;
        this.status = status;
    }

    public ActivityRequest(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return user.getUsername() + " - " + activity.getName() + " - "
                + activity + " - " + status;
    }
}
