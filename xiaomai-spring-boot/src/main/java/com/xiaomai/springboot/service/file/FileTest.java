package com.xiaomai.springboot.service.file;

import org.junit.Test;

import java.io.*;

/**
 * @author Madison
 * @date 2022/6/23
 */
public class FileTest {
    @Test
    public void test01(){
        FileService fileService1 = new LocalFileService();
        FileService fileService2 = new MinioFileService();
        
        System.out.println(FileService.getInterfaceName());
        
        System.out.println(fileService2.impTypeName());
        System.out.println(fileService1.impTypeName());
        System.out.println(fileService2.impTypeName());
       
       
    }
}
