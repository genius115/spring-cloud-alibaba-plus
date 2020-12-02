package com.xiaomai.cloud.service.minio;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.xiaomai.cloud.config.BusinessException;
import com.xiaomai.cloud.config.Constant;
import com.xiaomai.cloud.config.minio.MinIOConfig;
import com.xiaomai.cloud.config.minio.MinIoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wangfeng
 * @date 2020/11/26
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {

    @Resource
    private MinIOConfig minIOConfig;

    @Autowired
    private MinIoUtils minIoUtils;

    // 上传 <html> ,返回服务器地址
    @Override
    public String uploadHtml(String fileName, String filePath) throws Exception {
        return minIOConfig.uploadHtml(fileName, filePath);
    }

    // 上传 <img> ,返回服务器地址
    @Override
    public String uploadImg(String imgName, String imgPath) throws Exception {
        return minIOConfig.uploadImg(imgName, imgPath);
    }


    @Override
    public String upload(MultipartFile file) throws BusinessException{
        verifyFile(file);
        try {
//            byte[] filebyte = file.getBytes();
//            System.out.println("file = [" + filebyte.length + "]");

            String bucketName = "file";
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf("."));

            String saveFileName = DateUtil.today()+"/"+IdUtil.fastSimpleUUID()+suffix;

            //创建存储桶
            minIoUtils.makeBucket(bucketName);
            //存储对象
            minIoUtils.putObject(bucketName, file, saveFileName);


            return "OK"+new Date();
        }catch (Exception e){
            throw new BusinessException("上传失败！");
        }
    }

    private void verifyFile(MultipartFile file) throws BusinessException {
        Long fileSize = file.getSize();
        log.info("文件大小："+fileSize/1000);
        boolean flag = true;
        if (file.isEmpty()){
            throw new BusinessException("文件为空！");
        }
        if (fileSize > Constant.VIDEO_MAX_SIZE.getValue()){
            throw new BusinessException("文件太大无法上传！");
        }
        // 文件原名
        String fileNameOriginal = file.getOriginalFilename();
        String mimeType = file.getContentType();

        if (mimeType.startsWith("image/")) {
            if (fileSize > Constant.IMG_MAX_SIZE.getValue()){
                throw new BusinessException("图片太大无法上传！");
            }
            flag = false;
        }

//        if(fileNameOriginal.endsWith(".ove")){
//            if (fileSize > Constant.IMG_MAX_SIZE){
//                throw new BusinessException("ove文件太大无法上传！");
//            }
//            flag = false;
//        }
//
//        if (mimeType.startsWith("audio/")) {
//            if (fileSize > Constant.MUSIC_MAX_SIZE){
//                throw new BusinessException("声音太大无法上传！");
//            }
//            flag = false;
//        }
//
//        if (mimeType.startsWith("video/")) {
//            if (fileSize > Constant.VIDEO_MAX_SIZE){
//                throw new BusinessException("视频太大无法上传！");
//            }
//            flag = false;
//        }

        if (flag){
            throw new BusinessException("文件格式不合法！");
        }
    }

    @Override
    public String downurl(String objectName){
        String url = minIoUtils.presignedObjectUrl(null, objectName,-1);
        log.info("{}图片地址：{}",objectName,url);
        return url;
    }
}