package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("detail")
public class AlbumDetailController {

    @Autowired
    private AlbumService albumService;

    @RequestMapping("wen")
    public Object AlbumDetail(String id, Integer uid) {
        Album album = albumService.selectAlbumByid(id);

        return album;

    }
}
