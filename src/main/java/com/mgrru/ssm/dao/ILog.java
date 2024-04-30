package com.mgrru.ssm.dao;


import com.mgrru.ssm.entity.Log;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILog {

    @Select("select * from log")
    public List<Log> selectAll();

    @Select("insert into log values (#{time},#{desc})")
    public void add(Log log);
}
