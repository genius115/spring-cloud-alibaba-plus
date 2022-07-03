package com.xiaomai.xiaomaispringbootbase.service.file;

/**
 * @author Madison
 * @date 2022/6/2
 */
public interface FileService {

    /**
     * 静态方法
     * @return
     */
    static String getInterfaceName(){
        return " FileService Static getInterfaceName："+System.currentTimeMillis();
    }

    /**
     * 默认方法
     * @return
     */
    default String impTypeName(){
        return "ImpTypeName: NULL";
    }
    
    String uploadFile();
    
    String downloadFile(String fileId);
}
