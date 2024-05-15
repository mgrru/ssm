package com.mgrru.ssm.advice;

import com.mgrru.ssm.dao.ILog;
import com.mgrru.ssm.dao.IUser;
import com.mgrru.ssm.entity.Log;
import com.mgrru.ssm.entity.User;
import jakarta.annotation.Resource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@Aspect
public class UserServiceAdvice {
    @Resource(name = "ILog")
    private ILog iLog;

    @Resource(name = "IUser")
    private IUser iUser;


    @Pointcut(value = "execution(* com.mgrru.ssm.service.UserService.addUser(..))")
    public void addUserPointcut() {
    }

    @Pointcut(value = "execution(* com.mgrru.ssm.service.UserService.deleteUser(..))")
    public void deleteUserPointcut() {
    }

    @Pointcut(value = "execution(* com.mgrru.ssm.service.UserService.updateUser(..))")
    public void updateUserPointcut(){

    }

    @AfterReturning(value = "addUserPointcut()", returning = "res")
    public void afterAddUser(JoinPoint joinPoint, String res) {
        String name = (String) joinPoint.getArgs()[0];

        if (Objects.equals(res, "添加成功")) {
            User user = iUser.selectName(name);
            Log log = new Log(new Timestamp(System.currentTimeMillis()),
                    "添加用户 : " + user);
            iLog.add(log);
        }
    }


    @Before(value = "deleteUserPointcut()")
    public void beforeDeleteUser(JoinPoint joinPoint) {
        Integer id = (Integer) joinPoint.getArgs()[0];

        User user = iUser.selectId(id);
        if (user != null) {
            Log log = new Log(new Timestamp(System.currentTimeMillis()),
                    "删除用户 : " + user);
            iLog.add(log);
        }
    }

    private User u1;

    @Before(value = "updateUserPointcut()")
    public void beforeUpdateUser(JoinPoint joinPoint){
        User user = (User) joinPoint.getArgs()[0];
        u1 = iUser.selectId(user.getId());
    }

    @AfterReturning(value = "updateUserPointcut()", returning = "res")
    public void afterUpdateUser(JoinPoint joinPoint, String res) {
        User u2 = (User) joinPoint.getArgs()[0];

        if (Objects.equals(res, "更新成功")) {
            Log log = new Log(new Timestamp(System.currentTimeMillis()),
                    "更新用户 : " + u1 + " ==> " + u2);
            iLog.add(log);
        }
    }
}
