package com.login.logintest.Service.AnalyseService;

import com.login.logintest.domain.User;
import com.login.logintest.utils.MailUtil;
import org.springframework.stereotype.Service;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    @Override
    public void sendResult(String email, String fileName){

        MailUtil mailUtil = new MailUtil(email);
        mailUtil.sendAnalyseResult(fileName);

    }
}
