package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "banner")
public class Master {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String dharma;
    private String headImg;
    private Integer status;
    @Transient
    private List<Article> articles;

}
