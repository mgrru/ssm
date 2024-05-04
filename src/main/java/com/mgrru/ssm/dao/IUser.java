package com.mgrru.ssm.dao;

import com.mgrru.ssm.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUser {

    @Select("select * from user")
    public List<User> selectAll();

    @Select("select max(id) from user")
    public int selectMaxId();

    @Select("select * from user where name=#{name}")
    public User selectName(@Param("name") String name);

    
    @Select("insert into user(name, photo, path) values (#{name},#{photo},#{path})")
    @SelectKey(keyColumn = "id",keyProperty = "id", statement = {}, before = false, resultType = Integer.class)
    public void add(User user);

}
