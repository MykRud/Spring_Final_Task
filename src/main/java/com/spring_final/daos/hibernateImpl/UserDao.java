package com.spring_final.daos.hibernateImpl;

import com.spring_final.model.Authority;
import com.spring_final.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository  //@Component
@Transactional
public class UserDao {

    //@Autowired
    private SessionFactory sessionFactory = null;

    public void addUser(User user){
        sessionFactory.getCurrentSession().save(user);
    }

    public User getUser(int id){
        User user = sessionFactory.getCurrentSession().
                get(User.class, id);//createQuery("FROM User WHERE id= :id").setParameter("id", id).uniqueResult();
        return user;
    }


    public User getUser(String username){
        User user = (User) sessionFactory.getCurrentSession().createQuery("FROM User WHERE username = :username")
                .setParameter("username", username).uniqueResult();
        return user;
    }

    public int getNumberOfUsers(){
        int numberOfUsers = 0;
        numberOfUsers =((BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT COUNT(*) FROM User").uniqueResult()).intValue();
        return numberOfUsers;
    }


    public List<User> getUsers(){
        List<User> users;
        users = sessionFactory.getCurrentSession().createQuery("FROM User").getResultList();
        return users;
    }


    public List<User> getUsersInLimit(int size, int page) {
        List<User> users;

        users = sessionFactory.getCurrentSession().createSQLQuery("SELECT * from User order by id LIMIT :page, :size")
                .setParameter("page", size * page)
                .setParameter("size", size)
                .getResultList();

        return users;
    }

    public void delete(int id){
        User user = getUser(id);
        deleteAuthorities(id);
        deleteActivities(id);
        sessionFactory.getCurrentSession().delete(user);
    }

    public void update(User user){
        sessionFactory.getCurrentSession().update(user);
    }

    public void setAuthority(int userId, Authority authority) {
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO authority_user(authorities_id, users_id) VALUES" +
                "(:authorities_id, :users_id)")
                .setParameter("authorities_id", authority.getId())
                .setParameter("users_id", userId)
                .executeUpdate();
    }

    public void deleteAuthorities(int userId) {
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM authority_user WHERE users_id=:userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }

    public void deleteActivities(int userId){
        sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM activity_user WHERE users_id=:userId")
                .setParameter("userId", userId)
                .executeUpdate();
    }
}
