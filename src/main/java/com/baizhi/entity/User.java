package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Excel(name = "编号")
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    @Excel(name = "姓名")
    private String name;
    @Excel(name = "法名")
    private String dharms;
    @Excel(name = "性别")
    private Integer sex;
    //@Excel(name="省份")
    private String province;
    //@Excel(name="城市")
    private String city;
    private String sign;
    private Integer status;
    @Excel(name = "手机号", width = 40)
    private String phone;
    private String password;
    private Integer salt;
    @Excel(name = "头像", type = 2, width = 30, height = 40)
    private String headImg;
    @Excel(name = "注册日期", format = "yyyy年MM月dd日HH时mm分ss秒", width = 50)
    @JSONField(format = "yyyy-MM-dd HH-mm-ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createDate;
    private Integer masterId;
    @Transient
    private Master master;

}
