package com.mgrru.ssm.web;

import com.google.gson.Gson;
import com.mgrru.ssm.entity.User;
import com.mgrru.ssm.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class UserController {
    @Resource
    private UserService us;


    @RequestMapping(value = "/user/sss", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String find(@RequestBody String name) {
        return "你好" + name;
    }

    @RequestMapping(value = "/user/select", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String find() {
        return us.selectAllUser();
    }

    @PostMapping(value = "/user/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(@RequestParam("name") String name, @RequestBody MultipartFile file) throws IOException {
        return us.addUser(name, file);
    }

    @PostMapping(value = "/user/del", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String del(@RequestBody String json) throws IOException {
        User user = new Gson().fromJson(json, User.class);
        return us.deleteUser(user.getId());
    }
}