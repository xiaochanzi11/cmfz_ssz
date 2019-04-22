package com.baizhi.service.impl;

import com.baizhi.entity.User;
import com.baizhi.mapper.UserMapper;
import com.baizhi.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public void update(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Integer> echarts() {
        List<Integer> list = new ArrayList<>();
        list.add(userMapper.echarts1());
        list.add(userMapper.echarts2());
        list.add(userMapper.echarts3());
        return list;
    }

    @Override
    public Map UserCount() {

        List<User> list1 = userMapper.selectMaleCount();
        List<User> list2 = userMapper.selectFemaleCount();
        List<Map> data1 = new ArrayList<>();
        List<Map> data2 = new ArrayList<>();

        for (User user : list1) {
            Map map = new HashMap();
            map.put("name", user.getProvince());
            map.put("value", user.getCounts());
            //System.out.println(map3+"1111111");
            data1.add(map);
        }

        for (User user : list2) {
            Map map = new HashMap();
            map.put("name", user.getProvince());
            map.put("value", user.getCounts());
            data2.add(map);
        }
        Map map2 = new HashMap();
        map2.put("data1", data1);
        map2.put("data2", data2);
        return map2;
    }
}
