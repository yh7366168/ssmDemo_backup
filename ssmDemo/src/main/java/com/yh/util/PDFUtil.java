package com.yh.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.yh.util.exception.YhSystemException;

import java.awt.*;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author yh 2019-6-28 08:43:39
 * */
public class PDFUtil {

    public static void main(String[] args) {
        try{
            createPdfNoTemplateTest();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 不使用模板例子
     * */
    public static void createPdfNoTemplateTest(){
        Document document = null;
        FileOutputStream outputStream = null;
        String fileName = "C:\\Users\\T430487\\Desktop\\test.pdf";//生成的文件名字
        String fontPath = "C:\\Users\\T430487\\Desktop\\同牛\\pdf\\simsun.ttc"+",1";
        try{
            //pageSize、marginLeft、marginRight、marginTop、marginBottom
            document = new Document(PageSize.A4, 20, 20, 20, 20);
            outputStream = new FileOutputStream(new File(fileName));
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            //字体
            BaseFont baseFont = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font normalFont = new Font(baseFont, 9, Font.NORMAL);
            Font boldFont = new Font(baseFont, 9, Font.BOLD);
            Font redNormalFont = new Font(baseFont, 9 , -1, Color.red);
            document.open();
            //段落
            Paragraph titleParagraph = new Paragraph("附件二：", boldFont);
            document.add(titleParagraph);
            Paragraph paragraphOne = new Paragraph();
            Chunk chunkOne = new Chunk("  正文部分，我是", normalFont);
            Chunk chunkTwo = new Chunk("yh", redNormalFont);
            paragraphOne.add(chunkOne);
            paragraphOne.add(chunkTwo);
            document.add(paragraphOne);
            //表格
            Table table = new Table(3);
            table.setPadding(1f);
            table.setWidths(new float[]{50,50,50});
            //表格-表头
            String[] headStringArr = {"序号","列one","列two"};
            Cell[] headCellArr = new Cell[headStringArr.length];
            for(int i=0; i<headStringArr.length; i++){
                headCellArr[i] = new Cell(new Phrase(headStringArr[0],normalFont));
                headCellArr[i].setHorizontalAlignment(Element.ALIGN_CENTER);//上下居中
                headCellArr[i].setVerticalAlignment(Element.ALIGN_MIDDLE);//左右居中
                table.addCell(headStringArr[i]);
            }
            //表格-内容
            String[] infoStringArr = {"1","ww","ee"};
            Cell[] infoCellArr = new Cell[infoStringArr.length];
            for(int i=0; i<infoStringArr.length; i++){
                infoCellArr[i] = new Cell(new Phrase(infoStringArr[i], normalFont));
                infoCellArr[i].setHorizontalAlignment(Element.ALIGN_CENTER);
                infoCellArr[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(infoCellArr[i]);
            }
        }catch (Exception e){
            throw new YhSystemException("Pdf文件生成失败！");
        }finally {
            if(document != null){
                document.close();
            }
        }
    }

    /**
     * 关闭Closeable资源
     * */
    public static void closeSource(Closeable able, String errorMsg){
        try{
            if(able != null){
                able.close();
            }
        }catch (Exception e){
            throw new YhSystemException(errorMsg);
        }
    }
}
