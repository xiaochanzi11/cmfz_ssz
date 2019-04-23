package com.baizhi.service.impl;

import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("albumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public List<Album> queryAll() {
        List<Album> list = albumMapper.select1();
        //System.out.println(list);
        return list;
    }

    @Override
    public Album selectAlbumByid(String id) {
        return albumMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Album> selectAlbum() {
        List<Album> list = albumMapper.selectAlbum();
        return list;
    }

    @Override
    public void insert(Album album) {
        albumMapper.insert(album);
    }

    //有映射关系的
    @Override
    public Album selectOne1(String id) {
        Album album = albumMapper.selectOne1(id);
        return album;
    }


    @Override
    public void update(Album album) {
        albumMapper.updateByPrimaryKeySelective(album);
    }




}
