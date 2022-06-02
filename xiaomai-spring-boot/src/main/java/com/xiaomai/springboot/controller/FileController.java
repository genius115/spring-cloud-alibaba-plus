package com.xiaomai.springboot.controller;

import cn.hutool.core.date.DateUtil;
import com.xiaomai.springboot.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2022/6/2
 */
@RestController
@RequestMapping("/file")
public class FileController {
    
    @Autowired
    private FileService fileService;
    
    @GetMapping("/upload")
    public String uploadFile(){
        return fileService.uploadFile();
    }
    
    @GetMapping("/downloadFile")
    public String downloadFile(String fileId){
        return fileService.downloadFile(fileId);
    }
}
