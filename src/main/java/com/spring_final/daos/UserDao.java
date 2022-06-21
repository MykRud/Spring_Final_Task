package com.spring_final.daos;

import com.spring_final.configuration.HibernateDriver;
import com.spring_final.model.Authority;
import com.spring_final.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository  //@Component
@Transactional
public class UserDao {

    @Autowired
    private SessionFactory sessionFactory;

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
        sessionFactory.getCurrentSession().remove(user);
    }

    public void update(User user){
        sessionFactory.getCurrentSession().update(user);
    }

    public void setAuthority(int userId, Authority authority) {
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO user_authority(users_id, authorities_id) VALUES" +
                "(:user_id, :authority_id)")
                .setParameter("user_id", userId)
                .setParameter("authority_id", authority.getId())
                .executeUpdate();
    }
}
