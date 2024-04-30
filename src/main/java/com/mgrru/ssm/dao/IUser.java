package com.mgrru.ssm.dao;

import com.mgrru.ssm.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUser {
    @Select("select * from user")
    public List<User> selectAll();

    @Select("insert into user values (#{name},#{photo})")
    public void add(User user);

}
