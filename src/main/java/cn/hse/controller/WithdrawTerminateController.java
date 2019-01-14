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

import cn.hse.service.WithdrawTerminateService;
import cn.hse.util.G4Utils;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;

/**
 * 撤办终止
 * @author 
 *
 */
@RestController
@RequestMapping("withdrawTerminate")
public class WithdrawTerminateController {
	private static final Logger logger=LogManager.getLogger(WithdrawTerminateController.class);
	
	@Autowired
	private WithdrawTerminateService withdrawTerminateService;
	@RequestMapping(value="/findWithdrawTerminate",method=RequestMethod.POST)
	public String findWithdrawTerminate(@RequestBody Map<String, Object> map){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[撤办流程-入参]"+inputJson);
		// 参数校验
		if(inputJson.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("0", resultMap, null);
		}
		//登录用户id
		String userId = "" ;
		//检查单id
		String checkId = "";
		//实例id
		String instanceId = "";
		
		if (inputJson.containsKey("userId")) {
			userId = inputJson.getString("userId");
		}
		if (inputJson.containsKey("checkId")) {
			checkId = inputJson.getString("checkId");
		}
		if (inputJson.containsKey("instanceId")) {
			instanceId = inputJson.getString("instanceId");
		}
		boolean[] sArr = { G4Utils.isNotEmpty(userId),G4Utils.isNotEmpty(checkId),G4Utils.isNotEmpty(instanceId)};
		boolean flag = BooleanUtils.and(sArr);
		if (!flag) {
			return ResultUtil.result("-9999", "接入参数不完整！");
		} else {
			String resultStr = withdrawTerminateService.findWithdrawTerminate(inputJson);
			return resultStr;
		}
		
	}
	
	
}
