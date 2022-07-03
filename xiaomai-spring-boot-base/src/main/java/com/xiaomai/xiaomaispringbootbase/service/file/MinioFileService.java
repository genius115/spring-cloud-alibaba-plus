package com.xiaomai.xiaomaispringbootbase.service.file;

import com.xiaomai.xiaomaispringbootbase.Dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @author Madison
 * @date 2022/6/2
 */
@Primary
@Service
public class MinioFileService implements FileService{
    
    @Autowired
    public TestMapper testMapper;

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
        return "MinioFileService:"+testMapper.querybook();
    }

    @Override
    public String downloadFile(String fileId) {
        System.out.println("MinioFileService downloadFile！！！"+fileId);
        return "MinioFileService:"+fileId;
    }
}
