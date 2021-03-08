package com.war3elo.Service;

import com.war3elo.dao.UserDao;
import com.war3elo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 21:32
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public int addUser(User user){
        return userDao.addUser(user);
    }
    public int updateUserById(User user){
        return userDao.updateUserById(user);
    }
    public int deleteUserById(int id){
        return userDao.deleteUserById(id);
    }
    public int deleteUserByUsername(String username){
        return userDao.deleteUserByUsername(username);
    }
    public List<User> getAllUser(){
        return userDao.getAllUser();
    }
}
