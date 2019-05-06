package com.login.logintest.controller;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.Service.LoginService;
import com.login.logintest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/login")
public class LoginController {

    private UserRepository userRepository;

    @Autowired
    private  LoginController(
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    private String checkLogin(
            User user
    ){
        LoginService loginService = new LoginService();
        return loginService.login(user,userRepository);
    }

}

