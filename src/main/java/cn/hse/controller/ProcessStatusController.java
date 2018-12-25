package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hse.service.ProcessStatusService;
import cn.hse.util.G4Utils;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;
/**
 * 流程状态
 * @author 
 *
 */
@RestController
@RequestMapping("processStatus")
public class ProcessStatusController {
	private static final Logger logger=LogManager.getLogger(ProcessStatusController.class);
	
	@Autowired
	private ProcessStatusService processStatusService;
	/*
	 * 待办查询标识（0）、已办查询标识（1）、流转查询标识（2）、草稿查询标识（3）、待阅查询标识（4）、已阅查询标识（5）
	 */
	@RequestMapping(value="/findToDo",method=RequestMethod.POST)
	public String findToDo(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[流程状态-查询入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//登录用户id
		String id = "" ;
		//用户查询状态编码
		String logo = "";
		if (inputJson.containsKey("id")) {
			id = inputJson.getString("id");
		}
		if (inputJson.containsKey("logo")) {
			logo = inputJson.getString("logo");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(id),G4Utils.isNotEmpty(logo)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = processStatusService.findToDo(inputJson);
			return resultStr;
		}
		
	}
	
	/*
	 * 流程状态查询个数
	 */
	@RequestMapping(value="/findProcessStatusCount",method=RequestMethod.POST)
	public String findProcessStatusCount(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[流程状态-查询入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//登录用户id
		String id = "" ;
		if (inputJson.containsKey("id")) {
			id = inputJson.getString("id");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(id)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = processStatusService.findProcessStatusCount(inputJson);
			return resultStr;
		}
	}
	
	/*
	 * 流转信息查询
	 */
	@RequestMapping(value="/findTransferInformation",method=RequestMethod.POST)
	public String findTransferInformation(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[流程状态-流转信息查询入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//检查单编码
		String recordNo = "" ;
		if (inputJson.containsKey("recordNo")) {
			recordNo = inputJson.getString("recordNo");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(recordNo)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = processStatusService.findTransferInformation(inputJson);
			return resultStr;
		}
	}
	
	/*
	 *待阅点击后更新为已阅状态 
	 */
	@RequestMapping(value="/updateHaveRead",method=RequestMethod.POST)
	public String findHaveRead(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[流程状态-流转信息查询入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//抄送人表的id-- deliveryId
		String deliveryId = "" ;
		if (inputJson.containsKey("deliveryId")) {
			deliveryId = inputJson.getString("deliveryId");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(deliveryId)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = processStatusService.findHaveRead(inputJson);
			return resultStr;
		}
	}
}
