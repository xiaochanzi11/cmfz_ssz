package com.baizhi.service;

import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface BannerService {
    Map queryAll(int page, int rows);

    void delete(Banner banner);

    void insert(Banner banner);

    void update(Banner banner);

    List<Banner> selectAll();
}
