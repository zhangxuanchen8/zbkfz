package com.ehinfo.hr.common.utils;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.DVConstraint;  
import org.apache.poi.hssf.usermodel.HSSFDataValidation;  
import org.apache.poi.hssf.usermodel.HSSFWorkbook;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.CellStyle;  
import org.apache.poi.ss.usermodel.DataValidation;  
import org.apache.poi.ss.usermodel.Font;  
import org.apache.poi.ss.usermodel.IndexedColors;  
import org.apache.poi.ss.usermodel.Name;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class CreateExcelMoBusiness {  
  
  
	
    public static Workbook toWorkBook(String file) {
    		boolean isExcel2003 = file.toLowerCase().endsWith("xls")?true:false;
    		Workbook workbook = null;
    		try {
    			if(isExcel2003){
        			workbook = new HSSFWorkbook(new FileInputStream(new File(file)));
        		}else{
        			workbook = new XSSFWorkbook(new FileInputStream(new File(file)));
        		}
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return workbook;
    }
    
    public static void  createExcelMo(Workbook wb){  
            Sheet sheet = wb.createSheet("用户分类添加批导");     
            // Create a row and put some cells in it. Rows are 0 based.     
            Row row = sheet.createRow(0);     
            Cell cell = row.createCell(0);     
            cell.setCellValue("手机号码");     
            cell.setCellStyle(getTitleStyle(wb));     
            cell = row.createCell(1);     
            cell.setCellValue("所属父类");     
            cell.setCellStyle(getTitleStyle(wb));     
            cell = row.createCell(2);     
            cell.setCellValue("所属子类");     
            cell.setCellStyle(getTitleStyle(wb));     
            cell = row.createCell(3);     
    }   
    /**   
     * 设置模板文件的横向表头单元格的样式   
     * @param wb   
     * @return   
     */    
    public static CellStyle getTitleStyle(Workbook wb){     
        CellStyle style = wb.createCellStyle();     
        //对齐方式设置     
        style.setAlignment(CellStyle.ALIGN_CENTER);     
        //边框颜色和宽度设置     
        style.setBorderBottom(CellStyle.BORDER_THIN);     
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());     
        style.setBorderLeft(CellStyle.BORDER_THIN);     
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());     
        style.setBorderRight(CellStyle.BORDER_THIN);     
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());     
        style.setBorderTop(CellStyle.BORDER_THIN);     
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());     
        style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());     
        //设置背景颜色     
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());     
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);     
        //粗体字设置     
        Font font = wb.createFont();     
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);     
        style.setFont(font);     
        return style;     
    }    
      
    /**   
     * 创建一个名称   
     * @param workbook   
     */    
    public static void creatExcelNameList(Workbook workbook,String nameCode,int order,int size,boolean cascadeFlag,String SheetName){     
        Name name;     
        name = workbook.createName();     
        name.setNameName(nameCode);     
        name.setRefersToFormula(SheetName+"!"+creatExcelNameList(order,size,cascadeFlag));  
        System.out.println(creatExcelNameList(order,size,cascadeFlag));
    }     
      
    /**   
     * 名称数据行列计算表达式   
     * @param workbook   
     */    
    public static String creatExcelNameList(int order,int size,boolean cascadeFlag){     
        char start = 'A';     
        if(cascadeFlag){     
            start = 'B';     
            if(size<=25){     
                char end = (char)(start+size-1);     
                return "$"+start+"$"+order+":$"+end+"$"+order;     
            }else{     
                char endPrefix = 'A';     
                char endSuffix = 'A';     
                if((size-25)/26==0||size==51){//26-51之间，包括边界（仅两次字母表计算）     
                    if((size-25)%26==0){//边界值     
                        endSuffix = (char)('A'+25);     
                    }else{     
                        endSuffix = (char)('A'+(size-25)%26-1);     
                    }     
                }else{//51以上     
                    if((size-25)%26==0){     
                        endSuffix = (char)('A'+25);     
                        endPrefix = (char)(endPrefix + (size-25)/26 - 1);     
                    }else{     
                        endSuffix = (char)('A'+(size-25)%26-1);     
                        endPrefix = (char)(endPrefix + (size-25)/26);     
                    }     
                }     
                return "$"+start+"$"+order+":$"+endPrefix+endSuffix+"$"+order;     
            }     
        }else{     
            if(size<=26){     
                char end = (char)(start+size-1);     
                return "$"+start+"$"+order+":$"+end+"$"+order;     
            }else{     
                char endPrefix = 'A';     
                char endSuffix = 'A';     
                if(size%26==0){     
                    endSuffix = (char)('A'+25);     
                    if(size>52&&size/26>0){     
                        endPrefix = (char)(endPrefix + size/26-2);     
                    }     
                }else{     
                    endSuffix = (char)('A'+size%26-1);     
                    if(size>52&&size/26>0){     
                        endPrefix = (char)(endPrefix + size/26-1);     
                    }     
                }     
                return "$"+start+"$"+order+":$"+endPrefix+endSuffix+"$"+order;     
            }     
        }     
    }   
      
    /**   
     * 创建一列数据   
     * @param currentRow   
     * @param textList   
     */    
    public static void creatRow(Row currentRow,String[] textList){     
        if(textList!=null&&textList.length>0){     
            int i = 0;     
            for(String cellValue : textList){     
                Cell userNameLableCell = currentRow.createCell(i++);     
                userNameLableCell.setCellValue(cellValue);     
            }     
        }     
    }  
  
    /**   
     * 使用已定义的数据源方式设置一个数据验证   
     * @param formulaString   
     * @param naturalRowIndex   
     * @param naturalColumnIndex   
     * @return   
     */    
    public static DataValidation getDataValidationByFormula(String formulaString,int naturalRowIndex,int naturalColumnIndex){     
        //加载下拉列表内容       
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaString);      
        //设置数据有效性加载在哪个单元格上。       
        //四个参数分别是：起始行、终止行、起始列、终止列       
        int firstRow = naturalRowIndex-1;     
        int lastRow = naturalRowIndex-1;     
        int firstCol = naturalColumnIndex-1;     
        int lastCol = naturalColumnIndex-1;     
        CellRangeAddressList regions=new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);       
        //数据有效性对象      
        DataValidation data_validation_list = new HSSFDataValidation(regions,constraint);     
        //设置输入信息提示信息     
        data_validation_list.createPromptBox("下拉选择提示","请使用下拉方式选择合适的值！");     
        //设置输入错误提示信息     
        data_validation_list.createErrorBox("选择错误提示","你输入的值未在备选列表中，请下拉选择合适的值！");     
        return data_validation_list;     
    }     
      
    public static DataValidation getDataValidationByDate(int naturalRowIndex,int naturalColumnIndex){     
        //加载下拉列表内容       
        DVConstraint constraint = DVConstraint.createDateConstraint(DVConstraint.OperatorType.BETWEEN,"1900-01-01", "5000-01-01", "yyyy-mm-dd");      
        //设置数据有效性加载在哪个单元格上。       
        //四个参数分别是：起始行、终止行、起始列、终止列       
        int firstRow = naturalRowIndex-1;     
        int lastRow = naturalRowIndex-1;     
        int firstCol = naturalColumnIndex-1;     
        int lastCol = naturalColumnIndex-1;     
        CellRangeAddressList regions=new CellRangeAddressList(firstRow,lastRow,firstCol,lastCol);       
        //数据有效性对象      
        DataValidation data_validation_list = new HSSFDataValidation(regions,constraint);     
        //设置输入信息提示信息     
        data_validation_list.createPromptBox("日期格式提示","请按照'yyyy-mm-dd'格式输入日期值！");     
        //设置输入错误提示信息     
        data_validation_list.createErrorBox("日期格式错误提示","你输入的日期格式不符合'yyyy-mm-dd'格式规范，请重新输入！");     
        return data_validation_list;     
    }      
}  