package com.baizhi.controller;

import com.baizhi.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("first")
public class FirstController {

    @Autowired
    private FirstService firstService;

    @RequestMapping("queryAll")
    public Object queryAll(Integer uid, String type, String sub_type) {
        Object o = firstService.queryAll(uid, type, sub_type);
        System.out.println(o);
        return o;
    }
}
