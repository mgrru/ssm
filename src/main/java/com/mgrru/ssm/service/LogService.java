package com.mgrru.ssm.service;

import com.mgrru.ssm.dao.ILog;
import com.mgrru.ssm.entity.Log;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Resource
    private ILog iLog;

    public List<Log> select() {
        return iLog.selectAll();
    }
}
