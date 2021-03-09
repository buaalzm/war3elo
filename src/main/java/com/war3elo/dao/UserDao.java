package com.war3elo.dao;

import com.war3elo.domain.User;
import com.war3elo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;


/**
 * @author lzm
 * @create 2021-03-08 10:02
 */
@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;
    public int addUser(User user){
        return userMapper.insert(user);
    }
    /*
     * 删除成功返回1，失败返回0
     * */
    public int deleteUserById(int id){
        return userMapper.deleteByPrimaryKey(id);
    }
    public int deleteUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return userMapper.delete(user);
    }
    /*
    * 查到返回User实体
    * 查不到返回null
    * */
    public User getUserByUsername(String username){
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        List<User> users = userMapper.selectByExample(example);
        if (users.isEmpty())
            return null;
        else
            return users.get(0);
    }
    public User getUserById(int id){
        User user = new User();
        user.setId(id);
        return userMapper.selectByPrimaryKey(user);
    }
    public int updatePasswordByUsername(String username,String newPassword){
        Example example = new Example(User.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        User user = new User();
        user.setUsername(username);
        user.setPassword(newPassword);
        return userMapper.updateByExample(user, example);
    }
    public int updateUserById(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }
    public List<User> getAllUser(){
        Example example = Example.builder(User.class).select("id","username").build();
        return userMapper.selectByExample(example);
    }
}
