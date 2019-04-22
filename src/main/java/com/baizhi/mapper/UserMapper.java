package com.baizhi.mapper;

import com.baizhi.entity.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {

    public Integer echarts1();

    public Integer echarts2();

    public Integer echarts3();

    public List<User> selectMaleCount();

    public List<User> selectFemaleCount();

}
