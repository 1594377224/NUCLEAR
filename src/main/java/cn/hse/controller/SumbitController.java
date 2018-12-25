package cn.hse.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hse.beans.DangerList;
import cn.hse.beans.FlowActionTrace;
import cn.hse.service.DangerListServie;
import cn.hse.service.FlowActionTraceService;
import cn.hse.service.FlowInstanceService;
import cn.hse.util.Constant;
import cn.hse.util.DateUtil;
import cn.hse.util.ResultUtil;
/**
 * 整改验证通过，不通过
 * @author T440P
 *
 */
@RequestMapping(value="/sumbit")
@RestController
public class SumbitController {
		private static final Logger logger=LogManager.getLogger(SumbitController.class);
		@Autowired
		private DangerListServie dangerListServie;
		@Autowired
		private FlowActionTraceService  flowActionTraceService;
		@Autowired
		private FlowInstanceService flowInstanceService;
		
		@Transactional
		@RequestMapping(value="/verification",method=RequestMethod.POST)
		public String verification(@RequestBody Map<String, Object> map) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			logger.info("整改验证入参==="+map);
			//首先判断整改验证是否通过  0通过1不通过
			String hsePassContent=map.get("isPass").toString(); //是否通过
			String comfirmContent=map.get("comfirmContent").toString(); //确认情况
			String contractonPeople=map.get("contractonPeople").toString();  //整改单编制人
			String closePerson=map.get("closePerson").toString();  //关闭人
			String closeDate=map.get("closeDate").toString();  //关闭日期
			String corApprovePerson=map.get("corApprovePerson").toString();  //批准人
			String corApproveDate=map.get("corApproveDate").toString();  //批准日期
			//流转表id + 隐患id+  实例id
			Integer traceId=Integer.parseInt(map.get("traceId").toString());
			Integer dangerId=Integer.parseInt(map.get("dangerId").toString());
			Integer instanceId=Integer.parseInt(map.get("instanceId").toString());
			String userId=map.get("userId").toString();
			String userName=map.get("userName").toString();
			logger.info("===traceId="+traceId);
			logger.info("===dangerId="+dangerId);
			logger.info("===instanceId="+instanceId);
			//封装隐患信息
			DangerList dangerList=new DangerList();
			dangerList.setId(dangerId);
			dangerList.setHsepasscontent(hsePassContent);
			dangerList.setComfirmcontent(comfirmContent);
			dangerList.setContractonpeople(contractonPeople);
			dangerList.setCloseperson(closePerson);
			dangerList.setClosedate(DateUtil.string2Date(closeDate));
			dangerList.setCorapproveperson(corApprovePerson);
			dangerList.setCorapprovedate(DateUtil.string2Date(corApproveDate));
			if ("0".equals(hsePassContent)) { //整改验证通过了
				 //更新隐患表信息
				int a=dangerListServie.updateDanger(dangerList);
				//更新流转表信息 1、先更新2、在插入闭合数据
				FlowActionTrace flowActionTrace=new FlowActionTrace();
				flowActionTrace.setId(traceId);
				flowActionTrace.setSubmituserid(userId);
				flowActionTrace.setSubmitusername(userName);
				flowActionTrace.setSubmituserdesc(Constant.QUE_REN_REN);
				flowActionTrace.setArrivetime(new Date());
				int b=flowActionTraceService.updateChange(flowActionTrace,instanceId);
				//更新实例表
				int c=flowInstanceService.updateInstanceEnd(instanceId);
				if (b!=0 && c!=0) {
					resultMap.put("resultCode", "0");
					resultMap.put("resultMsg", "操作成功！");
					return ResultUtil.result("0", resultMap, null);
				}
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
				return ResultUtil.result("-9999", resultMap, null);
				
			}else { //不通过
				//更新流转表
				FlowActionTrace flowActionTrace=new FlowActionTrace();
				flowActionTrace.setId(traceId);
				flowActionTrace.setSubmituserid(userId);
				flowActionTrace.setSubmitusername(userName);
				flowActionTrace.setSubmituserdesc(Constant.ZHENG_GAI_REN);
				int b=flowActionTraceService.updateChangeLast(flowActionTrace,instanceId);
				if (b!=0) {
					resultMap.put("resultCode", "0");
					resultMap.put("resultMsg", "操作成功！");
					return ResultUtil.result("0", resultMap, null);
				}
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
				return ResultUtil.result("-9999", resultMap, null);
			}
		}
		
		
	    //退回再提交
		/**
		 * 退回到发起人再次提交流程操作
		 * @param map
		 * @return
		 */
		@RequestMapping(value="/retResubmit",method=RequestMethod.POST)
		public String retResubmit(@RequestBody Map<String, Object> map) {
			logger.info("[退回再提交]入参="+map);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Integer traceId=Integer.parseInt(map.get("traceId").toString());   //流转表的ID
			Integer instanceId=Integer.parseInt(map.get("instanceId").toString());  //流转表中的实例ID
			String userId=map.get("userId").toString();   //用户ID
			String userName=map.get("userName").toString();   //用户ID
			Integer dangerId=Integer.parseInt(map.get("dangerId").toString()); //隐患ID
			//封装隐患单对象
			String responsiblePerson=map.get("responsiblePerson").toString();  //整改单责任 人
			String responsiblePersonId=map.get("responsiblePersonId").toString();  //整改责任人的ID
			String rectificationSituation=map.get("rectificationSituation").toString();  //整改情况
			String completeDate=map.get("completeDate").toString();  //整改完成日期
			String copyPerson=map.get("copyPerson").toString();   //抄送
			String hiddenDoc=map.get("hiddenDoc").toString();   //隐患附件
			DangerList dangerList=new DangerList();
			dangerList.setId(dangerId);
			dangerList.setResponsibleperson(responsiblePerson);
			dangerList.setCompletedate(DateUtil.string2Date(completeDate));
			dangerList.setCopyPerson(copyPerson);
			dangerList.setRectificationsituation(rectificationSituation);
			dangerList.setHiddendoc(hiddenDoc);
			int b=dangerListServie.updateDanger(dangerList);
			//更新流转表
			FlowActionTrace flowActionTrace=new FlowActionTrace();
			flowActionTrace.setId(traceId);
			flowActionTrace.setSubmituserid(userId);
			flowActionTrace.setSubmitusername(userName);
			flowActionTrace.setSubmituserdesc("再次发起流程");
			int c=flowActionTraceService.updateRetResubmit(flowActionTrace,instanceId,responsiblePersonId,responsiblePerson);
			if (b!=0&&c!=0) {
				resultMap.put("resultCode", "0");
				resultMap.put("resultMsg", "操作成功！");
				return ResultUtil.result("0", resultMap, null);
			}
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("-9999", resultMap, null);
		}
		
}
