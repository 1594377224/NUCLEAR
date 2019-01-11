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

import cn.hse.service.DelayToApplyForService;
import cn.hse.util.G4Utils;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;

/**
 * 延期申请
 * @author 
 *
 */
@RestController
@RequestMapping("delayToApplyFor")
public class DelayToApplyForController {
	private static final Logger logger=LogManager.getLogger(DelayToApplyForController.class);
	@Autowired
	private DelayToApplyForService delayToApplyForService;
	
	@RequestMapping(value="/findDelayToApplyFor",method=RequestMethod.POST)
	public String delayToApplyFor(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[延期申请-查询入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//用户登录id
		String userId = "" ;
		//用户登录名称
		String userName = "" ;
		//ActionTrace表的id
		String actionTraceId = "" ;
		//实例id
		String instanceId = "" ;
		//隐患单id
		String dangerId = "" ;
		//检查单id
		String checkId = "" ;
		//要求完成的时间
		String reqCompleteDate = "" ;
		//延期原因
		String delayToApplyForDec = "" ; 
		//延期时间
		String delayToApplyForDate = "" ; 
		//延期申请次数
		String delayNum = "" ; 
		//检查单编号
		String recordNo = "";
		if (inputJson.containsKey("userId")) {
			userId = inputJson.getString("userId");
		}
		if (inputJson.containsKey("userName")) {
			userName = inputJson.getString("userName");
		}
		if (inputJson.containsKey("actionTraceId")) {
			actionTraceId = inputJson.getString("actionTraceId");
		}
		if (inputJson.containsKey("instanceId")) {
			instanceId = inputJson.getString("instanceId");
		}
		if (inputJson.containsKey("dangerId")) {
			dangerId = inputJson.getString("dangerId");
		}
		if (inputJson.containsKey("checkId")) {
			checkId = inputJson.getString("checkId");
		}
		if (inputJson.containsKey("reqCompleteDate")) {
			reqCompleteDate = inputJson.getString("reqCompleteDate");
		}
		if (inputJson.containsKey("delayToApplyForDec")) {
			delayToApplyForDec = inputJson.getString("delayToApplyForDec");
		}
		if (inputJson.containsKey("delayToApplyForDate")) {
			delayToApplyForDate = inputJson.getString("delayToApplyForDate");
		}
		if (inputJson.containsKey("delayNum")) {
			delayNum = inputJson.getString("delayNum");
		}
		if (inputJson.containsKey("recordNo")) {
			recordNo = inputJson.getString("recordNo");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(userId),G4Utils.isNotEmpty(userName),G4Utils.isNotEmpty(actionTraceId),
				G4Utils.isNotEmpty(instanceId),G4Utils.isNotEmpty(dangerId),G4Utils.isNotEmpty(checkId),
				G4Utils.isNotEmpty(reqCompleteDate),G4Utils.isNotEmpty(delayToApplyForDec),
				G4Utils.isNotEmpty(delayToApplyForDate),G4Utils.isNotEmpty(delayNum),G4Utils.isNotEmpty(recordNo)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = delayToApplyForService.findDelayToApplyFor(inputJson);
			return resultStr;
		}
	}
	
	/**
	 * 查询延期申请次数
	 * @author 
	 *
	 */
	@RequestMapping(value="/findDelayNum",method=RequestMethod.POST)
	public String findDelayNum(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[查询延期申请次数-查询入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//隐患单id
		String dangerId = "" ;
		//检查单id
		String checkId = "" ;
		if (inputJson.containsKey("dangerId")) {
			dangerId = inputJson.getString("dangerId");
		}
		if (inputJson.containsKey("checkId")) {
			checkId = inputJson.getString("checkId");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(dangerId),G4Utils.isNotEmpty(checkId)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = delayToApplyForService.findDelayNum(inputJson);
			return resultStr;
		}
		
	}
}
