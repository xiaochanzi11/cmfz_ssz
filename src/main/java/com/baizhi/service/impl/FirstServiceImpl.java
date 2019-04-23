package com.baizhi.service.impl;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.mapper.ArticleMapper;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("firstService")
@Transactional
public class FirstServiceImpl implements FirstService {

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private BannerMapper bannerMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Object queryAll(Integer uid, String type, String sub_type) {
        List<Banner> bannerList = bannerMapper.selectAll();
        List<Album> albumList = albumMapper.selectAlbum();
        List<Article> articleList = articleMapper.selectAll();
        List<Article> articleList1 = articleMapper.selectArticleByUid(uid);
        List<Article> articleList2 = articleMapper.selectArticleExceptUid(uid);
        if (uid == null || type == null) {
            return new Error("参数不能为空");
        } else {
            if (type.equals("all")) {
                Map<String, Object> map = new HashMap<>();

                map.put("header", bannerList);
                map.put("album", albumList);
                map.put("artical", articleList);
                return map;
            } else if (type.equals("wen")) {
                Map<String, Object> map = new HashMap<>();
                map.put("album", albumList);
                return map;
            } else {
                if (sub_type == null) {
                    return new Error("思类型为空未查到数据");
                } else {
                    if (sub_type.equals("ssyj")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("artical", articleList1);
                        return map;
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("artical", articleList2);
                        return map;
                    }
                }
            }
        }

    }
}
