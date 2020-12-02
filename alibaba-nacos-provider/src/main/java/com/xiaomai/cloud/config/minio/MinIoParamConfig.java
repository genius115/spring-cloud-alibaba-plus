package com.xiaomai.cloud.config.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wangfeng
 * @date 2020/11/30
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinIoParamConfig {

    private String url;
    private String endpoint ;
    private String accessKey ;
    private String secretKey ;
    private String bucketName;
    private String bucketNameHtml ;
    private String bucketNameImage ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

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

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
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
