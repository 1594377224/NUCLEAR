package cn.hse.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("uploadPhotos")
public class SaveFile {
	private static final Logger logger=LogManager.getLogger(SaveFile.class);
	 @Autowired  
	 private Environment env;
	/*
	 * 把图片上传到ftp服务器(创建隐患单上传多张图片)
	 */
	 @RequestMapping(value="/uploadPictureFiles",method=RequestMethod.POST)
	 public String filesUpload(@RequestParam("files") MultipartFile[] files) throws IOException {  
		 List<Map<String,Object>> list = new ArrayList<Map<String, Object>>() ;
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 logger.info("创建隐患单上传多张图片"+files.toString());
		  //判断file数组不能为空并且长度大于0  
	       if(files!=null&&files.length>0){ 
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                Map<String,Object> returnParams = uploadPictureFile(file); 
	                logger.info("上传图片返回的值"+returnParams);
	                if(G4Utils.isEmpty(returnParams)){
	                	resultMap.put("resultCode", "-1");
	     				resultMap.put("resultMsg", "系统异常图片上传失败！");
	     				return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	                }
	                list.add(returnParams);
	                logger.info("上传图片-最终-返回的值"+list.toString());
	            } 
	        	resultMap.put("resultCode", "0");
 				resultMap.put("resultMsg", "图片上传成功！");
	        }  
	       return ResultUtil.result("0", resultMap, list);  
	    }
    /*
	 * 把图片上传到ftp服务器(整改完成附件上传多张图片)
	 */
	 @RequestMapping(value="/uploadRectifyFiles",method=RequestMethod.POST)
	 public String uploadRectifyFiles(@RequestParam("files") MultipartFile[] files) throws IOException {  
		 List<Map<String,Object>> list = new ArrayList<Map<String, Object>>() ;
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 logger.info("整改完成附件上传多张图片"+files.toString());
		  //判断file数组不能为空并且长度大于0  
	       if(files!=null&&files.length>0){  
	            //循环获取file数组中得文件  
	            for(int i = 0;i<files.length;i++){  
	                MultipartFile file = files[i];  
	                //保存文件  
	                Map<String,Object> returnParams = uploadRectifyFile(file); 
	                logger.info("上传图片返回的值"+returnParams);
	                if(G4Utils.isEmpty(returnParams)){
	                	resultMap.put("resultCode", "-1");
	     				resultMap.put("resultMsg", "系统异常图片上传失败！");
	     				return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	                }
	                list.add(returnParams);
	                logger.info("上传图片-最终-返回的值"+list.toString());
	            } 
	        	resultMap.put("resultCode", "0");
 				resultMap.put("resultMsg", "图片上传成功！");
	        }  
	       return ResultUtil.result("0", resultMap, list);  
	    }
		/*
		 * 把图片上传到ftp服务器(创建隐患单上传多张图片)
		 */
	 private Map uploadPictureFile(MultipartFile file) throws IOException {  
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		 logger.info("---调用创建隐患单上传单张图片---");
		//上传至服务器
		//sftp主机
		String host = env.getProperty("hseHost");
		//sftp端口
		int port = Integer.parseInt(env.getProperty("hsePort"));
		//sftp用户名
		String username = env.getProperty("hseUserName");
		//sftp密码
		String pwd = env.getProperty("hsePwd");
		//路径
		String filePath = env.getProperty("address");
		//新建检查单附件地址
		String hseRecordFile = env.getProperty("hseRecordFile");
		FTPClient ftp=FtpUtils.getFTPClient(host, username, pwd, port);
		String url = "RecordFile/";
		try {
			ftp.setControlEncoding("UTF-8"); // 中文支持
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			ftp.enterLocalPassiveMode();
			 ftp.changeWorkingDirectory("/ProdTest/Mobile/"+hseRecordFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String fileName = file.getOriginalFilename();
		 if(fileName!=null&&fileName!=""){   
			 String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	         fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
		 }
		//切换ftp目录
		// 新图片，写入磁盘
         File f = new File(filePath, fileName);
		 FileInputStream fis=null;
		 try{
				file.transferTo(f);
				fis=new FileInputStream(f);
	            ftp.storeFile(fileName, fis);
				resultMap.put("url",url);
				resultMap.put("img",fileName);
		}catch(IOException e){
			logger.info("上传图片失败！");
		}finally{
			fis.close();
		}
		 return resultMap;
	 }
	    /*
		 * 把图片上传到ftp服务器(整改完成附件上传多张图片)
		 */
		public Map uploadRectifyFile(MultipartFile file) throws IOException { 
			 Map<String, Object> resultMap = new HashMap<String, Object>();
			 logger.info("---调用整改完成附件上传单张图片---");
			//上传至服务器
			//sftp主机
			String host = env.getProperty("hseHost");
			//sftp端口
			int port = Integer.parseInt(env.getProperty("hsePort"));
			//sftp用户名
			String username = env.getProperty("hseUserName");
			//sftp密码
			String pwd = env.getProperty("hsePwd");
			//路径
			String filePath = env.getProperty("address");
			//整改完成附件地址
			String hseRectifyFile = env.getProperty("hseRectifyFile");
			FTPClient ftp=FtpUtils.getFTPClient(host, username, pwd, port);
			String url = "RectifyFile/";
			//切换ftp目录
			try {
				ftp.setControlEncoding("UTF-8"); // 中文支持
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
				ftp.enterLocalPassiveMode();
				ftp.changeWorkingDirectory("/ProdTest/Mobile/"+hseRectifyFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String fileName = file.getOriginalFilename();
			 if(fileName!=null&&fileName!=""){   
				 String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
		         fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
			 }
			 // 新图片，写入磁盘
	         File f = new File(filePath, fileName);
			 FileInputStream fis=null;
			 try{
					file.transferTo(f);
					fis=new FileInputStream(f);
		            ftp.storeFile(fileName, fis);
					resultMap.put("url",url);
					resultMap.put("img",fileName);
			}catch(IOException e){
				logger.info("上传图片失败！");
			}finally{
				fis.close();
			}
			 return resultMap;
		}
	 
}
