package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "counter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    private String title;
    private Integer amount;

    private Integer taskId;
    private Integer userId;

    @Transient
    private Task task;
    @Transient
    private User user;

}
