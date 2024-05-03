package com.mgrru.ssm.dao;

import com.mgrru.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUser {

    @Select("select * from user")
    public List<User> selectAll();

    @Select("select max(id) from user")
    public int selectMaxId();

    @Select("select * from user where name=#{name}")
    public User selectName(@Param("name")String name);

    @Select("insert into user values (#{name},#{photo},#{path})")
    public void add(User user);

}
