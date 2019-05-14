package com.login.logintest.controller;

import com.login.logintest.Service.AnalyseService.AnalyseService;
import com.login.logintest.domain.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/analyse")
public class AnalyseController {
    AnalyseService analyseService;

    @Autowired
    public AnalyseController(
            AnalyseService analyseService
    ){
        this.analyseService = analyseService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void sendAnalyseResult(
            Mail mail
    ){
        System.out.println(mail.getMail());
        String fileName = "C:\\Users\\27945\\logingit\\readme.txt";
        this.analyseService.sendResult(mail.getMail(),fileName);

    }
}
