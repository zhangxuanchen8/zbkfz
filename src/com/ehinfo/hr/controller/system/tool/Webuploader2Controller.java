package com.ehinfo.hr.controller.system.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.ehinfo.hr.common.utils.DateUtils;
import com.ehinfo.hr.common.utils.PropertyUtil;
import com.ehinfo.hr.common.utils.base.Const;
import com.ehinfo.hr.common.utils.base.OfficeToPdf;
import com.ehinfo.hr.common.utils.base.Tools;
import com.ehinfo.hr.common.utils.base.UuidUtil;
import com.ehinfo.hr.controller.base.BaseController;
import com.ehinfo.hr.entity.file.BaseFile;
import com.ehinfo.hr.service.system.file.BaseFileService;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
/**
 * 上传工具
 */


@Controller
@RequestMapping("/tool/webuploader/")
public class Webuploader2Controller extends BaseController<Object>{
	@Autowired
	private BaseFileService service;
	/**
	 * 测试页
	 * @param change
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/test/{change}")	
	public String index(@PathVariable("change") String change) throws UnsupportedEncodingException{
			if(StringUtils.equals("img",change)){
				return "/system/tool/upload/img";
			}else if(StringUtils.equals("file", change)){
				return "/system/tool/upload/file";
			}else{
				return "/system/tool/upload/moreImg";
			}
	}
	
	@RequestMapping(value = "uploadPic")  
    public void uploadPic(@RequestParam(value = "file", required = false) MultipartFile file,
    		HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		PrintWriter out = response.getWriter();
		logger.info("上传图片开始");
		try {  
			
			
			Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
			String picAllowSuffix=uploadMap.get("picAllowSuffix");//允许图片文件规格
			String picAllowSize=uploadMap.get("picAllowSize");//允许图片文件大小
			String picFilePath=uploadMap.get("picFilePath");//允许图片文件大小		
			//获取文件后缀名
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if(StringUtils.isNotBlank(picAllowSuffix)){
		    	int length = picAllowSuffix.indexOf(suffix.toLowerCase());	    	
		        if(length == -1){
		        	json.put("res", Const.FAIL);
					json.put("resMsg", "请上传允许格式的图片");
					out.print(json.toString());
					return;
		        }	
			}	
			long size = file.getSize();
			if(StringUtils.isNotBlank(picAllowSize)){
				 long allowsize=Long.parseLong(picAllowSize);
			        if(size > allowsize){
			        	json.put("res", Const.FAIL);
						json.put("resMsg", "超过上传图片大小限制");
						out.print(json.toString());
						return;
			        }	
			}          
	        String realPath=request.getSession().getServletContext().getRealPath("/");
	        String path=realPath+picFilePath;
	        String fileName =DateUtils.getDate("yyyyMMdd")+ UuidUtil.get32UUID()+"."+suffix;  
	        File baseFile = new File(path);
	        String name=file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."))+"."+suffix;
			File targetFile = new File(baseFile, fileName);
			if(!baseFile.exists())baseFile.mkdirs();  
	        //保存  
            file.transferTo(targetFile);        	
        	json.put("res", Const.SUCCEED);
        	json.put("imgname",name);
        	json.put("resMsg", "上传成功");
			json.put("saveUrl","/"+picFilePath+fileName);
			json.put("size", size);
			out.print(json.toString());
			logger.info("上传图片结束，位置："+"/"+picFilePath+fileName);
        } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传图片出错",e);
		}		
    }  
	
	@RequestMapping(value = "uploadFile")  
    public void uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
    		HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		PrintWriter out = response.getWriter();
		logger.info("上传文件开始");
		try {  
			Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
			String fileAllowSuffix=uploadMap.get("fileAllowSuffix");//允许文件规格
			String fileAllowSize=uploadMap.get("fileAllowSize");//允许文件大小
			String fileFilePath=uploadMap.get("fileFilePath");//允许文件大小
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if(StringUtils.isNotBlank(fileAllowSuffix)){
				//获取文件后缀名	
		    	int length = fileAllowSuffix.indexOf(suffix.toLowerCase());
		        if(length == -1){
		        	json.put("res", Const.FAIL);
					json.put("resMsg", "请上传允许格式的文件");
					out.print(json.toString());
					return;
		        }
			}	    	
			long size = file.getSize();
			if(StringUtils.isNotBlank(fileAllowSize)){
				 long allowsize=Long.parseLong(fileAllowSize);
			        if(size > allowsize){
			        	json.put("res", Const.FAIL);
						json.put("resMsg", "超过上传文件大小限制");
						out.print(json.toString());
						return;
			        }	
			}   
	        String realPath=request.getSession().getServletContext().getRealPath("/");
	        String path=realPath+fileFilePath;
	        String fileName =DateUtils.getDate("yyyyMMdd")+ UuidUtil.get32UUID()+"."+suffix;  
	        File baseFile = new File(path);
			File targetFile = new File(baseFile, fileName);
			if(!baseFile.exists())baseFile.mkdirs();  
	        //保存  
            file.transferTo(targetFile);        	
        	json.put("res", Const.SUCCEED);
			json.put("saveUrl","/"+fileFilePath+fileName);
			json.put("resMsg", "上传成功");
			json.put("size", size);
			out.print(json.toString());
			logger.info("上传文件结束，位置："+path);
        } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传文件出错",e);
		}		
    }  
	
	
	/**
	 * 用前台传来的文件先存在本地,然后把本地文件转换为二进制数据传到数据库-------暂时的方法----以后看有没有直接存的(主要是MultipartFile类如何转为File类的问题---)
	 * 
	 * 
	 * */
	
