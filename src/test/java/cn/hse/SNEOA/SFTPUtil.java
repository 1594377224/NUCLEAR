package cn.hse.SNEOA;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SFTPUtil {
	    private ChannelSftp sftp;  
     
	    private Session session;  
	    /** SFTP 登录用户名*/    
	    private String username; 
	    /** SFTP 登录密码*/    
	    private String password;  
	    /** 私钥 */    
	    private String privateKey;  
	    /** SFTP 服务器地址IP地址*/    
	    private String host;  
	    /** SFTP 端口*/  
	    private int port;  
	
	    /**  
	     * 构造基于密码认证的sftp对象  
	     */    
	    public SFTPUtil(String username, String password, String host, int port) {  
	        this.username = username;  
	        this.password = password;  
	        this.host = host;  
	        this.port = port;  
	    } 
	    
	    /**  
	     * 构造基于秘钥认证的sftp对象 
	     */  
	    public SFTPUtil(String username, String host, int port, String privateKey) {  
	        this.username = username;  
	        this.host = host;  
	        this.port = port;  
	        this.privateKey = privateKey;  
	    }  
	    
	    public SFTPUtil(){} 
	    
	    

		/** 
	     * 连接sftp服务器 
	     */  
	    public void login(){  
	        try {  
	            JSch jsch = new JSch();  
	            if (privateKey != null) {  
	                jsch.addIdentity(privateKey);// 设置私钥  
	            }  
	    
	            session = jsch.getSession(username, host, port);  
	           
	            if (password != null) {  
	                session.setPassword(password);    
	            }  
	            Properties config = new Properties();  
	            config.put("StrictHostKeyChecking", "no");  
	                
	            session.setConfig(config);  
	            session.connect();  
	              
	            Channel channel = session.openChannel("sftp");  
	            channel.connect();  
	    
	            sftp = (ChannelSftp) channel;  
	        } catch (JSchException e) {  
	            e.printStackTrace();
	        }  
	    } 
	    /** 
	     * 关闭连接 server  
	     */  
	    public void logout(){  
	        if (sftp != null) {  
	            if (sftp.isConnected()) {  
	                sftp.disconnect(); 
	                System.out.println("关闭服务器连接");
	            }  
	        }  
	        if (session != null) {  
	            if (session.isConnected()) {  
	                session.disconnect();  
	                System.out.println("关闭session连接");
	            }  
	        }  
	      
	    }  
	    /**  
	     * 将输入流的数据上传到sftp作为文件。文件完整路径=basePath+directory
	     * @param basePath  服务器的基础路径 
	     * @param directory  上传到该目录  
	     * @param sftpFileName  sftp端文件名  
	     * @param in   输入流  
	     */  
	    public void upload(String basePath,String directory, String sftpFileName, InputStream input) throws SftpException{  
	        try {   
	            sftp.cd(basePath);
	            sftp.cd(directory);  
	        } catch (SftpException e) { 
	            //目录不存在，则创建文件夹
	            String [] dirs=directory.split("/");
	            String tempPath=basePath;
	            for(String dir:dirs){
	            	if(null== dir || "".equals(dir)) continue;
	            	tempPath+="/"+dir;
	            	try{ 
	            		sftp.cd(tempPath);
	            	}catch(SftpException ex){
	            		sftp.mkdir(tempPath);
	            		sftp.cd(tempPath);
	            	}
	            }
	        }  
	        sftp.put(input, sftpFileName);  //上传文件
	    } 
	public static void main(String[] args) throws Exception {
		 SFTPUtil sftp = new SFTPUtil("root", "xuTONG@2018", "39.105.204.84", 22);  
	     sftp.login();
	     
	     File file = new File("d:\\1.jpg");  
	     InputStream is = new FileInputStream(file);   
	     sftp.upload("/","imgUpload", "test_sftp.jpg", is);  

	     sftp.logout();
	}
	
	
	
}
