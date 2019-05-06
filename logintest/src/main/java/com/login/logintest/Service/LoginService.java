package com.login.logintest.Service;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.domain.User;

import java.util.Optional;

public class LoginService {

    public String login(User user, UserRepository userRepository){
        String name = user.getName();
        String password = user.getPassword();

        Optional<User> optionalUser = userRepository.findByNameAndPassword(
                name,password
        );

        if(optionalUser.isPresent()){
            return "welcome "+optionalUser.get().getName();
        }else{
            return "login fail!";
        }
    }
}
