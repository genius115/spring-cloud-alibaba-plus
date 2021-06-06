package com.xiaomai.office;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

/**
 * @author Madison
 * @date 2021/4/8
 */
public class Pdf2WordSpire {
    public static void main(String[] args) {
        PdfDocument pdf = new PdfDocument("D:/test/01/1.pdf");
        pdf.saveToFile("D:/test/01/ToWord.doc",FileFormat.DOC);
        pdf.close();


        PdfDocument pdf2 = new PdfDocument("D:/test/01/1.pdf");
        pdf2.saveToFile("D:/test/01/ToHTML.html", FileFormat.HTML);
        pdf2.close();
        try{
            Document doc = new Document("D:/test/01/ToWord.doc");
            doc.save("D:/test/01/1-pdf.pdf",SaveFormat.PDF);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("执行完成！");
    }
}
