package com.login.logintest.controller;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.Service.LoginService.LoginService;
import com.login.logintest.Service.LoginService.LoginServiceImpl;
import com.login.logintest.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/checkLogin")
public class LoginController {

    private UserRepository userRepository;
    private LoginService loginService;
    @Autowired
    private  LoginController(
            UserRepository userRepository,
            LoginService loginService
    ){
        this.userRepository = userRepository;
        this.loginService = loginService;
    }


    @RequestMapping(method = RequestMethod.POST)
    private String checkLogin(
           User user,
           HttpServletRequest request
    ){

        //LoginService loginService  = new LoginServiceImpl();

        boolean result = loginService.isAccountExist(user,userRepository);

        if(result){
            //判断是否激活
            boolean isActived =loginService.isActived(user.getName(),userRepository);
            if(isActived){
                request.getSession().setAttribute("user", user.getName());
                return "welcome "+user.getName();
            }else{
                return "please active first";
            }
        }else {
            return "username or password is wrong";
        }

    }

}

