package cn.hse.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hse.beans.CheckList;
import cn.hse.beans.DangerList;
import cn.hse.beans.FlowActionTrace;
import cn.hse.service.CheckListService;
import cn.hse.service.DangerListServie;
import cn.hse.service.FlowActionTraceService;
import cn.hse.service.FlowInstanceService;
import cn.hse.util.Constant;
import cn.hse.util.DateUtil;
import cn.hse.util.NodeSyn;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONArray;
//github.com/1594377224/NUCLEAR.git
import net.sf.json.JSONObject;
/**
 * 整改单验证通过，不通过
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
		@Autowired
		private CheckListService checkListService;
		
		@Transactional
		@RequestMapping(value="/verification",method=RequestMethod.POST)
		public String verification(@RequestBody Map<String, Object> map) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			logger.info("整改验证入参==="+map);
			String str=dataProcess(map);
			logger.info("整改验证同步用友接口数据结果==="+str);
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
			Integer checkId=Integer.parseInt(map.get("checkId").toString());
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
				//更新检查表
				CheckList checkList=new CheckList();
				checkList.setId(checkId);
				checkList.setApprovedate(new Date());
				checkList.setApproveperson(userName);
				checkListService.updateCheck(checkList);
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
				
				//整改提交同步节点数据到用友
				Map<String, Object> nodeResult=new HashMap<String, Object>();
				nodeResult.put("projNo", map.get("projNo").toString());  //项目编号
				nodeResult.put("recordNo", map.get("recordNo").toString());  //检查编号
				nodeResult.put("lineNo", map.get("lineNo").toString());  //序号
				nodeResult.put("stepId", map.get("stepId").toString());   //节点id
				nodeResult.put("stepName", map.get("stepName").toString());  //节点名称
				nodeResult.put("stepCode", map.get("stepCode").toString());  //节点编码
				nodeResult.put("ownerUserId", userId);  //当前用户id
				nodeResult.put("ownerUserName", userName);  //当前用户名称
				nodeResult.put("ownerUserDesc", Constant.QUE_REN_REN);   //当前用户描述
				nodeResult.put("submitUserId", userId);  //提交人id
				nodeResult.put("submitUserName",userName);   //提交人名称
				nodeResult.put("submitUserDesc", "结束流程");  //提交人描述
				NodeSyn nodeSyn=new NodeSyn();
				String aString=nodeSyn.synNodeData(nodeResult);
				logger.info("[调用用友接口同步节点数据]="+aString);
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
			Integer checkId=Integer.parseInt(map.get("checkId").toString()); //检查单ID
			//封装隐患单对象
			String responsiblePerson=map.get("responsiblePerson").toString();  //整改单责任 人
			String responsiblePersonId=map.get("responsiblePersonId").toString();  //整改责任人的ID
//			String copyPerson=map.get("copyPerson").toString();   //抄送
			String hiddenDoc=map.get("hiddenDoc").toString();   //隐患附件
			String unit = map.get("unit").toString();  //适用机组
			String area = map.get("area").toString();  //区域
			String unitID = map.get("unitID").toString();  //被检查单位
			String hseHiddenLevel = map.get("hseHiddenLevel").toString();  //隐患级别
			String hiddenCategory = map.get("hiddenCategory").toString();  //隐患属性
			String nonconformity = map.get("nonconformity").toString();  // 隐患类型
			String hiddenDescription = map.get("hiddenDescription").toString();  //隐患描述
			//插入信息到抄送人delivery表
			Map<String, Object> deliveryMap = new HashMap<String, Object>();
			List<Map<String,Object>> deliveryList = JSONArray.fromObject(map.get("copyPerson")); //抄送
			deliveryMap.put("userId", map.get("userId").toString());
			deliveryMap.put("userName", map.get("userName").toString());
			deliveryMap.put("checkId", checkId);
			deliveryMap.put("dangerId", dangerId);
			deliveryMap.put("traceId", traceId);
			deliveryMap.put("statusId", "0");//0待阅，1已阅
			deliveryMap.put("deliveryList", deliveryList);
			int deliveryNum = flowInstanceService.addDelivery(deliveryMap);
			
			DangerList dangerList=new DangerList();
			dangerList.setId(dangerId);
			dangerList.setArea(area);
			dangerList.setUnit(unit);
			dangerList.setUnitid(unitID);
			dangerList.setResponsibleperson(responsiblePerson);
			dangerList.setHsehiddenlevel(hseHiddenLevel);  //隐患级别
			dangerList.setHiddencategory(hiddenCategory);  //隐患属性
			dangerList.setNonconformity(nonconformity);  // 隐患类型
			dangerList.setHiddendescription(hiddenDescription);  //隐患描述
			dangerList.setReqcompletedate(DateUtil.string2Date(map.get("reqCompleteDate").toString()));   //要求完成时间
			dangerList.setCopyPerson(deliveryList.toString()); //抄送
			dangerList.setHiddendoc(hiddenDoc);
			int b=dangerListServie.updateDanger(dangerList);
			//更新流转表
			FlowActionTrace flowActionTrace=new FlowActionTrace();
			flowActionTrace.setId(traceId);
			flowActionTrace.setSubmituserid(userId);
			flowActionTrace.setSubmitusername(userName);
			flowActionTrace.setSubmituserdesc("再次发起流程");
			int c=flowActionTraceService.updateRetResubmit(flowActionTrace,instanceId,responsiblePersonId,responsiblePerson);
			if (b!=0&&c!=0&&deliveryNum!=0) {
				resultMap.put("resultCode", "0");
				resultMap.put("resultMsg", "操作成功！");
				return ResultUtil.result("0", resultMap, null);
			}
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("-9999", resultMap, null);
		}
		
		
		/**
		 * 对整改验证数据进行处理	
		 * @param map
		 * @return
		 */
		public String dataProcess(Map<String, Object> map) {
			logger.info("[整改单验证接口入参]"+map);
			
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			Map<String, Object> result = new HashMap<String, Object>();
			List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> imgList=new ArrayList<Map<String, Object>>();
			//检查单信息封装
			paramsMap.put("proj_no", map.get("projNo"));   //项目编号
			paramsMap.put("record_no",map.get("record_no"));  //检查编号
			paramsMap.put("no", "1");  //隐患明细编号
			//paramsMap.put("check_date", map.get("checkDate").toString());  //检查日期
			//检查形式 ：日常检查（0）、专项检查（1）、综合检查（2）
			int check=Integer.valueOf(map.get("checkForm").toString());
			String checkForm="";
			if (check==0) {
				checkForm="DAILY";   //日常检查
			}else if (check==1) {
				checkForm="CONPLEX";  //专项检查
			}else {
				checkForm="SPECIALLY";  //专项检查
			}
			paramsMap.put("hse_check_form", checkForm);   //检查形式
			paramsMap.put("login_user_id", map.get("userId").toString());   //用户id
			paramsMap.put("step_id", "200");
		
			//隐患单信息封装
			resultMap.put("hse_pass_content","通过");  //是否验证通过
			resultMap.put("verify_content",map.get("comfirmContent").toString());  //验证情况
			resultMap.put("close_person",map.get("closePerson").toString());  //关闭人
			resultMap.put("close_date",map.get("closeDate").toString());  //关闭日期
			list.add(resultMap);
			//上传图片
			result.put("imgName", "");  //图片名称
			result.put("imgAddress", "");   //图片地址
			imgList.add(result);
			paramsMap.put("attachment", imgList);
			paramsMap.put("HseSiteCorrectionline", list);
			logger.info("[传用友处理新建数据封装完成参数]==="+paramsMap);
			
			JSONObject params = JSONObject.fromObject(paramsMap);
			
			WebServiceController webServiceController=new WebServiceController();
			
			//调用返回的结果
			String returnResult=webServiceController.modifyHseSiteCorrectionLine(params);
			JSONObject json=JSONObject.fromObject(returnResult);
			String str="";
			if (json.get("status").equals("0")) {
				logger.info("[同步用友整改接口返回结果]==="+json.get("status"));
				str="整改验证数据同步失败！";
				return str;
			}
			str="整改验证数据同步成功！";
			return str;
			
		}
}
