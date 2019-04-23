package com.baizhi;

import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import com.baizhi.service.AlbumService;
import com.baizhi.service.FirstService;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzSszApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    AlbumService albumService;

    @Autowired
    FirstService firstService;

    @Test
    public void testMain() {

        //Object all = firstService.queryAll(1, "all", null);
        // Object all = firstService.queryAll(1, "wen", null);
        //Object all = firstService.queryAll(1, "si", "ssyj");
        Object all = firstService.queryAll(1, "si", "xmfy");
        System.out.println(all);
    }

    @Test
    public void album() {
        Album album = albumService.selectOne1("0dbb5d4d-a7df-4a22-839e-6e614f923b11");
        System.out.println(album);
    }

    @Test
    public void login() {

        Object o = userService.selectOne("15236456875", DigestUtils.md5Hex("1234516"));
        System.out.println(o);

    }

    @Test
    public void regist() {
        User user = new User();
        user.setPhone("15236456875");
        user.setPassword(DigestUtils.md5Hex("123456"));
        userService.insert(user);

    }


}
