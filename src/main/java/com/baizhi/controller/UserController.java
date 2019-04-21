package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("selectAll")
    public Map selectAll(int page, int rows) {
        return userService.queryAll(page, rows);
    }

    @RequestMapping("insert")
    public Map insert(User user, MultipartFile image) throws IOException {
        System.out.println(user + "userController");
        String uuid = UUID.randomUUID().toString();
        String oldName = image.getOriginalFilename();
        String newName = uuid + oldName.substring(oldName.lastIndexOf("."));
        image.transferTo(new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\user\\" + newName));
        user.setHeadImg(newName);
        Map map = new HashMap();
        try {
            userService.insert(user);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        return map;
    }

    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response) {
        List<User> list = userMapper.selectAll();
        for (User user : list) {
            user.setHeadImg("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\user\\" + user.getHeadImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持明法洲注册用户", "用户"), User.class, list);
        String oldName = "easypoi_cmfz_user.xlsx";
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //直接将写入到输出流中即可;
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("upStatus")
    public Map upStatus(User user) {
        Map map = new HashMap();
        if (user.getStatus() == 0) {
            user.setStatus(1);
        } else {
            user.setStatus(0);
        }
        try {
            userService.update(user);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        return map;
    }


    /*@RequestMapping("delete")
    public Map delete(User user) {
        Map map = new HashMap();
        try {
            userService.
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        return map;
    }

    @RequestMapping("update")
    public Map update(Banner banner) {
        Map map = new HashMap();
        try {
            bannerService.update(banner);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        return map;
    }*/


}
