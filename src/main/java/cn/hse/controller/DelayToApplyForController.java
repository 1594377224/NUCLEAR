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
			String resultStr = delayToApplyForService.findDelayToApplyFor(inputJson);
			return resultStr;
		}
	}
	
	
	
}
