package cn.hse.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Base64 {

	
	/**
	 * 加密
	 * @param key
	 * @return
	 */
	 public static String encryptBASE64(String key) {
	        byte[] bt = key.getBytes();
	        return (new BASE64Encoder()).encodeBuffer(bt);
	    }
	 /**
	  * 解密
	  * @param key
	  * @return
	  */
	 public static String decryptBASE64(String key) {
	        byte[] bt;
	        try {
	            bt = (new BASE64Decoder()).decodeBuffer(key);
	            return new String(bt);//如果出现乱码可以改成： String(bt, "utf-8")或 gbk
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "";
	        }
	    }
	 
	 /*public static void main(String[] args) {
		String aString="123dadasfsaffasfasfafaf";
		System.out.println(encryptBASE64(aString));
		String bString=encryptBASE64(aString);
		System.out.println(decryptBASE64(bString));
	}*/
}