package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("album")
@ResponseBody
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @Autowired
    AlbumMapper albumMapper;

    @RequestMapping("select")
    public List<Album> queryAll() {
        List<Album> list = albumService.queryAll();
        return list;
    }

    @RequestMapping("selectAlbum")
    public List<Album> selectAlbum() {
        List<Album> list = albumService.selectAlbum();
        return list;
    }

    @RequestMapping("selectAlbumByid")
    @ResponseBody
    public Album selectAlbumByid(String id) {
        System.out.println("======" + id);
        return albumService.selectAlbumByid(id);
    }


    @RequestMapping("addalbum")
    @ResponseBody
    public Map addalbum(MultipartFile file, Album album) throws IOException {
        Map map = new HashMap<>();
        String oldName = file.getOriginalFilename();
        //2.将接收的文件复制到服务器上
        String uuid = UUID.randomUUID().toString();
//        System.out.println("action 文件名"+oldName);
        String newname = uuid + oldName.substring(oldName.lastIndexOf("."));
        file.transferTo(new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\audioCollection\\" + newname));
        album.setId(uuid);
        album.setImgPath(newname);
        Date date = new Date();
        album.setPublishDate(date);
        try {
            albumService.insert(album);
            System.out.println();
            map.put("isadd", true);
        } catch (Exception e) {
            map.put("isadd", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response) {
        List<Album> list = albumMapper.select1();
        for (Album album : list) {
            album.setImgPath("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\audioCollection\\" + album.getImgPath());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持明法洲专辑", "专辑详情"), Album.class, list);
        String oldName = "easypoi_cmfz_album.xlsx";
        String encode = null;
        try {
            encode = URLEncoder.encode(oldName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置响应头
        response.setHeader("Content-Disposition", "attachment;fileName=" + encode);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //直接将写入到输出流中即可;
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