	@RequestMapping(value = "uploadDocFile")  
    public void uploadDocFile(@RequestParam(value = "file", required = false) MultipartFile file,
    		HttpServletResponse response, HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		PrintWriter out = response.getWriter();
		logger.info("上传文件开始");
		try {  
			Map<String,String> uploadMap=PropertyUtil.getPropertyMap(Const.UPLOAD_CONFIG);
			String fileAllowSuffix=uploadMap.get("fileAllowSuffix");//允许文件规格
			String fileAllowSize=uploadMap.get("fileAllowSize");//允许文件大小
			String fileFilePath=uploadMap.get("docFilePath");//允许文件位置
			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
			if(StringUtils.isNotBlank(fileAllowSuffix)){
				//获取文件后缀名	
		    	int length = fileAllowSuffix.indexOf(suffix.toLowerCase());
		        if(length == -1){
		        	json.put("res", Const.FAIL);
					json.put("resMsg", "请上传允许格式的文件");
					out.print(json.toString());
					return;
		        }
			}	    	
			long size = file.getSize();
			if(StringUtils.isNotBlank(fileAllowSize)){
				 long allowsize=Long.parseLong(fileAllowSize);
			        if(size > allowsize){
			        	json.put("res", Const.FAIL);
						json.put("resMsg", "超过上传文件大小限制");
						out.print(json.toString());
						return;
			        }	
			}   
			
			
			
			
			
			
			//-----------------------------下面是放到服务器上面的方法-----------------------------
	        String realPath=request.getSession().getServletContext().getRealPath("/");
	        String path=realPath+fileFilePath;
	        String fileName =DateUtils.getDate("yyyyMMdd")+ UuidUtil.get32UUID()+"."+suffix;  
	        File baseFile = new File(path);
			File targetFile = new File(baseFile, fileName);
			if(!baseFile.exists())baseFile.mkdirs();  
	        //保存  
            file.transferTo(targetFile);  
            
            
            //------------------------------------下面则是存到数据库的相关代码、测试用,最终要换到这个方法--------------------------------------------
            FileInputStream inputStream=new FileInputStream(targetFile);
            
            byte[] b = FileCopyUtils.copyToByteArray(inputStream);
            
            BaseFile bf = new BaseFile();
            bf.setFiles(b);
            String name=file.getOriginalFilename().substring(0,file.getOriginalFilename().lastIndexOf("."))+"pdf";
            bf.setFilename(file.getOriginalFilename());
            bf.setFiletype(suffix);
            bf.setId(UUID.randomUUID().toString());
            service.insert(bf);
            //FileUtil.delete(targetFile);//---用完就删
            
            //-----------------------------------------------------------------------------------------------------
            
            inputStream.close();
        	json.put("res", Const.SUCCEED);
			json.put("saveUrl","/"+fileFilePath+fileName);
			json.put("resMsg", "上传成功");
			json.put("pdfname", name);
			json.put("fileId", bf.getId());
			json.put("size", size);
			out.print(json.toString());
			logger.info("上传文件结束，位置："+path);
        } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传文件出错",e);
		}		
    }  


	//-----------数据库的图片转换到本地---------------------
	@RequestMapping("download")  
	public void download(HttpServletResponse response, HttpServletRequest request,String id) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json=new JSONObject();
		PrintWriter out = response.getWriter();
		logger.info("下载文件开始");
		try {  
	        BaseFile file = new BaseFile();
	        file.setId(id);
	        file = service.findById(file);
	        String saveUrl = file.byteToFile(request);
			json.put("saveUrl",saveUrl);
			
			out.print(json.toString());
			
	    } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传文件出错",e);
		}		
	}  
	
	
	@RequestMapping("doc2Html")  
	public String doc2Html(ModelMap model,HttpServletResponse response, HttpServletRequest request,String id) throws Exception {
		
		JSONObject json=new JSONObject();
		
		logger.info("下载文件开始");
		try {  
	        BaseFile file = new BaseFile();
	        file.setId(id);
	        file = service.findById(file);
	        String saveUrl = file.byteToFile(request);
	        
	        String realPath=request.getSession().getServletContext().getRealPath("/");
	        String path = realPath;
	        String urlpath =request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"//";
	        
	        
	        File dir = new File(path);
        	if (!dir.exists()) {
        		dir.mkdirs();
        	}
        	InputStream input = new FileInputStream(path + saveUrl);
        	HWPFDocument wordDocument = new HWPFDocument(input);
        	WordToHtmlConverter wordToHtmlConverter;
        	wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory
        			.newInstance().newDocumentBuilder().newDocument());
        	wordToHtmlConverter.setPicturesManager(new PicturesManager() {
        		public String savePicture(byte[] content, PictureType pictureType,
        				String suggestedName, float widthInches, float heightInches) {
        			return urlpath + saveUrl + "_"
        					+ suggestedName;
        		}
        	});
        	wordToHtmlConverter.processDocument(wordDocument);
        	List pics = wordDocument.getPicturesTable().getAllPictures();
        	if (pics != null) {
        		for (int i = 0; i < pics.size(); i++) {
        			Picture pic = (Picture) pics.get(i);
        			try {
        				pic.writeImageContent(new FileOutputStream(path + saveUrl
        						+ "_" + pic.suggestFullFileName()));
        			} catch (FileNotFoundException e) {
        				e.printStackTrace();
        			}
        		}
        	}
        	Document htmlDocument = wordToHtmlConverter.getDocument();
        	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        	DOMSource domSource = new DOMSource(htmlDocument);
        	StreamResult streamResult = new StreamResult(outStream);

        	TransformerFactory tf = TransformerFactory.newInstance();
        	Transformer serializer = tf.newTransformer();
        	serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        	serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        	serializer.setOutputProperty(OutputKeys.METHOD, "html");
        	serializer.transform(domSource, streamResult);
        	outStream.close();

        	String content = new String(outStream.toByteArray());
	        
	        
	        
			model.put("content", content);
			
	    } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("doc转pdf出错",e);
		}	
		return "system/file/showDoc";
	}  
	
	
	
	 
		@RequestMapping("doc2Pdf")  
		public String doc2Pdf(ModelMap model,HttpServletResponse response, HttpServletRequest request,String id,String url) throws Exception {
			
			
		
			try {  
				String saveUrl = null;
				if(Tools.notEmpty(id)) {
					 BaseFile file = new BaseFile();
				        file.setId(id);
				        file = service.findById(file);
				        saveUrl = file.byteToFile(request);
				}else {
					saveUrl=url;
				}
		       
		        
		        String realPath=request.getSession().getServletContext().getRealPath("/");
		        String docPath = realPath+saveUrl;
		        
		        if(docPath.endsWith(".doc")) {
		        	String pdfUrl = docPath.replaceAll("\\.doc", ".pdf");
		        	 OfficeToPdf.convert2PDF(docPath, pdfUrl);
		        	 model.put("pdfUrl",saveUrl.replaceAll("\\.doc", ".pdf"));
		        }
		       
		        
		        
		        
		        
			}catch (Exception e) {
				logger.error(e.toString(),e);
				logger.error("doc转pdf出错",e);
			}
			return "system/file/showPdf";
		}
	
	
	
	@RequestMapping("downloadS")  
	public String downloadS(Model model,HttpServletResponse response, HttpServletRequest request,String id)  {
		
		try {  
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			PrintWriter out = response.getWriter();
			logger.info("下载文件开始");
	        BaseFile file = new BaseFile();
	        String saveUrl = "";
	        if(id!=null&&!"".equals(id)){
	        	file.setId(id);
	        	file = service.findById(file);
	        	saveUrl += file.byteToFile(request)+",";
	        }
	        model.addAttribute("saveUrl",saveUrl);
			return "system/file/showImg";
			
	    } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传文件出错",e);
			return "500";
		}
		
	}  
	
	
	@RequestMapping("idToImgs")  
	public void idToImgs(Model model,HttpServletResponse response, HttpServletRequest request,String id)  {
		
		try {  
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			PrintWriter out = response.getWriter();
			logger.info("下载文件开始");
	        BaseFile file = new BaseFile();
	        String saveUrl = "";
	        if(id!=null&&!"".equals(id)&&id.indexOf(";")>=0){
	        	String ids [] = id.split(";");
	        	for(int i = 0;i < ids.length;i++){
	        		if(ids[i]!=null&&!"".equals(ids[i])){
	        			file.setId(ids[i]);
	        	        file = service.findById(file);
	        	        saveUrl += file.byteToFile(request)+",";
	        		}
	        	}
	        	
	        }
	       out.print(saveUrl);
	    } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传文件出错",e);
		}
		
	}  
	
	@RequestMapping("readDef")  
	public String readDef(HttpServletResponse response, HttpServletRequest request,String id,ModelMap model,String url) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		logger.info("下载文件开始");
		try {  
			if(Tools.notEmpty(url)){
				model.put("saveUrl",url);
				return "system/file/showPdf";
			}
	        BaseFile file = new BaseFile();
	        if(Tools.notEmpty(id)){
	        	 file.setId(id);
			        file = service.findById(file);
			        String saveUrl = file.byteToFile(request);
					model.put("saveUrl",saveUrl);
					if("jpg".equals(file.getFiletype()) || "JPG".equals(file.getFiletype()) || "png".equals(file.getFiletype()) || "PNG".equals(file.getFiletype())){
						return "system/file/showImg";
					}
	        }
	    } catch (Exception e) {
			logger.error(e.toString(),e);
			logger.error("上传文件出错",e);
		}	
		return "system/file/showPdf";
	}  

	
}






