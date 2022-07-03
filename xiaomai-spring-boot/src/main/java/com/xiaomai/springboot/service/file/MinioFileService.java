package com.xiaomai.springboot.service.file;

import cn.hutool.core.date.DateUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Madison
 * @date 2022/6/2
 */
@Primary
@Service
public class MinioFileService implements FileService{

    /**
     * 接口默认方法重载
     * @return
     */
    @Override
    public String impTypeName(){
        return "ImpTypeName: MinioFileService！！！";
    }
    
    @Override
    public String uploadFile() {
        System.out.println("MinioFileService uploadFile！！！");
        return "MinioFileService:"+DateUtil.today();
    }

    @Override
    public String downloadFile(String fileId) {
        System.out.println("MinioFileService downloadFile！！！"+fileId);
        return "MinioFileService:"+fileId;
    }
}
