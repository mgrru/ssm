package com.mgrru.ssm.web;

import com.google.gson.Gson;
import com.mgrru.ssm.service.LogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogController {
    @Resource
    private LogService ls;

    @RequestMapping(value = "log/select", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String select() {
        return new Gson().toJson(ls.select());
    }
}
