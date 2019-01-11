package cn.hse.util;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FtpUtils {
	
	private static final Logger logger=LogManager.getLogger(UploadPhoto.class);
	
	
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
            String ftpPassword, int ftpPort) {
         FTPClient ftpClient = new FTPClient();
         try {
           ftpClient = new FTPClient();
           ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
           ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
         if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
        	 	logger.info("未连接到FTP，用户名或密码错误。");
        	 	ftpClient.disconnect();
         } else {
        	 logger.info("FTP连接成功。");
         }
         } catch (SocketException e) {
        	 e.printStackTrace();
        	 logger.info("异常信息="+e.getMessage());
         } catch (IOException e) {
        	 e.printStackTrace();
        	 logger.info("异常信息="+e.getMessage());
         }
         return ftpClient;
    }
}
