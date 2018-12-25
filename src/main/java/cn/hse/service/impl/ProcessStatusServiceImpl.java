package cn.hse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.mapper.ProcessStatusMapper;
import cn.hse.service.ProcessStatusService;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;
/**
 * 流程状态
 * @author 
 *
 */
@Service
public class ProcessStatusServiceImpl implements ProcessStatusService{
	private static final Logger logger=LogManager.getLogger(ProcessStatusServiceImpl.class);
	/*
	 * 流程状态查询
	 * 待办查询标识（0）、已办查询标识（1）、流转查询标识（2）、草稿查询标识（3）、待阅查询标识（4）、已阅查询标识（5）
	 */
	@Autowired
	private ProcessStatusMapper processStatusMapper;
	@Override
	public String findToDo(JSONObject inputJson) {
		logger.info("[流程状态-查询入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> map = inputJson.fromObject(inputJson);
		String id = inputJson.getString("id");
		String logo = inputJson.getString("logo");
		String start = inputJson.get("start").toString();
		String limit = inputJson.get("limit").toString();
		int logos = Integer.parseInt(logo);
		int starts = Integer.parseInt(start);
		int limits = Integer.parseInt(limit);
		map.put("start", starts);
		map.put("limit", limits);
		map.put("ownerUserId", id);
		map.put("logo", logo);
		List<Map<String,Object>> list = null ;
		int num = 0 ;
		if(0==logos){//待办查询标识（0）
			list = processStatusMapper.findToDo(map);
			num = processStatusMapper.findCountToDo(map);
		} else if (1==logos){//已办查询标识（1）
			list = processStatusMapper.findHaveToDo(map);
			num = processStatusMapper.findCountHaveToDo(map);
		} else if (2==logos){//流转查询标识（2）
			list = processStatusMapper.findCirculation(map);
			num = processStatusMapper.findCountCirculation(map);
		} else if (3==logos){//草稿查询标识（3）
			list = processStatusMapper.findDraft(map);
			num = processStatusMapper.findCountDraft(map);
		} else if (4==logos){//待阅查询标识（4）
			list = processStatusMapper.findWaitingRead(map);
			num = processStatusMapper.findCountWaitingRead(map);
		} else if (5==logos){//已阅查询标识（5）
			list = processStatusMapper.findHaveRead(map);
			num = processStatusMapper.findCountHaveRead(map);
		}
		resultMap.put("total", num);
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功！");
		return ResultUtil.result("0", resultMap, list);
	}
	
	/*
	 * 流程状态查询个数
	 */
	@Override
	public String findProcessStatusCount(JSONObject inputJson) {
		logger.info("[流程状态-查询个数入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> map = inputJson.fromObject(inputJson);
		String id = inputJson.getString("id");
		map.put("ownerUserId", id);
		//待办个数
		int toDoNumA = processStatusMapper.findCountToDo(map);
		int toDoNumB = processStatusMapper.findCountToDoAnd(map);
		//已办个数
		int haveToDoNumA = processStatusMapper.findCountHaveToDo(map);
		//已办个数
		int haveToDoNumB = processStatusMapper.findCountHaveToDoAnd(map);
		//流转个数
		int circulationNumA = processStatusMapper.findCountCirculation(map);
		int circulationNumB = processStatusMapper.findCountCirculationAnd(map);
		//草稿个数
		int draftNum = processStatusMapper.findCountDraft(map);
		//待阅个数
		int waitingReadNum = processStatusMapper.findCountWaitingRead(map);
		//已阅个数
		int haveRead = processStatusMapper.findCountHaveRead(map);
		
		int toDoNum = toDoNumA+toDoNumB;
		int haveToDoNum = haveToDoNumA+haveToDoNumB;
		int circulationNum = circulationNumA+circulationNumB;
		resultMap.put("toDoNum", toDoNum);
		resultMap.put("haveToDoNum", haveToDoNum);
		resultMap.put("circulationNum", circulationNum);
		resultMap.put("draftNum", draftNum);
		resultMap.put("waitingReadNum", waitingReadNum);
		resultMap.put("haveRead", haveRead);
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功！");
		return ResultUtil.result("0", resultMap, null);
	}

	/*
	 * 页面详情-流转信息查询
	 */
	@Override
	public String findTransferInformation(JSONObject inputJson) {
		logger.info("[流程状态-流转信息查询入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String,Object>> list = processStatusMapper.findTransferInformation(inputJson);
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功！");
		return ResultUtil.result("0", resultMap, list);
	}

	/*
	 *待阅点击后更新为已阅状态 
	 */
	public String findHaveRead(JSONObject inputJson) {
		logger.info("[待阅点击后更新为已阅状态 -更改入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int  num = processStatusMapper.updateHaveRead(inputJson);
		if(num > 0){
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "操作成功！");
		} else {
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
		}
		return ResultUtil.result("0", resultMap, null);
	}
}
