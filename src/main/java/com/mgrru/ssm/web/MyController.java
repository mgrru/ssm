package com.mgrru.ssm.web;

import com.mgrru.ssm.entity.User;
import com.mgrru.ssm.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


@Controller
public class MyController {

    @RequestMapping(value = "/user/sss", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String find(@RequestBody String name) {
        return "你好" + name;
    }

    @PostMapping(value = "/user/add", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(@RequestParam("name") String name, @RequestBody MultipartFile file) throws IOException {
        File dir = new File("D:\\java\\files\\" + name);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String oriname = file.getOriginalFilename();
        String suffix = null;
        if (oriname != null) {
            suffix = oriname.substring(oriname.lastIndexOf("."));
        }
        if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")) { // 判断文件的后缀
            ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring-*.xml");
            UserService us = ac.getBean("userService", UserService.class);

            String uuid = UUID.randomUUID().toString();// 生成一个新的文件名，避免文件同名出现文件覆盖的现象
            String newName = uuid + suffix;
            User user = new User(null, name, oriname, dir + "\\" + newName);
            if (us.addUser(user)) {
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + "\\" + newName));
                return "添加成功";
            } else
                return "用户已存在";

        } else {
            return "文件类型不支持";
        }
    }
}