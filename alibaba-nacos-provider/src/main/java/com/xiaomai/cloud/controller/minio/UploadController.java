package com.xiaomai.cloud.controller.minio;

import com.xiaomai.cloud.po.order.Payment;
import com.xiaomai.cloud.service.minio.UploadService;
import com.xiaomai.cloud.service.minio.UploadServiceImpl;
import com.xiaomai.cloud.service.order.OrderServiceImpl;
import com.xiaomai.cloud.service.order.SlaveOrderServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangfeng
 * @date 2020/11/26
 */
@Api(value = "上传")
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService UploadService;

    @ApiOperation(value = "上传 <html> ,返回服务器地址", nickname = "图片")
    @GetMapping("/html")
    public String uploadHtml(String fileName, String filePath) throws Exception {
        return UploadService.uploadHtml(fileName, filePath);
    }

    @ApiOperation(value = " 上传 <img> ,返回服务器地址", nickname = "图片")
    @GetMapping("/img")
    public String uploadImg(String imgName, String imgPath) throws Exception {
        return UploadService.uploadImg(imgName, imgPath);
    }

}
