package com.login.logintest.Service;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.domain.User;

import java.util.Optional;

public class ActiveService {

    public ActiveService(){}


    public String active(String name, String code, UserRepository userRepository){
        Optional<User> userOptional = userRepository.findByName(name);

        if(userOptional.isPresent()){
            //判断激活码是否匹配
            if(userOptional.get().getActiveState()==true){
                return "请勿重复激活";
            }

            if(code.equals(userOptional.get().getCode())){
                //修改数据库中激活状态
                if(userRepository.active(name)==1){
                    return "actice success!";
                }else{
                    return "actice fail unknown error";
                }

            }else{
                return "unavailible code";
            }
        }else{
            return "failActive!";
        }
    }
}
