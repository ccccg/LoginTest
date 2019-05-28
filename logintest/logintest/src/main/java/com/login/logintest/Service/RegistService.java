package com.login.logintest.Service;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.Service.LoginService.LoginService;
import com.login.logintest.Service.LoginService.LoginServiceImpl;
import com.login.logintest.domain.User;
import com.login.logintest.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.Optional;
import java.util.UUID;

public class RegistService {


    LoginService loginServiceImpl = new LoginServiceImpl();

    public String regist(User user, UserRepository userRepository){
        try{
            user.setActiveState(false);
            user.setCode(getUUID());


            if(!hasRegisted(user,userRepository,loginServiceImpl)){

                //save()不是简单insert，是更新，如果新数据中某个字段为空，则数据库记录中也使某属性为空
                user.setPassword(getMD5(user.getPassword()));
                userRepository.save(user);
                MailUtil mailUtil = new MailUtil(user.getEmail(),user.getCode());
                mailUtil.sendMailToActive(user);
                return "http://localhost:8888/active?name="+user.getName()+"&code="+user.getCode();
            }
            return "Account has been exist!";
        }catch (Exception e){

            return e.toString();
        }
    }


    public boolean hasRegisted(User user, UserRepository userRepository, LoginService loginService){

        boolean result = loginService.isAccountExist(user,userRepository);

        if(!result){

            return false;
        }

        boolean isActived =loginService.isActived(user.getName(),userRepository);
        if(isActived){
            return true;
        }
        return false;
    }

    public static String getMD5(
            String password
    ){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }


    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

}
