package com.xiaomai.cloud.config.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Minio 配置参数类
 * @author wangfeng
 * @date 2020/11/26
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class ParamConfig {

    private String endpoint ;
    private String accessKey ;
    private String secretKey ;
    private String bucketNameHtml ;
    private String bucketNameImage ;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucketNameHtml() {
        return bucketNameHtml;
    }

    public void setBucketNameHtml(String bucketNameHtml) {
        this.bucketNameHtml = bucketNameHtml;
    }

    public String getBucketNameImage() {
        return bucketNameImage;
    }

    public void setBucketNameImage(String bucketNameImage) {
        this.bucketNameImage = bucketNameImage;
    }
}
