package com.mgrru.ssm.advice;

import com.mgrru.ssm.dao.ILog;
import com.mgrru.ssm.entity.Log;
import com.mgrru.ssm.entity.User;
import jakarta.annotation.Resource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Aspect
public class UserServiceAdvice {
    @Resource(name = "ILog")
    private ILog iLog;

    @Pointcut(value = "execution(* com.mgrru.ssm.service.UserService.addUser(..)) && args(user)")
    public void addUserPointcut(User user) {
    }

    @Before(value = "addUserPointcut(user)", argNames = "user")
    public void beforeAddUser(User user) {
        Log log = new Log(new Timestamp(System.currentTimeMillis()),
                "add user : " + user.getName() + " "
                        + user.getPhoto() + " " + user.getPath());
        iLog.add(log);
        System.out.println("添加log");
    }
}
