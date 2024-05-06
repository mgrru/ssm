package com.mgrru.ssm.dao;


import com.mgrru.ssm.entity.Log;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILog {

    @Select("select * from log")
    List<Log> selectAll();

    @Select("insert into log values (#{time},#{desc})")
    void add(Log log);
}
