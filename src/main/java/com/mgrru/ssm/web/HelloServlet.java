package com.mgrru.ssm.web;

import com.mgrru.ssm.entity.User;
import com.mgrru.ssm.service.UserService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller("user")
public class HelloServlet extends HttpServlet {

    @PostMapping(value = "add", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String add(String name, MultipartFile file, HttpServletRequest req) throws IOException {

        String rpath = req.getServletContext().getRealPath("/files/" + name);
        File dir = new File(rpath);
        if (!dir.exists())
            dir.mkdirs();

        String oriname = file.getOriginalFilename();
        String suffix = null;
        if (oriname != null) {
            suffix = oriname.substring(oriname.lastIndexOf("."));
        }

        if (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg")) { // 判断文件的后缀
            ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
            UserService us = ac.getBean("userService", UserService.class);

            String uuid = UUID.randomUUID().toString();// 生成一个新的文件名，避免文件同名出现文件覆盖的现象
            String newName = uuid + suffix;
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + "\\" + newName));
            User user = new User(null, name, oriname, dir + "\\" + newName);

            if (us.addUser(user)) {
                return "添加成功";
            } else
                return "用户重复";

        } else {
            return "文件类型不支持";
        }
    }
}