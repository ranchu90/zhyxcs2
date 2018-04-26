package com.example.mybatis.controller;

import com.example.mybatis.domain.User;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserConctroller {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public User getUser(Long id) {
        System.out.println(id);
        User user = userService.findById(id);
        return user;
    }

    @RequestMapping(value = "show", method = RequestMethod.GET)
    public String getShow() {

        return "show finish";
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<User> getAllUser() {
        List<User> user = userService.getAllUsers();
        return user;
    }
}
