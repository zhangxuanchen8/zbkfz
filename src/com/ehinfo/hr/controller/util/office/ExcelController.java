package com.ehinfo.hr.controller.util.office;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/excel")
public class ExcelController {
	
	private static final long FILE_MAX_SIZE = 4 * 1024 * 1024; 
	
	@RequestMapping(value="/read", method=RequestMethod.POST)
	@ResponseBody
	public void read(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletResponse response, HttpServletRequest request) {	
	        //输入流   
	        InputStream fis;
			try {
				fis = file.getInputStream();
				 List list = ExcelController.importEmployeeByPoi(fis);   
			        
			        fis.close();  
			        
			     response.getWriter().print(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}   
	           
	        //JXL:得到解析Excel的实体集合   
	        // List<EmployeeInfo> infos = ImportEmployee.importEmployee(fis);   
	           
	        //POI:得到解析Excel的实体集合   
	      
		 
	}
	
	public static List importEmployeeByPoi(InputStream fis) {  
        
        List result = new ArrayList();
       
        try {  
            //创建Excel工作薄   
        	org.apache.poi.ss.usermodel.Workbook wbs = WorkbookFactory.create(fis);  
            //得到第一个工作表   
           //Workbook wbs = WorkbookFactory.create(f.getInputStream()); 
            org.apache.poi.ss.usermodel.Sheet sheet = wbs.getSheetAt(0);
            //HSSFSheet sheet = hwb.getSheetAt(0);  
            Row row = null;  
           
            
            int colSum = 0;
            
            sheet = wbs.getSheetAt(0);
            for(int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
            	row = sheet.getRow(j);
            	if(j==0){
            		colSum = row.getPhysicalNumberOfCells();
            	}else{
            		if(colSum > row.getPhysicalNumberOfCells()){
            			colSum = row.getPhysicalNumberOfCells();
            		}
            	}
            }
            if(colSum>40){															//最多只读30行
            	colSum=40;
            }
            
            result.add(colSum);
           
            //遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数    
          //  for(int i = 0; i < wbs.getNumberOfSheets(); i++) {  					//--------------读第几个sheet-----这里只读第一个sheet
                sheet = wbs.getSheetAt(0);  
                //遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数   
                for(int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {  		//--------------读第几行
                	
                      
                	row = sheet.getRow(j);    
                	
                      for(int k=0;k<colSum;k++){
                    	  
                    	  if(k<5){
                    		  
                    		  if(getStringCellValue(row.getCell(k)).split(".").length>0){
                    			  result.add(getStringCellValue(row.getCell(k)).split(".")[0].trim()) ;
                    		  }else{
                    			  result.add(getStringCellValue(row.getCell(k)).trim()) ;
                    		  }
                    	  }else{
                    		  result.add(getStringCellValue(row.getCell(k)).trim()) ;
                    	  }
                    	  
                      }
                      
                     
                  						//---------------读第几列
                   
                }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    } 
	
	private static String getStringCellValue(Cell cell) {
        String strCell = "";
        DecimalFormat df = new DecimalFormat("#");
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            strCell = cell.getStringCellValue();
            break;
        case Cell.CELL_TYPE_NUMERIC:
        	strCell = df.format(cell.getNumericCellValue());
           // strCell = String.valueOf(cell.getNumericCellValue());
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            strCell = String.valueOf(cell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_BLANK:
            strCell = "";
            break;
        default:
            strCell = "";
            break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }
	
	
   
}
