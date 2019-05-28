package com.login.logintest.controller;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.Service.ActiveService;
import com.login.logintest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/active")
public class ActiveController {
    private UserRepository userRepository;

    @Autowired
    public ActiveController(
            UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    private String activeUser(
           @RequestParam(name = "name") String name,
           @RequestParam(name = "code") String code
    ){
        ActiveService activeService = new ActiveService();
        return activeService.active(name,code,userRepository);
    }

}
