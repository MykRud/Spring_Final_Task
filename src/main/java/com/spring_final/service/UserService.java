package com.spring_final.service;

import com.spring_final.daos.ActivityDaoRep;
import com.spring_final.daos.ActivityRequestDaoRep;
import com.spring_final.daos.TypesOfActivitiesDaoRep;
import com.spring_final.daos.UserDaoRep;
import com.spring_final.daos.hibernateImpl.ActivityDao;
import com.spring_final.daos.hibernateImpl.ActivityRequestDao;
import com.spring_final.daos.hibernateImpl.TypesOfActivitiesDao;
import com.spring_final.daos.hibernateImpl.UserDao;
import com.spring_final.model.Authority;
import com.spring_final.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class UserService {

    @Autowired
    private UserDaoRep userDao;
    @Autowired
    private ActivityDaoRep activityDao;
    @Autowired
    private ActivityRequestDaoRep requestDao;
    @Autowired
    private TypesOfActivitiesDaoRep typeDao;

    public void addUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        userDao.save(user);
        User foundUser = getUser(user.getUsername());
        if(foundUser != null)
            setAuthority(foundUser, Authority.getUserAuthority());
    }

    public User getUser(int id){
        return userDao.findById(id).get();
    }

    public User getUser(String username){
        return userDao.getByUsername(username);
    }

    public List<User> getUsers(){
        return userDao.findAll();
    }

    public void deleteUser(int id){
        userDao.deleteById(id);
    }

    public void updateProfile(User user){
        User foundUser = getUser(user.getId());
        user.setGender(foundUser.getGender());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setAuthorities(foundUser.getAuthorities());
        userDao.save(user);
    }

    public void updateUser(User user){
        User foundUser = getUser(user.getId());
        user.setPassword(foundUser.getPassword());
        user.setAuthorities(foundUser.getAuthorities());
        userDao.save(user);
    }

    public void setAuthority(User user, Authority authority){
        Set<Authority> authorities = user.getAuthorities();
        for(Authority currentAuthority : authorities){
            if(currentAuthority == authority)
                return;
        }
        userDao.setAuthority(user.getId(), authority.getId());
    }

    public void deleteAuthority(int userId, Authority authority){
        User user = userDao.findById(userId).get();
        user.getAuthorities().remove(authority);
        userDao.save(user);
    }
}
