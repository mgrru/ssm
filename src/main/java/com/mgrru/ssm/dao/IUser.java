package com.mgrru.ssm.dao;

import com.mgrru.ssm.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUser {

    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where name=#{name}")
    User selectName(@Param("name") String name);


    @Select("insert into user(name, photo, path) values (#{name},#{photo},#{path})")
    @SelectKey(keyColumn = "id", keyProperty = "id", statement = {}, before = false, resultType = Integer.class)
    void add(User user);

    @Update("update user set name=#{name},photo=#{photo},path=#{path} where id=#{id}")
    void update(User user);

    @Update("update user set id=#{id},name=#{name},photo=#{photo},path=#{path} where id=#{oldId}")
    void update(User user, int oldId);

    @Delete("delete from user where id=#{id}")
    void delete(@Param("id") Integer id);

}
