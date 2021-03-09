package com.war3elo.dao;

import com.war3elo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lzm
 * @create 2021-03-08 10:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    @Autowired
    private UserDao userService;
    @Test
    public void InsertUser(){
        User user = new User();
        user.setUsername("mike");
        user.setPassword("123");
        userService.addUser(user);
    }
    @Test
    public void deleteUserById(){
        int i = userService.deleteUserById(1);
        System.out.println(i);
    }
    @Test
    public void deleteUserByUsername(){
        int tom2 = userService.deleteUserByUsername("tom2");
        System.out.println(tom2);
        int tom3 = userService.deleteUserByUsername("tom3");
        System.out.println(tom3);
    }
    @Test
    public void getUserByUsername(){
        User user = userService.getUserByUsername("tom2");
        System.out.println(user);
        System.out.println(userService.getUserByUsername("tom3"));
    }
    @Test
    public void updatePasswordByUsername(){
        String username = "tom2";
        String password = "321";
        System.out.println(userService.updatePasswordByUsername(username,password));
    }
    @Test
    public void getAllUser(){
        System.out.println(userService.getAllUser());
    }
}
