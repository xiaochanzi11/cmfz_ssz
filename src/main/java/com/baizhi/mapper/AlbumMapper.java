package com.baizhi.mapper;

import com.baizhi.entity.Album;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface AlbumMapper extends Mapper<Album> {

    List<Album> select1();

    List<Album> selectAlbum();

    Album selectOne1(String id);


}
