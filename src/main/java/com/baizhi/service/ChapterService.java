package com.baizhi.service;


import com.baizhi.entity.Chapter;


public interface ChapterService {
    // public void insertByAlbumId(Chapter chapter);
    public void insert(Chapter chapter);

    public Chapter selectOne(Chapter chapter);
}
