package com.login.logintest.Service.LoginService;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.domain.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean login(User user, UserRepository userRepository){
        String name = user.getName();
        String password = user.getPassword();

        Optional<User> optionalUser = userRepository.findByNameAndPassword(
                name,password
        );

        if(optionalUser.isPresent()){
            return true;
            //
        }else{
            return false;

        }
    }

    @Override
    public boolean isActived(String username, UserRepository userRepository){
        Optional<User> optionalUser = userRepository.findByName(
                username
        );

        if(optionalUser.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
