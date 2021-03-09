package com.war3elo.web;

import com.war3elo.Service.CombatService;
import com.war3elo.Service.EloService;
import com.war3elo.Service.UserService;
import com.war3elo.Utils.JsonResult;
import com.war3elo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author lzm
 * @create 2021-03-08 21:39
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private EloService eloService;
    @Autowired
    private CombatService combatService;
    @RequestMapping(value = "/addUser", produces = "application/json;charset=UTF-8")
    public JsonResult<User> addUser(@RequestParam("username") String username,@RequestParam(name="password",required = false) String password) throws UnsupportedEncodingException {
        User user = new User();
        user.setUsername(username);
        if (password != null){
            user.setPassword(password);
        }
        System.out.println("中文测试");
        System.out.println(username);
        try {
            int i = userService.addUser(user);
            eloService.addDeaultIfNotExist(username);
            return new JsonResult<User>(user);
        }
        catch (Exception e){
            return new JsonResult<User>(null,"1","添加失败");
        }
    }
    @GetMapping(value = "/updateUserById",produces = "application/json;charset=UTF-8")
    public JsonResult<User> updateUserById(@RequestParam("id") int id,
                                    @RequestParam(name = "username",required = false) String username,
                                    @RequestParam(name="password",required = false) String password){
        User user = new User();
        user.setId(id);
        if (username!=null){
            user.setUsername(username);
        }
        if (password!=null){
            user.setPassword(password);
        }
        int i = userService.updateUserById(user);
        if (i==0){
            return new JsonResult<User>(null,"1","添加失败");
        }
        else {
            return new JsonResult<User>(user);
        }
    }
    @GetMapping(value = "/deleteUserById",produces = "application/json;charset=UTF-8")
    public JsonResult<Integer> deleteUserById(@RequestParam("id") int id){
        User user = userService.getUserById(id);
        return deleteUser(user);
    }
    @GetMapping(value = "/deleteUserByUsername",produces = "application/json;charset=UTF-8")
    public JsonResult<Integer> deleteUserByUsername(@RequestParam("username") String username){
        User user = userService.getUserByUsername(username);
        return deleteUser(user);
    }
    @GetMapping(value = "/getAllUser",produces = "application/json;charset=UTF-8")
    public JsonResult<List<User>> getAllUser(){
        return new JsonResult(userService.getAllUser());
    }
    public JsonResult<Integer> deleteUser(User user){
        if (user == null){
            return new JsonResult<Integer>(0,"1","删除失败");
        }
        else {
            int i = userService.deleteUserById(user.getId());//delete user
            eloService.deleteEloByUsername(user.getUsername());// delete elo
            combatService.deleteCombatByUsername(user.getUsername());// delete combat
            return new JsonResult<Integer>(1);
        }
    }
}
