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
    User selectName(String name);

    @Select("select * from user where id=#{id}")
    User selectId(int id);


    @Insert("insert into user(name, photo, path) values (#{name},#{photo},#{path})")
    @SelectKey(keyColumn = "id", keyProperty = "id", statement = "SELECT LAST_INSERT_ID()", before = false, resultType = Integer.class)
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void add(User user);

    @Update("update user set name=#{name},photo=#{photo},path=#{path} where id=#{id}")
    void update(User user);


    @Delete("delete from user where id=#{id}")
    Boolean delete(@Param("id") Integer id);

}
