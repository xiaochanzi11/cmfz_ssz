package com.baizhi.service.impl;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public Map queryAll(int page, int rows) {
        Map map = new HashMap();
        PageHelper.startPage(page, rows);
        PageInfo<User> pageInfo = new PageInfo<>(userMapper.selectAll());
        map.put("total", pageInfo.getTotal());
        map.put("rows", pageInfo.getList());
        return map;
    }

    @Override
    public void insert(User user) {
        user.setCreateDate(new Date());
        user.setStatus(0);
        userMapper.insert(user);

    }
}
