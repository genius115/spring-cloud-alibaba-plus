package com.xiaomai.cloud.test;

import com.xiaomai.cloud.ExcelUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 *
 * HSSF － 提供读写Microsoft Excel格式档案的功能。
 * XSSF － 提供读写Microsoft Excel OOXML格式档案的功能。
 * HWPF － 提供读写Microsoft Word格式档案的功能。
 * HSLF － 提供读写Microsoft PowerPoint格式档案的功能。
 * HDGF － 提供读写Microsoft Visio格式档案的功能。
 *
 * HSSF：
 * HSSF是Horrible SpreadSheet Format的缩写，通过HSSF，你可以用纯Java代码来读取、写入、修改Excel文件。
 * HSSF为读取操作提供了两类API：usermodel和eventusermodel，即“用户模型”和“事件-用户模型”。
 *
 *
 * @author Madison
 * @date 2021/3/28
 */
public class PoiTest {

    @Test
    public void test() throws Exception {
        String filepath = "D:\\test\\NHMPMS.NH_SAND_MODEL_CODE.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(filepath));
        List<List<Object>> list = ExcelUtils.getListByExcel(inputStream, filepath);
        System.out.println(list);
    }
}
