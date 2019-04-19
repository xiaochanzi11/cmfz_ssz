package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String name;
    private String dharma;
    private Integer sex;
    private String province;
    private String city;
    private String sign;
    private Integer status;
    private String phone;
    private String password;
    private Integer salt;
    private String headImg;
    @JSONField(format = "yyyy-MM-dd HH-mm-ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    private Date createDate;
    private Integer masterId;
    @Transient
    private Master master;

}
