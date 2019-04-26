package com.baizhi.service.impl;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public void insert(Chapter chapter) {
        chapter.setPublishDate(new Date());
        chapterMapper.insert(chapter);
    }

    @Override
    public Chapter selectOne(Chapter chapter) {
        Chapter chapter1 = chapterMapper.selectOne(chapter);
        return chapter1;
    }

    @Override
    public void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKeySelective(chapter);
    }
}
