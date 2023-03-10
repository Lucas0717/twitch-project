package com.laioffer.twitch.service;

import com.laioffer.twitch.dao.LoginDao;
import com.laioffer.twitch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class LoginService {


    private LoginDao loginDao;
    @Autowired
    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }
    public String verifyLogin(String userId, String password) throws IOException {
        password = Util.encryptPassword(userId, password);
        return loginDao.verifyLogin(userId, password);
    }

}
