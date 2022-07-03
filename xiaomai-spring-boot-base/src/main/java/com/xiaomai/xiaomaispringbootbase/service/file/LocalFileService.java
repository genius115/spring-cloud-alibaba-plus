package com.xiaomai.xiaomaispringbootbase.service.file;

import com.xiaomai.xiaomaispringbootbase.Dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Madison
 * @date 2022/6/2
 */
@Service
public class LocalFileService implements FileService{
    
    @Autowired
    public TestMapper testMapper;

    /**
     * 接口默认方法重载
     * @return
     */
    @Override
    public String impTypeName(){
        return "ImpTypeName: LocalFileService！！！";
    }

    @Override
    public String  uploadFile() {
        System.out.println("LocalFileService uploadFile！！！");
        return "LocalFileService:"+testMapper.querybook();
    }
  
    @Override
    public String  downloadFile(String fileId) {
        System.out.println("LocalFileService downloadFile！！！"+fileId);
        return "LocalFileService:"+fileId;
    }
}
