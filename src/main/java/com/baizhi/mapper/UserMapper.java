package com.baizhi.mapper;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface UserMapper extends Mapper<User> {

    Integer echarts1();

    Integer echarts2();

    Integer echarts3();

    List<User> selectMaleCount();

    List<User> selectFemaleCount();

    User selectOne1(@Param("phone") String phone, @Param("password") String password);


}
