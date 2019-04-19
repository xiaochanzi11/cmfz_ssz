package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @RequestMapping("select")
    public Map selectAll(int page, int rows) {

        return bannerService.queryAll(page, rows);
    }

    @RequestMapping("insert")
    public Map insert(Banner banner, MultipartFile image) throws IOException {
        //System.out.println(banner);
        String uuid = UUID.randomUUID().toString();
        String oldName = image.getOriginalFilename();
        String newName = uuid + oldName.substring(oldName.lastIndexOf("."));
        image.transferTo(new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\shouye\\" + newName));

        banner.setImgPath(newName);

        Map map = new HashMap();
        try {
            bannerService.insert(banner);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }

        return map;
    }

    @RequestMapping("delete")
    public Map delete(Banner banner) {
        Map map = new HashMap();
        try {
            bannerService.delete(banner);
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
    }
}
