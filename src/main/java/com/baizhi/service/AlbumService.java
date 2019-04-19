package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;


public interface AlbumService {
    List<Album> queryAll();

    Album selectAlbumByid(String id);

    public List<Album> selectAlbum();

    void insert(Album album);
}
