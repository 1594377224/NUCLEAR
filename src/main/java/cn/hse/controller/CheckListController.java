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

import cn.hse.beans.CheckAndDanger;
import cn.hse.beans.CheckList;
import cn.hse.beans.DangerList;
import cn.hse.beans.Flow;
import cn.hse.beans.FlowAction;
import cn.hse.beans.FlowActionTrace;
import cn.hse.beans.FlowInstance;
import cn.hse.beans.FlowStep;
import cn.hse.beans.InstanceRelation;
import cn.hse.service.CheckAndDangerService;
import cn.hse.service.CheckListService;
import cn.hse.service.DangerListServie;
import cn.hse.service.FlowActionService;
import cn.hse.service.FlowActionTraceService;
import cn.hse.service.FlowInstanceService;
import cn.hse.service.FlowService;
import cn.hse.service.FlowStepService;
import cn.hse.service.InstanceRelationService;
import cn.hse.util.Constant;
import cn.hse.util.DateUtil;
import cn.hse.util.Result;
import cn.hse.util.ResultUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@RequestMapping("/checkList")
@RestController
public class CheckListController {
	private static final Logger logger=LogManager.getLogger(CheckListController.class);
	@Autowired
	private CheckListService checkListService;
	@Autowired
	private DangerListServie dangerListServie;
	@Autowired
	private CheckAndDangerService checkAndDangerService;
	@Autowired
	private FlowService flowService;
	@Autowired
	private FlowInstanceService flowInstanceService;
	@Autowired
	private FlowActionService flowActionService;
	@Autowired
	private FlowStepService flowStepService;
	@Autowired
	private FlowActionTraceService  flowActionTraceService;
	@Autowired
	private InstanceRelationService instanceRelationService;
	/**
	 * 新建检查单
	 * @return
	 */
	@Transactional
	@RequestMapping(value="/insertCheck",method=RequestMethod.POST)
	public Result insertCheck(@RequestBody Map<String, Object> map){
		logger.info("=======进入新建检查单========接收参数="+map);
		//将检查隐患单数据传入用友数据库

		String array[]=dataProcess(map);
		
		Result result=new Result();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//封装检查单对象
		CheckList checkList=new CheckList();
		//String checkId=RandomUUID.RandomID();
		//checkList.setId(checkId);
		checkList.setUserId(map.get("userId").toString());
		checkList.setProjno(map.get("projNo").toString());   //项目编号
		checkList.setState(Integer.valueOf(map.get("state").toString()));  //状态

		checkList.setRecordno(array[0]);  //检查编号
		
		checkList.setCheckdate(DateUtil.string2Date(map.get("checkDate").toString()));//检查日期
		checkList.setCheckform(Integer.valueOf(map.get("checkForm").toString())); //检查形式
		checkList.setRecordtype(Integer.valueOf(map.get("recordType").toString()));  //检查单类型
	    checkList.setCheckcontent("");   //检查名称
		checkList.setCheckperson(map.get("checkPerson").toString());  //检查人
		checkList.setDraftunit(map.get("draftUnit").toString());   //编制单位
		checkList.setDraftdept(map.get("draftDept").toString());  //编制部门
		checkList.setDraftperson(map.get("draftPerson").toString());   //编制人
		checkList.setDraftdate(DateUtil.string2Date(map.get("draftDate").toString()));  //编制日期
		checkList.setApproveperson("");  //批准人
		//checkList.setApprovedate(DateUtil.string2Date(map.get("approveDate").toString()));  //批准日期
		checkList.setIsdel(0);
		int a=checkListService.insertCheck(checkList);
		int checkId=checkList.getId();
		//int checkId=1;
		logger.info("====检查单插入完毕"+checkId);
		//封装隐患单对象
		DangerList dangerList=new DangerList();
		dangerList.setLineno(array[1]);   //序号
		dangerList.setNoticeno(array[0]);//整改单编号
		dangerList.setDistributdate(new Date());  //分发日期
		dangerList.setUnit(map.get("unit").toString());  //适用机组
		dangerList.setArea(map.get("area").toString());  //区域
		dangerList.setUnitid(map.get("unitID").toString());  //被检查单位
		dangerList.setHsehiddenlevel(map.get("hseHiddenLevel").toString());  //隐患级别
		dangerList.setHiddencategory(map.get("hiddenCategory").toString());  //隐患属性
		dangerList.setNonconformity(map.get("nonconformity").toString());  // 隐患类型
		dangerList.setHiddendescription(map.get("hiddenDescription").toString());  //隐患描述
		dangerList.setHiddendoc(map.get("hiddenDoc").toString());   //隐患附件
		dangerList.setReqcompletedate(DateUtil.string2Date(map.get("reqCompleteDate").toString()));   //要求完成时间
		dangerList.setCorrectiverequest(map.get("correctiveRequest").toString());  //整改措施要求
		//dangerList.setArea(map.get("rectificationSituation").toString()); //整改情况描述
		dangerList.setResponsibledate(new Date());  //接收日期
		dangerList.setContractonpeople(map.get("contractonPeople").toString());  //整改单编制人
		String responsiblePerson=map.get("responsiblePerson").toString();
		dangerList.setResponsibleperson(responsiblePerson);  //整改责任人
		List<Map<String,Object>> deliveryList = JSONArray.fromObject(map.get("copyPerson"));
		dangerList.setCopyPerson(deliveryList.toString());   //抄送人
		dangerList.setIsdel(0);
		int b=dangerListServie.insertDanger(dangerList);
		int dangerId=dangerList.getId();
		logger.info("====隐患单插入完毕"+dangerId);
		//生成检查单个隐患单的关系表数据
		CheckAndDanger checkAndDanger=new CheckAndDanger();
		checkAndDanger.setCheckid(checkId);
		checkAndDanger.setDangerid(dangerId);
		int c=checkAndDangerService.insertCheckAndDanger(checkAndDanger);
		logger.info("====检查隐患关系表插入完毕");
		if (map.get("state").toString().equals("0")) {  //0代表是保存；1则是提交
			if (a==1&&b==1&&c==1) {
				result.setRtnCode("0");
				result.setRtnMsg("保存成功！");
				resultMap.put("resultCode", "0");
				resultMap.put("resultMsg", "操作成功！");
				result.setObject(resultMap);
				logger.info("======保存成功！");
			}else {
				result.setRtnCode("-9999");
				result.setRtnMsg("提交失败！");
				resultMap.put("resultCode", "-9999");
				resultMap.put("resultMsg", "操作失败！");
				result.setObject(resultMap);
				logger.info("======保存失败");
			}
		}else {  //等于1就是要提交
			//查询流程模板   查询模板1
			Flow flow=flowService.selectByPrimaryKey(1);
			//插入实例表
			FlowInstance flowInstance=new FlowInstance();
			//String instanceId=RandomUUID.RandomID();
			//flowInstance.setId(instanceId);
			flowInstance.setFlowid(flow.getId().toString());
			flowInstance.setFlowname(flow.getFlowname());
			flowInstance.setUserid(map.get("userId").toString());
			flowInstance.setUsername(map.get("userName").toString());
			flowInstance.setApplyusername(map.get("userName").toString());
			flowInstance.setApplydatetime(new Date());
			flowInstance.setStatusid("0");
			int d=flowInstanceService.insertFlowInstance(flowInstance);
			int instanceId=flowInstance.getId();
			logger.info("----==实例表插入成功");
			//插入实例和检查单关系表
			InstanceRelation instanceRelation=new InstanceRelation();
			instanceRelation.setCheckid(checkId);
			instanceRelation.setInstanceid(instanceId);
			instanceRelation.setDangerId(dangerId);
			int f=instanceRelationService.insert(instanceRelation);
			//查询操作表
			FlowAction flowAction=flowActionService.selectFlowAction(1);
			//根据stepId来查询步骤表
			FlowStep flowStep=flowStepService.selectFlowStep(flowAction.getStepid());
			//插入流转表
			FlowActionTrace flowActionTrace=new FlowActionTrace();
			flowActionTrace.setInstanceid(instanceId);
			flowActionTrace.setFlowid(flow.getId().toString());
			flowActionTrace.setFlowcode(flow.getFlowcode());
			flowActionTrace.setFlowname(flow.getFlowname());
			flowActionTrace.setStepid(flowStep.getStepid());
			flowActionTrace.setStepcode(flowStep.getStepcode());
			flowActionTrace.setStepname(flowStep.getStepname());
			flowActionTrace.setActionid(flowAction.getActionid());
			flowActionTrace.setActioncode(flowAction.getActioncode());
			flowActionTrace.setActionname(flowAction.getActionname());
			flowActionTrace.setOwneruserid(map.get("userId").toString());
			flowActionTrace.setOwnerusername(map.get("userName").toString());
			flowActionTrace.setOwneruserdesc(Constant.FA_QI_REN);
			flowActionTrace.setSubmituserid(map.get("userId").toString());
			flowActionTrace.setSubmitusername(map.get("userName").toString());
			flowActionTrace.setSubmituserdesc(flowAction.getActionname());
			flowActionTrace.setArrivetime(new Date());
			int e=flowActionTraceService.insertFlowActionTrace(flowActionTrace);
			String responsiblePersonId=map.get("responsiblePersonId").toString();  //下一步整改责任人的ID
			//流转表id
			int traceId=flowActionTraceService.insertFlowActionTrace(flowActionTrace,responsiblePerson,responsiblePersonId);
			logger.info("----==流转表插入成功"+traceId);
			//流转表id
			//插入信息到抄送人delivery表
			Map<String, Object> deliveryMap = new HashMap<String, Object>();
//			List<Map<String,Object>> deliveryList = JSONArray.fromObject(map.get("copyPerson"));
			deliveryMap.put("userId", map.get("userId").toString());
			deliveryMap.put("userName", map.get("userName").toString());
			deliveryMap.put("checkId", checkId);
			deliveryMap.put("dangerId", dangerId);
			deliveryMap.put("traceId", traceId);
			deliveryMap.put("statusId", "0");//0待阅，1已阅
			deliveryMap.put("deliveryList", deliveryList);
			int deliveryNum = flowInstanceService.addDelivery(deliveryMap);
			
			if (d==1&&f==1 && e==1 && deliveryNum>0) {
				result.setRtnCode("0");
				result.setRtnMsg("提交成功！");
				resultMap.put("resultCode", "0");
				resultMap.put("resultMsg", "操作成功！");
				result.setObject(resultMap);
				logger.info("----==提交成功");
			}else {
				result.setRtnCode("-9999");
				result.setRtnMsg("提交失败！");
				resultMap.put("resultCode", "-9999");
				resultMap.put("resultMsg", "操作失败！");
				result.setObject(resultMap);
				logger.info("----==提交失败");
			}
		}
		return result;
	}
	/**
	 * 根据检查单ID查询隐患单信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="selectDanger",method=RequestMethod.POST)
	public Result selectDanger(@RequestBody Map<String, Object> map) {
		Result result=new Result();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int checkId=Integer.parseInt(map.get("checkId").toString());
		DangerList dangerList=dangerListServie.selectDangerByCheckId(checkId);
		if (dangerList!=null) {
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "操作成功！");
			resultMap.put("dangerList", dangerList);
			result.setObject(resultMap);
		}
		return result;
	}
	/**
	 * 根据隐患单ID来删除隐患信息
	 * @param map
	 * @return
	 */
	@RequestMapping(value="delCheckAndDanger",method=RequestMethod.POST)
	public  Result delCheckAndDanger(@RequestBody Map<String, Object> map) {
		Result result=new Result();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//前台传隐患单ID
		int dangerId=Integer.parseInt(map.get("dangerId").toString());
		int  delResult=dangerListServie.delCheckAndDanger(dangerId);
		if (delResult!=0) {
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "操作成功！");
			resultMap.put("delResult", delResult);
			result.setObject(resultMap);
		}
		return result;
	}
	/**
	 * 整改提交
	 * @param map
	 * @return
	 */
	@RequestMapping(value="changeSubmit",method=RequestMethod.POST)
	public String  changeSubmit(@RequestBody Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//JSONObject inputJson = JSONObject.fromObject(map);
		logger.info("[流程状态-查询入参]"+map);
		Integer traceId=Integer.parseInt(map.get("traceId").toString());   //流转表的ID
		Integer instanceId=Integer.parseInt(map.get("instanceId").toString());  //流转表中的实例ID
		String userId=map.get("userId").toString();   //用户ID
		String userName=map.get("userName").toString();   //用户ID
		String checkId=map.get("checkId").toString(); //检查ID
		Integer dangerId=Integer.parseInt(map.get("dangerId").toString()); //隐患ID
		logger.info("=====流转表"+traceId+"=====整改情况更新结果"+userId+"---"+checkId+"---"+dangerId+"--"+userName);
		//整改需要的字段，更新隐患表
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
		int updateResult=dangerListServie.updateDanger(dangerList);
		logger.info("==========整改情况更新结果"+updateResult);
		//更新流转表
		FlowActionTrace flowActionTrace=new FlowActionTrace();
		flowActionTrace.setId(traceId);
		flowActionTrace.setSubmituserid(userId);
		flowActionTrace.setSubmitusername(userName);
		flowActionTrace.setSubmituserdesc("整改提交");
		int c=flowActionTraceService.updateChangeInfo(flowActionTrace,instanceId,responsiblePersonId,responsiblePerson);
		logger.info("==========更新流转表结果"+c);
		//插入信息到抄送人delivery表
		/*Map<String, Object> deliveryMap = new HashMap<String, Object>();
		List<Map<String,Object>> deliveryList = JSONArray.fromObject(map.get("copyPerson"));
		int deliveryNum = 0;
		if(deliveryList.size()>0){
			deliveryMap.put("userId", userId);
			deliveryMap.put("userName", userName);
			deliveryMap.put("checkId", checkId);
			deliveryMap.put("dangerId", dangerId);
			deliveryMap.put("traceId", traceId);
			deliveryMap.put("statusId", "0");//0待阅，1已阅
			deliveryMap.put("deliveryList", deliveryList);
			deliveryNum = flowInstanceService.addDelivery(deliveryMap);
		}*/
		//更新实例表
		int updateInstance=flowInstanceService.updateInstance(instanceId);
		if (updateInstance==0||updateResult==0) {
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("-9999", resultMap, null);
		}
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功！");
		return ResultUtil.result("0", resultMap, null);
	}
	
	/**
	 * 对新建数据进行处理	
	 * @param map
	 * @return
	 */
	public String[] dataProcess(Map<String, Object> map) {
		logger.info("[传用友处理新建数据入参]"+map);
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		//检查单信息封装
		paramsMap.put("proj_no", map.get("projNo"));   //项目编号
		paramsMap.put("record_no","");  //检查编号
		paramsMap.put("check_date", map.get("checkDate").toString());  //检查日期
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
		paramsMap.put("check_form", checkForm);   //检查形式
		paramsMap.put("check_content", "1");   //检查名称
		paramsMap.put("check_person", map.get("checkPerson").toString());  //检查人
		paramsMap.put("draft_date", map.get("draftDate").toString());    //编制日期
		paramsMap.put("approve_date", "");  //申请日期
		paramsMap.put("draft_unit", map.get("draftUnit").toString());  //编制单位
		paramsMap.put("draft_dept", map.get("draftDept").toString());  //编制部门
		paramsMap.put("draft_person", map.get("draftPerson").toString());  //编制人
		paramsMap.put("approve_person", "");  //申请人
		String recordType=Integer.valueOf(map.get("recordType").toString())==0?"Company":"Owner";
		paramsMap.put("record_type", recordType);  //检查单类型
		
		//隐患单信息封装
		resultMap.put("proj_no",map.get("projNo"));  //项目编号
		resultMap.put("record_no","");  //检查编号
		resultMap.put("line_no","");   //序号
		resultMap.put("distribution_date","");   //分发日期
		resultMap.put("unit",map.get("unit").toString());  //适用机组
		resultMap.put("area",map.get("area").toString());  //适用区域
		resultMap.put("track_people","SNG-CM");
		//隐患级别
		String hseHiddenLevel=Integer.valueOf(map.get("hseHiddenLevel").toString())==0?"GENERAL":"IMPORTANT";
		resultMap.put("hse_hidden_level",hseHiddenLevel);  //隐患级别
		//隐患属性
		int category=Integer.valueOf(map.get("hiddenCategory").toString());
		String hidden_category="";
		if (category==0) {   //0管理缺陷
			hidden_category="MANAGEMENTFLAW";
		}else if (category==1) {  //1人的不安全行为
			hidden_category="UNSAFEACT";
		}else if (category==2) {  //2物的不安全状态
			hidden_category="UNSAFECONDITION";
		}else {  //环境的不安全因素
			hidden_category="ENVIRONMENTFACTOR";
		}
		resultMap.put("hidden_category",hidden_category);   //隐患属性
		resultMap.put("nonconformity","");   //隐患类型
		resultMap.put("hidden_description",map.get("hiddenDescription").toString());  //隐患描述
		resultMap.put("req_complete_date",map.get("reqCompleteDate").toString());  //要求完成的日期
		resultMap.put("if_site_correction","");
		resultMap.put("corrective_request",map.get("correctiveRequest").toString());  //整改措施要求
		resultMap.put("corrective_content","");
		resultMap.put("complete_date","");
		resultMap.put("contractor_approve","");
		resultMap.put("contractor_people","");
		resultMap.put("contractor_approve_date","");
		resultMap.put("comfirm_content","");
		resultMap.put("verify_content","");
		
		list.add(resultMap);
		
		paramsMap.put("HseHiddenDangers", list);
		logger.info("[传用友处理新建数据封装完成参数]==="+paramsMap);
		
		JSONObject params = JSONObject.fromObject(paramsMap);
		
		WebServiceController webServiceController=new WebServiceController();
		
		//调用返回的结果
		String returnResult=webServiceController.createModifyHseSiteRecord(params);
		JSONObject json=JSONObject.fromObject(returnResult).getJSONObject("object");
		
		String recordNo=json.get("record_no").toString();
		String lineNo=json.get("line_no0").toString();
		logger.info("[获取到的用友检查单编号]==="+recordNo);
		logger.info("[获取到的用友隐患单序号]==="+lineNo);
		String  array[]= {recordNo,lineNo};
		
		return array;
		
	}
}
