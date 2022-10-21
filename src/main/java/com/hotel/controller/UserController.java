package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.User;
import com.hotel.service.UserService;


@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/find/all/User")
    private List<User> findallUsers() {
        return userService.findedallUser();
    }

    @RequestMapping("/saveUser")
    private String addUsers() {
        return userService.addUsers();
    }
}