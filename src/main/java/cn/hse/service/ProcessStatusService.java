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
	/*
	 * 流程状态查询个数
	 */
	public String findProcessStatusCount(JSONObject inputJson);
	/*
	 * 流转信息查询
	 */
	public String findTransferInformation(JSONObject inputJson);
	public String findHaveRead(JSONObject inputJson);

}
