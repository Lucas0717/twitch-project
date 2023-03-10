package com.laioffer.twitch.service;

import com.laioffer.twitch.dao.RegisterDao;
import com.laioffer.twitch.entity.db.User;
import com.laioffer.twitch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RegisterService {

    private RegisterDao registerDao;
    @Autowired
    public RegisterService(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    public boolean register(User user) throws IOException {
        user.setPassword(Util.encryptPassword(user.getUserId(),user.getPassword()));
        return registerDao.register(user);
    }


}
