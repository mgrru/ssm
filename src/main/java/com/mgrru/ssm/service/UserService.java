package com.mgrru.ssm.service;


import com.mgrru.ssm.dao.IUser;
import com.mgrru.ssm.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Resource(name = "IUser")
    private IUser iUser;

    public void addUser(User user) {
        iUser.add(user);
        System.out.println("添加user");
    }
}
