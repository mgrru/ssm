package com.mgrru.ssm.advice;

import com.mgrru.ssm.dao.ILog;
import com.mgrru.ssm.dao.IUser;
import com.mgrru.ssm.entity.Log;
import com.mgrru.ssm.entity.User;
import jakarta.annotation.Resource;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Aspect
public class UserServiceAdvice {
    @Resource(name = "ILog")
    private ILog iLog;

    @Resource
    private IUser iUser;

    @Pointcut(value = "execution(* com.mgrru.ssm.service.UserService.addUser(..)) && args(name)")
    public void addUserPointcut(String name) {
    }

    @After(value = "addUserPointcut(name)", argNames = "name")
    public void afterAddUser(String name) {
        User user = iUser.selectName(name);
        if (user != null) {
            Log log = new Log(new Timestamp(System.currentTimeMillis()),
                    "添加用户 : " + user);
            iLog.add(log);
        }
    }
}
