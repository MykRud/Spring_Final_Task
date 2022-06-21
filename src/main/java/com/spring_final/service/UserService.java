package com.spring_final.service;

import com.spring_final.daos.ActivityDao;
import com.spring_final.daos.ActivityRequestDao;
import com.spring_final.daos.TypesOfActivitiesDao;
import com.spring_final.daos.UserDao;
import com.spring_final.model.Authority;
import com.spring_final.model.User;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityRequestDao requestDao;
    @Autowired
    private TypesOfActivitiesDao typeDao;

    public void addUser(User user){
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        userDao.addUser(user);
        User foundUser = getUser(user.getUsername());
        if(foundUser != null)
            setAuthority(foundUser.getId(), Authority.getUserAuthority());
    }

    public User getUser(int id){
        return userDao.getUser(id);
    }

    public User getUser(String username){
        return userDao.getUser(username);
    }

    public List<User> getUsers(){
        return userDao.getUsers();
    }

    public int getNumberOfUsers(){
        return userDao.getNumberOfUsers();
    }

    public List<User> getUsersInLimit(int size, int page){
        return userDao.getUsersInLimit(size, page);
    }

    public void deleteUser(int id){
        userDao.delete(id);
    }

    public void updateUser(User user){
        User foundUser = getUser(user.getId());
        user.setGender(foundUser.getGender());
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        user.setAuthorities(foundUser.getAuthorities());
        userDao.update(user);
    }

    public void setAuthority(int userId, Authority authority){
        User user = userDao.getUser(userId);
        Set<Authority> authorities = user.getAuthorities();
        for(Authority currentAuthority : authorities){
            if(currentAuthority == authority)
                return;
        }
        userDao.setAuthority(userId, authority);
    }

    public void deleteAuthority(int userId, Authority authority){
        User user = userDao.getUser(userId);
        user.getAuthorities().remove(authority);
        userDao.update(user);
    }
}
