package com.login.logintest.Service.LoginService;

import com.login.logintest.Repository.UserRepository;
import com.login.logintest.domain.User;

import java.util.Optional;


public interface LoginService {

    /**
     *
     * @param user 实体对象
     * @param userRepository 仓储接口
     * @return 账号密码是否匹配
     */
    boolean login(User user, UserRepository userRepository);

    /**
     *
     * @param username 用户名
     * @param userRepository 仓储接口
     * @return 账户是否已激活
     */
    boolean isActived(String username, UserRepository userRepository);
}
