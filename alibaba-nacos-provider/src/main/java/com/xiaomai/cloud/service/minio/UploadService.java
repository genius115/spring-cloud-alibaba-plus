package com.xiaomai.cloud.service.minio;

import com.xiaomai.cloud.config.BusinessException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wangfeng
 * @date 2020/11/26
 */
public interface UploadService {
    /*
     *上传 <html> ,返回服务器地址
     */
    String uploadHtml(String fileName, String filePath) throws Exception;

    /*
     *上传 <img> ,返回服务器地址
     */
    String uploadImg(String imgName, String imgPath) throws Exception;

    String upload(MultipartFile file) throws BusinessException;

    String downurl(String objectName);
}
