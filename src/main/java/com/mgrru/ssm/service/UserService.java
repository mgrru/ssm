package com.mgrru.ssm.service;


import com.mgrru.ssm.dao.IUser;
import com.mgrru.ssm.entity.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Resource(name = "IUser")
    private IUser iUser;


    @Transactional
    public boolean addUser(User user) {
        if (iUser.selectName(user.getName()) != null){
            return false;
        }else {
            iUser.add(user);
            return true;
        }

    }

    public List<User> selectAll() {
        return iUser.selectAll();
    }
}
