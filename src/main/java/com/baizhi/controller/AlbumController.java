package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("album")
@ResponseBody
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @RequestMapping("select")
    public List<Album> queryAll() {
        List<Album> list = albumService.queryAll();
        return list;
    }

    @RequestMapping("selectAlbum")
    public List<Album> selectAlbum() {
        List<Album> list = albumService.selectAlbum();
        System.out.println(list);
        return list;
    }

    @RequestMapping("selectAlbumByid")
    @ResponseBody
    public Album selectAlbumByid(String id) {
        System.out.println("======" + id);
        return albumService.selectAlbumByid(id);
    }


    @RequestMapping("addalbum")
    @ResponseBody
    public Map addalbum(MultipartFile file, Album album) throws IOException {
        Map map = new HashMap<>();
        String oldName = file.getOriginalFilename();
        //2.将接收的文件复制到服务器上
        String uuid = UUID.randomUUID().toString();
//        System.out.println("action 文件名"+oldName);
        String newname = uuid + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\audioCollection\\" + newname));
        album.setId(uuid);
        album.setImgPath(newname);
        Date date = new Date();
        album.setPublishDate(date);
        try {
            albumService.insert(album);
            System.out.println();
            map.put("isadd", true);
        } catch (Exception e) {
            map.put("isadd", false);
            e.printStackTrace();
        }
        return map;
    }
}
