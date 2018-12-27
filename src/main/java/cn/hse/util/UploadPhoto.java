package cn.hse.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 * 图片上传工具类
 * @author
 *
 */
@RestController
@RequestMapping("uploadPhoto")
public class UploadPhoto {
	@RequestMapping(value="/uploadPicture",method=RequestMethod.POST)
	public String uploadPicture(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resultMap = new HashMap<String, Object>();
        File targetFile=null;
//        String url="http://10.4.210.89/RecordFile";
//        String url="http://39.105.204.84:3306/imgUpload";
        String returnUrl = "";//返回存储路径
        System.out.println(file.toString());
        String fileName=file.getOriginalFilename();//获取文件名加后缀
        if(fileName!=null&&fileName!=""){   
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/src/main/";//存储路径
            String path = request.getSession().getServletContext().getRealPath("/src/main");  //文件存储位置
            System.out.println(path);
            String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
            fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
 
            //先判断文件是否存在
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            String fileAdd = sdf.format(new Date());
            //获取文件夹路径
            File file1 =new File(path+"/"); 
            //如果文件夹不存在则创建    
//            if(!file1 .exists()  && !file1 .isDirectory()){       
//                file1 .mkdir();  
//            }
            //将图片存入文件夹
            targetFile = new File(file1, fileName);
            try {
            	//将上传的文件写到服务器上指定的文件。
                file.transferTo(targetFile);
                returnUrl=url+"/"+fileName;
                resultMap.put("resultCode", "0");
                resultMap.put("Message", "图片上传成功");
                resultMap.put("url", returnUrl);
                resultMap.put("img", fileName);
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "系统异常图片上传失败");
            }
        }
    	return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	}
	
}
