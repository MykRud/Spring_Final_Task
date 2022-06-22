package com.spring_final.service;

import com.spring_final.daos.hibernateImpl.ActivityDao;
import com.spring_final.model.Activity;
import com.spring_final.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class ActivityService {

    @Autowired
    private ActivityDao activityDao;

    public void addActivity(Activity activity){
        activityDao.addActivity(activity);
    }

    public Activity getActivity(int id){
        return activityDao.getActivity(id);
    }

    public Activity getActivity(String name){
        return activityDao.getActivity(name);
    }

    public List<Activity> getActivities(){
        return activityDao.getActivities();
    }

    public int getNumberOfActivities(){
        return activityDao.getNumberOfActivities();
    }

    public List<Activity> getActivitiesInLimit(int size, int page){
        return activityDao.getActivitiesInLimit(size, page);
    }

    public List<Activity> getActivitiesInLimitByName(int size, int page) {
        return activityDao.getActivitiesInLimitByName(size, page);
    }

    public List<Activity> getActivitiesInLimitByType(int size, int page) {
        return activityDao.getActivitiesInLimitByType(size, page);
    }

    @Transactional
    public void deleteActivity(int id){
        activityDao.delete(id);
    }

    public void takeActivityTime(int id, User user, int duration){
        Activity activity = activityDao.getActivity(id);
        if(activity.getStatus().equals("Active")){
            activity.setDuration(duration);
            activityDao.update(activity);
        }
    }

    @Transactional
    public void updateActivity(Activity activity){
        activityDao.update(activity);
    }
}
