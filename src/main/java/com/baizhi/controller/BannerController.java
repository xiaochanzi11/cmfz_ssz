package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.baizhi.service.BannerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@ResponseBody
@RequestMapping("banner")
public class BannerController {

    @Autowired
    BannerService bannerService;

    @Autowired
    BannerMapper bannerMapper;

    @RequestMapping("select")
    public Map selectAll(int page, int rows) {

        return bannerService.queryAll(page, rows);
    }

    @RequestMapping("insert")
    public Map insert(Banner banner, MultipartFile image) throws IOException {
        //System.out.println(banner);
        String uuid = UUID.randomUUID().toString();
        String oldName = image.getOriginalFilename();
        String newName = uuid + oldName.substring(oldName.lastIndexOf("."));
        image.transferTo(new File("D:\\cmfz_ssz\\cmfz_ssz\\src\\main\\webapp\\jsp\\images\\shouye\\" + newName));

        banner.setImgPath(newName);

        Map map = new HashMap();
        try {
            bannerService.insert(banner);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }

        return map;
    }

    @RequestMapping("delete")
    public Map delete(Banner banner) {
        Map map = new HashMap();
        try {
            bannerService.delete(banner);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        return map;
    }

    @RequestMapping("update")
    public Map update(Banner banner) {
        Map map = new HashMap();
        try {
            bannerService.update(banner);
            map.put("flag", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("flag", false);
        }
        return map;
    }

    @RequestMapping("exportXls")
    public Map exportXls() {
        Map map = new HashMap();
        List<Banner> list = bannerMapper.selectAll();
        //创建工作薄
        Workbook workbook = new HSSFWorkbook();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontName("楷体");
        font.setItalic(true);
        font.setColor(Font.COLOR_RED);
        //创建日期格式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");
        //创建样式
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //创建日期格式的样式
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        //创建工作表
        Sheet sheet = workbook.createSheet("banner");
        sheet.setColumnWidth(3, 20 * 256);

        String[] strings = {"编号", "标题", "图片路径", "添加日期", "状态"};

        Row row = sheet.createRow(0);
        for (int i = 0; i < strings.length; i++) {
            //创建单元格
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            //单元格赋值
            cell.setCellValue(strings[i]);
        }
        for (int i = 0; i < list.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getTitle());
            row1.createCell(2).setCellValue(list.get(i).getImgPath());
            row1.createCell(4).setCellValue(list.get(i).getStatus());
            Cell cell = row1.createCell(3);
            cell.setCellStyle(cellStyle1);
            cell.setCellValue(list.get(i).getCreateDate());
        }
        try {
            workbook.write(new FileOutputStream(new File("D:/cmfz_ssz_banner.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return map;
    }
}
