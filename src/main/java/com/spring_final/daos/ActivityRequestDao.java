package com.spring_final.daos;

import com.spring_final.model.Activity;
import com.spring_final.model.ActivityRequest;
import com.spring_final.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository //@Component
@Transactional
public class ActivityRequestDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;

    public void addRequest(ActivityRequest request){
        sessionFactory.getCurrentSession().save(request);
    }

    public ActivityRequest getRequest(int id){
        ActivityRequest request = sessionFactory.getCurrentSession().
                get(ActivityRequest.class, id);//createQuery("FROM ActivityRequest WHERE id= :id").setParameter("id", id).uniqueResult();
        return request;
    }

    public List<ActivityRequest> getRequestByUserAndActivity(User user, Activity activity){
        List<ActivityRequest> requests = (List<ActivityRequest>) sessionFactory.getCurrentSession().createQuery("FROM ActivityRequest WHERE user_id = :userId AND activity_id = :activityId")
                .setParameter("userId", user.getId())
                .setParameter("activityId", activity.getId())
                .getResultList();
        return requests;
    }

    public List<ActivityRequest> getRequestByUser(User user){
        List<ActivityRequest> requests = (List<ActivityRequest>) sessionFactory.getCurrentSession().createQuery("FROM ActivityRequest WHERE user_id = :userId")
                .setParameter("userId", user.getId())
                .uniqueResult();
        return requests;
    }

    public List<ActivityRequest> getRequestByActivity(Activity activity){
        List<ActivityRequest> requests = (List<ActivityRequest>) sessionFactory.getCurrentSession().createQuery("FROM ActivityRequest WHERE activity_id = :activityId")
                .setParameter("activityId", activity.getId())
                .uniqueResult();
        return requests;
    }

    public List<ActivityRequest> getRequests(){
        List<ActivityRequest> requests;
        requests = (List<ActivityRequest>) sessionFactory.getCurrentSession().createQuery("FROM ActivityRequest").getResultList();
        return requests;
    }

    public void delete(int id){
        ActivityRequest request = getRequest(id);
        sessionFactory.getCurrentSession().remove(request);
    }

    public void delete(Activity activity){
        List<ActivityRequest> requests = getRequestByActivity(activity);
        for(ActivityRequest request : requests)
            sessionFactory.getCurrentSession().remove(request);
    }

    public void delete(User user){
        List<ActivityRequest> requests = getRequestByUser(user);
        for(ActivityRequest request : requests)
            sessionFactory.getCurrentSession().remove(request);
    }

    public List<ActivityRequest> getRequestsInLimit(int size, int page) {
        List<ActivityRequest> requests;

        requests = sessionFactory.getCurrentSession().createSQLQuery("SELECT * from ActivityRequest order by id LIMIT :page, :size")
                .setParameter("page", size * page)
                .setParameter("size", size)
                .getResultList();
        return requests;
    }


    public int getNumberOfRequests(){
        int numberOfRequests = 0;
        numberOfRequests =((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM ActivityRequest").uniqueResult()).intValue();
        return numberOfRequests;
    }

    public void update(ActivityRequest request){
        sessionFactory.getCurrentSession().update(request);
    }

}
