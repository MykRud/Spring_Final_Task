package com.spring_final.daos.hibernateImpl;

import com.spring_final.model.Activity;
import com.spring_final.model.TypeOfActivity;
import com.spring_final.service.TypeOfActivityService;
import com.spring_final.service.UserService;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Repository
@Transactional
public class ActivityDao {

   //@Autowired
   private SessionFactory sessionFactory = null;

   public void addActivity(Activity activity){
      sessionFactory.getCurrentSession().save(activity);
   }

   public Activity getActivity(int id){
      Activity activity = sessionFactory.getCurrentSession().
              get(Activity.class, id);//createQuery("FROM Activity WHERE id= :id").setParameter("id", id).uniqueResult();
      return activity;
   }

   public Activity getActivity(String name){
      Activity activity = (Activity) sessionFactory.getCurrentSession().createQuery("FROM Activity WHERE name = :name")
              .setParameter("name", name).uniqueResult();
      return activity;
   }

   public List<Activity> getActivities(){
      List<Activity> list;
      list = (List<Activity>) sessionFactory.getCurrentSession().createQuery("FROM Activity").getResultList();



      /*Iterator itr = list.iterator();
      while(itr.hasNext()){
         Object[] obj = (Object[]) itr.next();
         int id = Integer.parseInt(String.valueOf(obj[0]));
         String description = String.valueOf(obj[1]);
         int duration = Integer.parseInt((String.valueOf(obj[2])));
         Date endTime = new Date(Integer.parseInt(String.valueOf(obj[3])));
         String name = String.valueOf(obj[4]);
         Date startTime = new Date(Integer.parseInt(String.valueOf(obj[5])));
         String status = String.valueOf(obj[6]);
         int type_id = Integer.parseInt(String.valueOf(obj[7]));
         TypeOfActivityService typeService = new TypeOfActivityService();
         TypeOfActivity type = typeService.findType(type_id);
         Activity activity = new Activity(id, name, status, description, duration, startTime, endTime, type,
                 new ArrayList<>());
      }*/
      return list;
   }

   public int getNumberOfActivities(){
      int numberOfActivities = 0;
      numberOfActivities = ((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM Activity").uniqueResult()).intValue();
      return numberOfActivities;
   }

   public List<Activity> getActivitiesInLimit(int size, int page) {
      List<Activity> list;

      list = (List<Activity>) sessionFactory.getCurrentSession().createQuery("from Activity order by id LIMIT :page, :size")
              .setParameter("page", size * page)
              .setParameter("size", size)
              .getResultList();

      return list;
   }

   public List<Activity> getActivitiesInLimitByName(int size, int page) {
      List<Activity> list;

      list = (List<Activity>) sessionFactory.getCurrentSession().createQuery("from Activity order by name LIMIT :page, :size")
                      .setParameter("page", size * page)
                              .setParameter("size", size)
                                      .getResultList();

      return list;
   }

   public List<Activity> getActivitiesInLimitByType(int size, int page) {
      List<Activity> activities;

      activities = (List<Activity>) sessionFactory.getCurrentSession().createQuery("from Activity order by type_id LIMIT :page, :size")
              .setParameter("page", size * page)
              .setParameter("size", size)
              .getResultList();


      return activities;
   }

   // !!! find in limit by number of users

   public void delete(int id){
      Activity activity = getActivity(id);
      sessionFactory.getCurrentSession().delete(activity);
   }

   public void update(Activity activity){
      sessionFactory.getCurrentSession().update(activity);
   }

}