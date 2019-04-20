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
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "chapter")
public class Chapter implements Serializable {

    @Excel(name = "音频编号")
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    @Excel(name = "音频标题")
    private String title;
    @Excel(name = "音频大小")
    private String size;
    @Excel(name = "音频时长")
    private String duration;
    @Excel(name = "入库日期", format = "yyyy年MM月dd日HH时mm分ss秒", width = 50)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private String albumId;
    private String downloadPath;

}
