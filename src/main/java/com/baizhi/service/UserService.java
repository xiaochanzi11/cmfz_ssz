package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;


public interface UserService {

    Map queryAll(int page, int rows);

    void insert(User user);

    void update(User user);

    List<Integer> echarts();

    Map UserCount();

    List<User> selectAll();

    Object selectOne(String phone, String password);

}
