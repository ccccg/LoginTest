package com.login.logintest.Service;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.domain.User;
import com.login.logintest.utils.MailUtil;

import java.util.UUID;

public class RegistService {

    public String regist(User user, UserRepository userRepository){
        try{
            user.setActiveState(false);
            user.setCode(getUUID());

            userRepository.save(user);
            MailUtil mailUtil = new MailUtil(user.getEmail(),user.getCode());
            mailUtil.sendMailToActive(user);
            return "http://localhost:8888/active?name="+user.getName()+"&code="+user.getCode();
        }catch (Exception e){
            return e.toString();
        }
    }
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

}
