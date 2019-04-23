package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import com.baizhi.service.ChapterService;
import com.baizhi.util.AudioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("chapter")
@ResponseBody
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @Autowired
    ChapterMapper chapterMapper;

    @RequestMapping("selectOne")
    public Chapter selectOne(Chapter chapter) {
        chapter = chapterMapper.selectOne(chapter);
        return chapter;
    }

    @RequestMapping("update")
    public Map update(MultipartFile file, Chapter chapter) throws IOException {
        Map map = new HashMap();
        String oldName = file.getOriginalFilename();
        //2.将接收的文件复制到服务器上
        /*String uuid = UUID.randomUUID().toString();
//        System.out.println("action 文件名"+oldName);
        String newname = uuid + oldName.substring(oldName.lastIndexOf("."));*/
        File file1 = new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\chapter\\" + oldName);
        file.transferTo(file1);
        chapter.setDownloadPath(oldName);
        chapter.setSize(getPrintSize(file.getSize()));
        chapter.setDuration(AudioUtil.getDuration(file1).toString());
        try {
            chapterService.update(chapter);
            map.put("isupdate", true);
        } catch (Exception e) {
            map.put("isupdate", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("insert")
    public Map addchapter(MultipartFile file, Chapter chapter) throws IOException {
        Map map = new HashMap<>();
        String oldName = file.getOriginalFilename();
        //2.将接收的文件复制到服务器上
        /*String uuid = UUID.randomUUID().toString();
//        System.out.println("action 文件名"+oldName);
        String newname = uuid + oldName.substring(oldName.lastIndexOf("."));*/
        File file1 = new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\chapter\\" + oldName);
        file.transferTo(file1);
        chapter.setDownloadPath(oldName);
        chapter.setSize(getPrintSize(file.getSize()));
        chapter.setDuration(AudioUtil.getDuration(file1).toString());
        try {
            chapterService.insert(chapter);
            map.put("isadd", true);
        } catch (Exception e) {
            map.put("isadd", false);
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("download")
    public void download(Chapter chapter, String fileName, HttpServletResponse resp) throws Exception {

        chapter = chapterService.selectOne(chapter);
        fileName = chapter.getDownloadPath();
        //1.读取要下载的文件
        InputStream is = new FileInputStream("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\chapter\\" + fileName);

        //设置响应头   attachment(附件)
        String s1 = URLEncoder.encode(fileName, "UTF-8");  //对中文进行编码 汉子 --> %3d
        resp.setHeader("content-disposition", "attachment;filename=" + s1);

        //2.获取给浏览器的响应输出流
        OutputStream os = resp.getOutputStream();

        //3.边读边写，将读取的内容响应输出给浏览器
        while (true) {
            int i = is.read();
            if (i == -1)
                break;
            os.write(i);
        }
    }

    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }
}
