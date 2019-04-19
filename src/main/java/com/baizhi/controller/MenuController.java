package com.baizhi.controller;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menu")
@ResponseBody
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("select")
    public List<Menu> queryAll() {
        List<Menu> list = menuService.queryAll();
        return list;
    }
}
