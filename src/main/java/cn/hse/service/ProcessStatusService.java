package cn.hse.service;

import net.sf.json.JSONObject;
/**
 * 流程状态
 * @author 
 *
 */
public interface ProcessStatusService {
	/*
	 * 流程状态查询
	 */
	public String findToDo(JSONObject inputJson);

}
