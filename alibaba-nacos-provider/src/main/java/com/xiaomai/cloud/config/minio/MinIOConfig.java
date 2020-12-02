package com.xiaomai.cloud.config.minio;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 封装MinIO客户端连接工具，文件上传的基础方法，返回文件在MinIO服务上的URL地址
 * @author wangfeng
 * @date 2020/11/26
 */
@Component
public class MinIOConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MinIOConfig.class) ;

    @Resource
    private ParamConfig paramConfig ;

    private MinioClient minioClient ;

    /**
     * 初始化 MinIO 客户端
     */
    @PostConstruct
    private void init(){
        try {
            minioClient = new MinioClient(paramConfig.getEndpoint(),
                    paramConfig.getAccessKey(),
                    paramConfig.getSecretKey());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("MinIoClient init fail ...");
        }
    }

    /**
     * 上传 <html> 页面
     */
    public String uploadHtml (String fileName, String filePath) throws Exception {
        PutObjectOptions options = new PutObjectOptions(52428800L,52428800L);
        minioClient.putObject(paramConfig.getBucketNameHtml(), fileName, filePath,options);
        return paramConfig.getEndpoint()+"/"+paramConfig.getBucketNameHtml()+"/"+fileName ;
    }

    /**
     * 上传 <img> 图片
     */
    public String uploadImg (String imgName, String imgPath) throws Exception {
        PutObjectOptions options = new PutObjectOptions(52428800L,52428800L);
        minioClient.putObject(paramConfig.getBucketNameImage(),imgName,imgPath,options);
        return paramConfig.getEndpoint()+"/"+paramConfig.getBucketNameImage()+"/"+imgName ;
    }
}