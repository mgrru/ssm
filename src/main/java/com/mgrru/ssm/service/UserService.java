package com.mgrru.ssm.service;


import com.google.gson.Gson;
import com.mgrru.ssm.dao.IUser;
import com.mgrru.ssm.entity.User;
import jakarta.annotation.Resource;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
public class UserService {

    @Resource(name = "IUser")
    private IUser iUser;

    @Transactional
    public String addUser(String name, MultipartFile file) throws IOException {
        if (iUser.selectName(name) != null) {
            return "用户已存在";
        } else {
            String oriname = file.getOriginalFilename();
            String suffix = null;
            if (oriname != null) {
                suffix = oriname.substring(oriname.lastIndexOf("."));
            }

            if (suffix != null && (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg"))) {
                File dir = new File("D:\\java\\files\\" + name);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String uuid = UUID.randomUUID().toString();// 生成一个新的文件名，避免文件同名出现文件覆盖的现象
                String newName = uuid + suffix;
                User user = new User(null, name, oriname, dir + "\\" + newName);

                iUser.add(user);
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + "\\" + newName));
                return "添加成功";
            } else {
                return "文件类型不支持";
            }
        }
    }

    @Transactional
    public String updateUser(User user, MultipartFile file) throws IOException {
        if (!Objects.equals(iUser.selectId(user.getId()).getName(), user.getName()) && iUser.selectName(user.getName()) != null) {
            // 更新名称且名称已存在
            return "名称已存在";
        } else {
            String oriname = file.getOriginalFilename();
            String suffix = null;
            if (oriname != null) {
                suffix = oriname.substring(oriname.lastIndexOf("."));
            }

            if (suffix != null && (suffix.equalsIgnoreCase(".png") || suffix.equalsIgnoreCase(".jpg"))) {
                // 删除原来的文件夹
                File file1 = new File(iUser.selectId(user.getId()).getPath());
                File parentDir = file1.getParentFile();
                FileUtils.deleteDirectory(parentDir);

                // 创建新文件夹
                File dir = new File("D:\\java\\files\\" + user.getName());
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                String uuid = UUID.randomUUID().toString();// 生成一个新的文件名，避免文件同名出现文件覆盖的现象
                String newName = uuid + suffix;
                user.setPhoto(oriname);
                user.setPath(dir + "\\" + newName);
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(dir + "\\" + newName));

                iUser.update(user);
                return "更新成功";
            } else {
                return "文件类型不支持";
            }
        }
    }

    @Transactional
    public String deleteUser(Integer id) throws IOException {
        User user = iUser.selectId(id);
        if (iUser.delete(id)) {
            File dir = new File(user.getPath());
            // 获取父文件夹
            File parentDir = dir.getParentFile();
            // 删除父文件夹
            if (parentDir != null && parentDir.exists()) {
                FileUtils.deleteDirectory(parentDir);
            }
            return "删除成功";
        } else {
            return "删除失败";
        }

    }

    @Transactional(readOnly = true)
    public String selectAllUser() {
        return new Gson().toJson(iUser.selectAll());
    }

}
