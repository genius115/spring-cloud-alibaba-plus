package com.xiaomai.office;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * @author Madison
 * @date 2021/4/8
 */
public class ITextTest {
    public static void main(String[] args) throws Exception {
        /*
        Document document =new Document(); // 默认页面大小是A4
        //Document document =new Document(PageSize.A4); // 指定页面大小为A4
        //Document document =new Document(PageSize.A4,50,50,30,20); // 指定页面大小为A4，且自定义页边距(marginLeft、marginRight、marginTop、marginBottom)
        //其中页面大小PageSize也可自定义大小，例：new Document(new Rectangle(400, 500));

        //2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        //创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径
        PdfWriter writer =PdfWriter.getInstance(document,new FileOutputStream(filePath));

        //3.打开文档写入数据之前要打开文档
        document.open();

        //4.向文档中添加内容
        document.add();

        document.addTitle("Title@PDF-Java");// 标题
        document.addAuthor("Author@umiz");// 作者
        document.addSubject("Subject@iText pdf sample");// 主题
        document.addKeywords("Keywords@iTextpdf");// 关键字
        document.addCreator("Creator@umiz`s");// 创建者

        //5.关闭文档
        document.close();
        */

        File file;
        OutputStream out = new FileOutputStream(new File("D:/test/01/iText-01.pdf"));
        //页面大小
        Rectangle rect = new Rectangle(PageSize.B5.rotate());
        //页面背景色
        //rect.setBackgroundColor(BaseColor.ORANGE);

        Document doc = new Document(rect);

        PdfWriter writer = PdfWriter.getInstance(doc, out);

        //PDF版本(默认1.4)
        writer.setPdfVersion(PdfWriter.VERSION_1_4);

        //文档属性
        doc.addTitle("Title@sample");
        doc.addAuthor("Author@rensanning");
        doc.addSubject("Subject@iText sample");
        doc.addKeywords("Keywords@iText");
        doc.addCreator("Creator@iText");

        //页边空白
        doc.setMargins(10, 20, 30, 40);

        doc.open();

        doc.add(new Paragraph("Hello World"));

        doc.close();


    }
}
