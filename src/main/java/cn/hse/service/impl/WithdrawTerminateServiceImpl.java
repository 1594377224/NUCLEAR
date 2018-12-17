package cn.hse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hse.mapper.WithdrawTerminateMapper;
import cn.hse.service.WithdrawTerminateService;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONObject;
/**
 * 撤办终止
 * @author 
 *
 */
@Service
public class WithdrawTerminateServiceImpl implements WithdrawTerminateService{
	private static final Logger logger=LogManager.getLogger(WithdrawTerminateServiceImpl.class);
	@Autowired
	WithdrawTerminateMapper withdrawTerminateMapper;
	@Override
	public String findWithdrawTerminate(JSONObject inputJson) {
		logger.info("[撤办终止--入参]"+inputJson);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String,Object> map = inputJson.fromObject(inputJson);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String userId = inputJson.getString("userId");
		String checkId = inputJson.getString("checkId");
		map.put("userId", userId);
		map.put("checkId", checkId);
		//查询用户创建的发起申请整改流程别人还未办理
		List<Map<String,Object>> withdrawTerminateList = withdrawTerminateMapper.findWithdrawTerminate(map);
		if(withdrawTerminateList.isEmpty()){
			resultMap.put("total", "0");
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "流程已经办理不能进行撤回！");
		} else {
			for (Map<String, Object> withdrawTerminateMap : withdrawTerminateList) {
				String submitUserId = withdrawTerminateMap.get(userId).toString();
				String submitUserName = withdrawTerminateMap.get("checkPerson").toString();
				String id = withdrawTerminateMap.get("id").toString();
				paramMap.put("id", id);
				paramMap.put("submitUserId", submitUserId);
				paramMap.put("submitUserName", submitUserName);
				paramMap.put("submitUserDesc", "撤办终止流程");
			}
			
			//查询模板信息--撤办终止流程
			Map<String,Object> flowMap = withdrawTerminateMapper.findFlowMap();
			if(flowMap.isEmpty()){
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
			} else {
				String actionId = flowMap.get("actionId").toString();
				String actionCode = flowMap.get("actionCode").toString();
				String actionName = flowMap.get("actionName").toString();
				paramMap.put("actionId", actionId);
				paramMap.put("actionCode", actionCode);
				paramMap.put("actionName", actionName);
				//更新flowActionTrace表
				int num = withdrawTerminateMapper.updateFlowActionTrace(paramMap);
				if (num < 0) {
					resultMap.put("resultCode", "-1");
					resultMap.put("resultMsg", "操作失败！");
				} else {
					resultMap.put("resultCode", "0");
					resultMap.put("resultMsg", "操作成功！");
				}
			}
		}
		return ResultUtil.result("0", resultMap, new ArrayList<Map<String, Object>>());
	}

}
