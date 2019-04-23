package com.baizhi.service.impl;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public Map queryAll(int page, int rows) {
        Map map = new HashMap();
        PageHelper.startPage(page, rows);
        PageInfo<Banner> pageInfo = new PageInfo<Banner>(bannerMapper.selectAll());
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());

        return map;
    }

    @Override
    public void delete(Banner banner) {
        bannerMapper.delete(banner);
    }

    @Override
    public void insert(Banner banner) {

        banner.setCreateDate(new Date());
        banner.setStatus(0);
        bannerMapper.insert(banner);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.updateByPrimaryKeySelective(banner);
    }

    @Override
    public List<Banner> selectAll() {
        List<Banner> banners = bannerMapper.selectAll();
        return banners;
    }
}
