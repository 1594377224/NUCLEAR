package cn.hse.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定义封装参数
 * @author 徐童
 *
 */
public class Result {
	
	private String rtnCode;// 响应结果编码
	private String rtnMsg;// 响应结果信息 
	private Map<String, Object> bean = new HashMap<String, Object>(); //返回单条信息，单层Map，不建议放入复杂对象 
	private List<Map<String, Object>> beans = new ArrayList<Map<String, Object>>();// 返回多条信息，单层列表，不建议放入复杂对象
	private Object object = new Object();//返回复杂对象 
	
	
	public interface RTN_CODE { 
		String SUCCESS = "0";//成功返回“0”
		String ERROR = "-9999";//失败返回“-9999” 
	} 
	public Result() { 
		this.setRtnCode(RTN_CODE.SUCCESS); 
		this.setRtnMsg(RTN_MSG.SUCCESS); 
		}
	public interface RTN_MSG { 
		String SUCCESS = "成功"; 
		String ERROR = "失败"; 
		}
	
	public  Result(String rtnCode) { 
		super(); 
		this.rtnCode = rtnCode; 
		} 
	

	public Result(String rtnCode, String rtnMsg) {
		super();
		this.rtnCode = rtnCode;
		this.rtnMsg = rtnMsg;
	}
	
	
	
	
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public String getRtnMsg() {
		return rtnMsg;
	}
	public void setRtnMsg(String rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	public Map<String, Object> getBean() {
		return bean;
	}
	public void setBean(Map<String, Object> bean) {
		this.bean = bean;
	}
	public List<Map<String, Object>> getBeans() {
		return beans;
	}
	public void setBeans(List<Map<String, Object>> beans) {
		this.beans = beans;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}


	@Override
	public String toString() {
		return "Result [rtnCode=" + rtnCode + ", rtnMsg=" + rtnMsg + ", bean=" + bean + ", beans=" + beans + ", object="
				+ object + "]";
	} 
	
	
}
