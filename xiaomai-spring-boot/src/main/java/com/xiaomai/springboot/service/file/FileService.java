package com.xiaomai.springboot.service.file;

/**
 * @author Madison
 * @date 2022/6/2
 */
public interface FileService {
    String uploadFile();
    
    String downloadFile(String fileId);
}
