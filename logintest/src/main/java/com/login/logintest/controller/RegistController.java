package com.login.logintest.controller;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.Service.RegistService;
import com.login.logintest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/regist")
public class RegistController {

    private UserRepository userRepository;

    @Autowired
    public RegistController(
            UserRepository userRepository){
        this.userRepository = userRepository;
    }


    @RequestMapping(method = RequestMethod.POST)
    public String addToRegist(
            User user
    ){
        RegistService registService = new RegistService();
        return registService.regist(user,userRepository);
    }



}
