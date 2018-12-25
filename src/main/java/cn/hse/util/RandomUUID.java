package cn.hse.util;

import java.util.UUID;

/**
 * 
 * @author 徐童
 *
 */
public class RandomUUID {
	 /**
	 * 生成32位UUID
	 * @return
	 */
	 public static String RandomID() {
	        return UUID.randomUUID().toString().replace("-", "");
	    }
}
