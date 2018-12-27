package cn.hse.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import cn.hse.controller.CheckListController;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class UploadImg {
	private static final Logger logger=LogManager.getLogger(CheckListController.class);
	//新建检查单附件地址
	private static final String checkImgUrl="http://10.4.210.89//RecordFile";
	//整改完成附件地址
	private static final String correctImgUrl="http://10.4.210.89//RectifyFile";
	
	@Autowired 
    static  HttpServletRequest request;
	   // 图片转化成base64字符串---将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		public static String GetImageBase64Str() {// 
			String imgFile = "C:\\Users\\徐童\\Pictures\\Saved Pictures\\0.jpg";// 待处理的图片
			InputStream in = null;
			byte[] data = null;
			// 读取图片字节数组
			try {
				in = new FileInputStream(imgFile);
				data = new byte[in.available()];
				in.read(data);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 对字节数组Base64编码
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(data);// 返回Base64编码过的字节数组字符串
		}
		
		
		// base64字符串转化成图片---对字节数组字符串进行Base64解码并生成图片
		public static boolean CreateImage(String imgStr) { 
			System.out.println("================================");
			System.out.println("======"+imgStr);
			if (imgStr == null) // 图像数据为空
				return false;
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				// Base64解码
				byte[] b = decoder.decodeBuffer(imgStr);
				for (int i = 0; i < b.length; ++i) {
					if (b[i] < 0) {// 调整异常数据
						b[i] += 256;
					}
				}
				// D:\\百度网盘\\1.jpg 生成jpg图片http://39.105.204.84//RecordFile/1.jpg
				String imgFilePath = "http://39.105.204.84//imgUpload";// 新生成的图片
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
				return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}
		
		public void uploadImage(File upload, String uploadFileName, String path) throws Exception {
	        InputStream is = null;
	        OutputStream os = null;
	        try {
	            is = new FileInputStream(upload);
	            File f = new File(path);
	            if (!f.exists()) f.mkdirs();
	            os = new FileOutputStream(path + "/" + uploadFileName);
	            byte buffer[] = new byte[1024];
	            int count = 0;
	            int flag = 0;
	            while ((count = is.read(buffer)) > 0) {
	                os.write(buffer, 0, count);
	            }
	        } catch (FileNotFoundException e) {
	        } catch (IOException e) {
	            File f = new File(path + "/" + uploadFileName);
	            if (f.exists()) {
	                f.delete();
	            }
	        } finally {
	            try {
	                os.close();
	                is.close();
	            } catch (IOException ioe) {
	            }
	        }
	    } 

		/*public static void main(String[] args) throws Exception {
			String str=GetImageBase64Str();
			Boolean flag=CreateImage(str);
			System.out.println(flag);						
		}*/
			/*//System.out.println((new Date()).getTime());
			BufferedOutputStream bos = null;  
	        FileOutputStream fos = null;
	        File file = null; 
			String imgFile = "D:\\1.jpg";// 待处理的图片
			InputStream in = null;
			byte[] data = null;
			// 读取图片字节数组
			String path="http://39.105.204.84//RecordFile/1.jpg";
			System.out.println(path);
			//String location=path.replace("\\\\", "\\");
			System.out.println(location);
			try {
				in = new FileInputStream(imgFile);
				data = new byte[in.available()];
				file = new File(path);  
				//file = new File("http:\\10.4.210.89\\RecordFile\\1.jpg");  
		        fos = new FileOutputStream(file);  
		        bos = new BufferedOutputStream(fos);  
		        bos.write(data);  

				in.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
*/
}
