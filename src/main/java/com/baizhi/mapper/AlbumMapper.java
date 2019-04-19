package com.baizhi.mapper;

import com.baizhi.entity.Album;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface AlbumMapper extends Mapper<Album> {

    public List<Album> select1();

    public List<Album> selectAlbum();


}
