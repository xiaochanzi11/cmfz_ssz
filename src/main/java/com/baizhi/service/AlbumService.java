package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;


public interface AlbumService {
    List<Album> queryAll();

    Album selectAlbumByid(String id);

    List<Album> selectAlbum();

    void insert(Album album);

    Album selectOne1(String id);

    void update(Album album);
}
