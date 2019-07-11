package com.ehinfo.hr.entity.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.type.Alias;
import com.ehinfo.hr.common.utils.DateUtils;
import com.ehinfo.hr.common.utils.PropertyUtil;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.UuidUtil;

@Alias("BaseFile")
public class BaseFile {
	private String id;
	private String filename;
	private byte [] files;
	private String filetype;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
	//--------------------------------将二进制数据转换为相应的文件------------------------------------------
	public byte[] getFiles() {
		return files;
	}
	public void setFiles(byte[] files) {
		this.files = files;
	}
	
	
	
	
	@Override
	public String toString() {
		return "BaseFile [id=" + id + ", filename=" + filename + ", files=" + Arrays.toString(files) + ", filetype="
				+ filetype + "]";
	}
	public String byteToFile (HttpServletRequest request)  
	{  
		//----------------------------------tomcat的位置+配置文件中docFilePath设置的路径+随机的UUID文件名+文件后缀
		Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
		String fileFilePath=uploadMap.get("docFilePath");//允许文件位置
		String realPath=request.getSession().getServletContext().getRealPath("/");
        String filePath=realPath+fileFilePath;
        String fileName =DateUtils.getDate("yyyyMMdd")+ UuidUtil.get32UUID()+"."+this.filetype;  
		 BufferedOutputStream bos = null;  
	        FileOutputStream fos = null;  
	        File file = null;  
	        try  
	        {  
	            File dir = new File(filePath);  
	            if (!dir.exists() && dir.isDirectory())  
	            {  
	                dir.mkdirs();  
	            }  
	            dir.mkdirs(); 
	            file = new File(filePath + File.separator + fileName);  
	            fos = new FileOutputStream(file);  
	            bos = new BufferedOutputStream(fos);  
	            bos.write(this.files);  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	        finally  
	        {  
	            if (bos != null)  
	            {  
	                try  
	                {  
	                    bos.close();  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	            if (fos != null)  
	            {  
	                try  
	                {  
	                    fos.close();  
	                }  
	                catch (IOException e)  
	                {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	    return "/"+fileFilePath+fileName;
	      
	}
	
	
	
}
