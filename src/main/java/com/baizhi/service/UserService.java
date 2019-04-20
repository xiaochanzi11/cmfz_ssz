package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.Map;


public interface UserService {

    Map queryAll(int page, int rows);

    void insert(User user);


}
