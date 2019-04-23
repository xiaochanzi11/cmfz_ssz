package com.baizhi.mapper;

import com.baizhi.entity.Article;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ArticleMapper extends Mapper<Article> {

    //师傅的文章
    List<Article> selectArticleByUid(Integer uid);

    //除了师傅其它上师的文章
    List<Article> selectArticleExceptUid(Integer uid);


}
