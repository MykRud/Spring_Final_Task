package com.spring_final.service;

import com.spring_final.daos.hibernateImpl.ActivityDao;
import com.spring_final.daos.hibernateImpl.ActivityRequestDao;
import com.spring_final.model.Activity;
import com.spring_final.model.ActivityRequest;
import com.spring_final.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class ActivityRequestService {

    @Autowired
    private ActivityRequestDao requestDao;
    @Autowired
    private ActivityDao activityDao;

    public void addRequest(ActivityRequest request){
        requestDao.addRequest(request);
    }

    public ActivityRequest getRequest(int id){
        return requestDao.getRequest(id);
    }

    public int getNumberOfRequests(){
        return requestDao.getNumberOfRequests();
    }

    public List<ActivityRequest> getRequestsInLimit(int size, int page){
        return requestDao.getRequestsInLimit(size, page);
    }

    public void makeAddRequest(User user, Activity activity){
        List<ActivityRequest> requests = requestDao.getRequestByUserAndActivity(user, activity);

        if(!requests.isEmpty()) {
            for (ActivityRequest currentRequest : requests) {
                if (!currentRequest.getAction().equals("Add") && !currentRequest.getStatus().equals("Rejected"))
                    return;
            }
        }

        if(activity.getStatus().equals("Completed")){
            return;
        }
        else if(activity.getStatus().equals("Active")){
            return;
        }
        ActivityRequest request = new ActivityRequest();

        request.setUser(user);
        request.setActivity(activity);
        request.setAction("Add");
        request.setStatus("Pending");

        requestDao.addRequest(request);
    }

    @Transactional
    public void makeCompleteRequest(User user, Activity activity) {

        List<ActivityRequest> requests = requestDao.getRequestByUserAndActivity(user, activity); // Error is here!!!

        if(requests.isEmpty())
            return;
        for(ActivityRequest currentRequest : requests){
            if(currentRequest.getAction().equals("Complete") && !currentRequest.getStatus().equals("Rejected")
                    || currentRequest.getAction().equals("Add") && currentRequest.getStatus().equals("Pending"))
                return;
        }

        if(activity.getStatus().equals("Completed")){
            return;
        }
        else if(activity.getStatus().equals("Pending"))
            return;
        ActivityRequest request = new ActivityRequest();
        request.setUser(user);
        request.setActivity(activity);
        request.setAction("Complete");
        request.setStatus("Pending");

        requestDao.addRequest(request);

    }

    public void approveRequest(ActivityRequest request){
        Activity activity = request.getActivity();
        User user = request.getUser();

        String status = activity.getStatus();

        if(status.equals("Pending")){
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
            activity.setStartTime(new Date(startTime.getTime()));
            activity.setStatus("Active");
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            request.setStatus("Approved");

            activityDao.update(activity);
        } else if(status.equals("Active")){
            activity.getUsers().add(user);
            user.getActivities().add(activity);
            request.setStatus("Approved");

            activityDao.update(activity);
        } else if(status.equals("Completed")){
            request.setStatus("Rejected");
        }

        requestDao.update(request);
    }

    public void completeRequest(ActivityRequest request){
        Activity activity = request.getActivity();
        User user = request.getUser();

        String status = activity.getStatus();

        if(status.equals("Pending")){
            return;
        } else if(status.equals("Active")){
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now());
            activity.setEndTime(new Date(startTime.getTime()));
            activity.setStatus("Completed");
            request.setStatus("Approved");

            activityDao.update(activity);
        } else if(status.equals("Completed")){
            request.setStatus("Rejected");
        }

        requestDao.update(request);
    }

    public void rejectRequest(ActivityRequest request){
        request.setStatus("Rejected");
        requestDao.update(request);
    }

    public List<ActivityRequest> getRequests() {
        return requestDao.getRequests();
    }
}
