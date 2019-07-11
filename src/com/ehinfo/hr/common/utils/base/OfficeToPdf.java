package com.ehinfo.hr.common.utils.base;

import java.io.File;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * 1.此处代码是把office文档转换成pdf的工具类，在BS架构的产品中，我们可以使用基于JS的pdf插件显示pdf文档，但是前提IE需要按照adobe的pdf软件，对于非IE不用安装。
 *2.可以基于flexPager插件显示文档，但是也需要把office转换成swf的文件才可以，实现需要完成转换。
 * */
public class OfficeToPdf {
    
    private static final int wdFormatPDF = 17;
    private static final int xlsFormatPDF = 0;
    private static final int pptFormatPDF = 32;
    private static final int msoTrue = -1;
    private static final int msofalse = 0;

    
    public static boolean convert2PDF(String inputFile, String pdfFile) {
        String suffix = getFileSufix(inputFile);
        File file = new File(inputFile);
        if (!file.exists()) {
            System.out.println("文件不存在！");
            return false;
        }
        if (suffix.equals("pdf")) {
            System.out.println("PDF not need to convert!");
            return false;
        }
        if (suffix.equals("doc") || suffix.equals("docx")) {
            return word2PDF(inputFile, pdfFile);
        } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
            return ppt2PDF(inputFile, pdfFile);
        } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
            return excel2PDF(inputFile, pdfFile);
        } else {
            System.out.println("文件格式不支持转换!");
            return false;
        }
    }

    private static String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }

    private static boolean word2PDF(String inputFile, String pdfFile) {
        try {
            ActiveXComponent app = new ActiveXComponent("Word.Application");
            app.setProperty("Visible", false);
            Dispatch docs = app.getProperty("Documents").toDispatch();
            Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true)
                    .toDispatch();
            File tofile = new File(pdfFile);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF 
                    );
            Dispatch.call(doc, "Close", false);
            app.invoke("Quit", 0);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean excel2PDF(String inputFile, String pdfFile) {
        try {
            ActiveXComponent app = new ActiveXComponent("Excel.Application");
            app.setProperty("Visible", false);
            Dispatch excels = app.getProperty("Workbooks").toDispatch();
            Dispatch excel = Dispatch.call(excels, "Open", inputFile, false,
                    true).toDispatch();
            File tofile = new File(pdfFile);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(excel, "ExportAsFixedFormat", xlsFormatPDF, pdfFile);
            Dispatch.call(excel, "Close", false);
            app.invoke("Quit");
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    private static boolean ppt2PDF(String inputFile, String pdfFile) {
        try {
            ActiveXComponent app = new ActiveXComponent(
                    "PowerPoint.Application");
            Dispatch ppts = app.getProperty("Presentations").toDispatch();
            Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true,// ReadOnly
                    true,// Untitled指定文件是否有标题
                    false// WithWindow指定文件是否可见
                    ).toDispatch();
            File tofile = new File(pdfFile);
            if (tofile.exists()) {
                tofile.delete();
            }
            Dispatch.call(ppt, "SaveAs", pdfFile, pptFormatPDF);
            Dispatch.call(ppt, "Close");
            app.invoke("Quit");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    public static void main(String[] args) {
        //OfficeToPdfTools.convert2PDF("c:\\ppt.pptx", "c:\\ppt.pdf");
        OfficeToPdf.convert2PDF("c:\\excel.xls", "c:\\excel.pdf");
        //OfficeToPdfTools.convert2PDF("c:\\word.docx", "c:\\word.pdf");
        
    }
}