package com.xiaomai.springboot.service.file;

import cn.hutool.core.date.DateUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Madison
 * @date 2022/6/2
 */
@Service
public class LocalFileService implements FileService{

    @Override
    public String  uploadFile() {
        System.out.println("LocalFileService uploadFile！！！");
        return "LocalFileService:"+DateUtil.today();
    }
  
    @Override
    public String  downloadFile(String fileId) {
        System.out.println("LocalFileService downloadFile！！！"+fileId);
        return "LocalFileService:"+fileId;
    }
}
