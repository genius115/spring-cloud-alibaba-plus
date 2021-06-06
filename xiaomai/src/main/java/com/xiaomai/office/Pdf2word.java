package com.xiaomai.office;

/**
 * @author Madison
 * @date 2021/4/8
 */
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.pdfbox.util.PDFTextStripper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pdf2word
{
    public static void main(String[] args)
    {
        try{
            String pdfFile = "D:/test/01/1.pdf";
            PDDocument doc = PDDocument.load(new File(pdfFile));
            int pagenumber = doc.getNumberOfPages();
            pdfFile = pdfFile.substring(0, pdfFile.lastIndexOf("."));
            String fileName = pdfFile + "_new.doc";
            File file = new File(fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer writer = new OutputStreamWriter(fos, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setSortByPosition(true);// 排序
            stripper.setStartPage(1);// 设置转换的开始页
            stripper.setEndPage(pagenumber);// 设置转换的结束页
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
            System.out.println("pdf转换word成功！");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        try {
            String pdfFile = "D:/test/01/1.pdf";
            PDDocument doc = PDDocument.load(new File(pdfFile));
            List<PDImageXObject> imagelist=  getImageListFromPDF(doc,0);
            System.out.println("图片个数 ： "+imagelist.size());

            for (int i=0;i<imagelist.size();i++){
                writeImageInputStream(imagelist.get(i));//写入文件系统
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /**
     * 从pdf文档中读取所有的图片信息
     *
     * @return
     * @throws Exception
     */
    public static List<PDImageXObject> getImageListFromPDF(PDDocument document, Integer startPage) throws Exception {
        List<PDImageXObject> imageList = new ArrayList<PDImageXObject>();
        if(null != document){
            PDPageTree pages = document.getPages();
            startPage = startPage == null ? 0 : startPage;
            int len = pages.getCount();
            System.out.println("页数 "+len);
            if(startPage < len){
                for(int i=startPage;i<len;i++){
                    PDPage page = pages.get(i);
                    if(page!=null) {
                        if( page.getResources()!=null) {
                            Iterable<COSName> objectNames = page.getResources().getXObjectNames();

                            for (COSName imageObjectName : objectNames) {
                                if (page.getResources().isImageXObject(imageObjectName)) {
                                    imageList.add((PDImageXObject) page.getResources().getXObject(imageObjectName));
                                }
                            }
                        }else {
                            System.out.println("当前页面没有图片 page.getResources() is null ");
                        }
                    }else {
                        System.out.println("page is null ");
                    }

                }
            }
        }
        return imageList;
    }
    /**
     * 读取图片文件流信息
     * @param image
     * @return
     * @throws Exception
     */
    public static InputStream getImageInputStream(PDImageXObject image) throws Exception
    {
        if(null!=image && null!= image.getImage())
        {
            BufferedImage bufferImage = image.getImage();
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bufferImage, image.getSuffix(), os);
            return new ByteArrayInputStream(os.toByteArray());
        }
        return null;
    }

    /**
     * 写入文件系统
     * @param image
     * @throws Exception
     */
    public static void writeImageInputStream(PDImageXObject image) throws Exception
    {
        if(null!=image && null!= image.getImage()) {
            //粗略写入到文件系统
            Date date=new Date();
            String name = date.getTime()+"_image" ;
            File imgFile = new File("D:/test/01/" + name + "." + image.getSuffix());//写入的地址
            FileOutputStream fout = new FileOutputStream(imgFile);
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            BufferedImage imageb = image.getImage();
            ImageIO.write(imageb, image.getSuffix(), os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            int byteCount = 0;
            byte[] bytes = new byte[1024];
            while ((byteCount = is.read(bytes)) > 0) {
                fout.write(bytes, 0, byteCount);
            }
            fout.close();
            is.close();
        }
    }

}
