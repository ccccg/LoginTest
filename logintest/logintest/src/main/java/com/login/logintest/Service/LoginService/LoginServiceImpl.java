package com.login.logintest.Service.LoginService;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.Service.RegistService;
import com.login.logintest.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public boolean isAccountExist(User user, UserRepository userRepository){
        String name = user.getName();
        String password = user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        Optional<User> optionalUser = userRepository.findByNameAndPassword(
                name,md5Password
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
        System.out.println("check actived");
        if(optionalUser.isPresent()){

            return optionalUser.get().getActiveState();
        }

        return false;

    }
}
