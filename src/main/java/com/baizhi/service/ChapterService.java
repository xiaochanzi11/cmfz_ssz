package com.baizhi.service;


import com.baizhi.entity.Chapter;


public interface ChapterService {

    void insert(Chapter chapter);

    Chapter selectOne(Chapter chapter);

    void update(Chapter chapter);
}
