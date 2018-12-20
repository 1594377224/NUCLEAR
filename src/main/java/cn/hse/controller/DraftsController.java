package cn.hse.controller;

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
import cn.hse.util.ResultUtil;
import net.sf.json.JSONArray;

@RestController
@RequestMapping(value="/drafts")
public class DraftsController {
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
	//草稿箱保存操作
	@Transactional
	@RequestMapping(value="/draftsSave",method=RequestMethod.POST)
	public String draftsSave(@RequestBody Map<String, Object> map) {
		logger.info("【草稿箱保存操作】");
		/*logger.info("【草稿箱保存操作====开始调用用友接口保存】");
		CheckListController checkListController=new CheckListController();
		String array[]=checkListController.dataProcess(map);
		logger.info("【草稿箱保存操作====调用用友接口保存结束】");*/
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//封装检查单对象
	    CheckList checkList=new CheckList();
	    checkList.setId(Integer.parseInt(map.get("checkId").toString()));
	    checkList.setUserId(map.get("userId").toString());
	    checkList.setProjno(map.get("projNo").toString());   //项目编号
	    checkList.setState(Integer.valueOf(map.get("state").toString()));  //状态
	    checkList.setRecordno("");  //检查编号
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
	    //对检查单中的数据进行更新操作
	    int a=checkListService.updateCheck(checkList);  
	    //更新隐患单
	    DangerList dangerList=new DangerList();
		//String dangerId=RandomUUID.RandomID();
		dangerList.setId(Integer.parseInt(map.get("dangerId").toString()));
		dangerList.setLineno("");   //序号
		dangerList.setNoticeno("");//整改单编号
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
//		dangerList.setCopyPerson(map.get("copyPerson").toString());   //抄送人
		List<Map<String,Object>> deliveryList = JSONArray.fromObject(map.get("copyPerson"));
		dangerList.setCopyPerson(deliveryList.toString());   //抄送人
		int b=dangerListServie.updateDanger(dangerList);
		if (a==0 || b==0) {
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("-9999", resultMap, null);
		}
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功！");
		return ResultUtil.result("0", resultMap, null);
	}
	//草稿箱提交操作
	@Transactional
	@RequestMapping(value="/draftsSubmit",method=RequestMethod.POST)
	public String draftsSubmit(@RequestBody Map<String, Object> map) {
		logger.info("【草稿箱提交操作】");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//查询流程模板   查询模板1
		Flow flow=flowService.selectByPrimaryKey(1);
		//插入实例表	
		FlowInstance flowInstance=new FlowInstance();
		flowInstance.setFlowid(flow.getId().toString());
		flowInstance.setFlowname(flow.getFlowname());
		flowInstance.setUserid(map.get("userId").toString());     //用户id
		flowInstance.setUsername(map.get("userName").toString());  //用户姓名
		flowInstance.setApplyusername(map.get("userName").toString());   //用户姓名
		flowInstance.setApplydatetime(new Date());
		flowInstance.setStatusid("0");
		int d=flowInstanceService.insertFlowInstance(flowInstance);
		int instanceId=flowInstance.getId();
		logger.info("----==实例表插入成功");
		String responsiblePerson=map.get("responsiblePerson").toString();
		//插入实例表和检查单关系表
		Integer checkId=Integer.parseInt(map.get("checkId").toString());  //检查单id
		Integer dangerId=Integer.parseInt(map.get("dangerId").toString());  //隐患id
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
		int traceId =flowActionTraceService.insertFlowActionTrace(flowActionTrace,responsiblePerson,responsiblePersonId);
		logger.info("----==流转表插入成功"+traceId);
		//插入信息到抄送人delivery表
		Map<String, Object> deliveryMap = new HashMap<String, Object>();
		deliveryMap.put("dangerId", dangerId);
		Map<String,Object> copyPerson = flowInstanceService.findCopyPerson(deliveryMap);
		List<Map<String,Object>> deliveryList = JSONArray.fromObject(copyPerson.get("copyPerson"));
		if(deliveryList.isEmpty()){
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "操作失败！");
			return ResultUtil.result("-9999", resultMap, null);
		} else {
			deliveryMap.put("userId", map.get("userId").toString());
			deliveryMap.put("userName", map.get("userName").toString());
			deliveryMap.put("checkId", checkId);
			deliveryMap.put("traceId", traceId);
			deliveryMap.put("statusId", "0");//0待阅，1已阅
			deliveryMap.put("deliveryList", deliveryList);
			int deliveryNum = flowInstanceService.addDelivery(deliveryMap);
			if(traceId<0 && deliveryNum<0 && d<0 && e<0 && f<0){
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
				return ResultUtil.result("-9999", resultMap, null);
			}
		}
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功！");
		return ResultUtil.result("0", resultMap, null);
	}
	
	/**
	 * 延期申请审批通过，不通过
	 * @param map
	 * @return
	 */
	  @Transactional
	  @RequestMapping(value="/checkPass",method=RequestMethod.POST)
	  public String checkPass(@RequestBody Map<String, Object> map) {
		logger.info("【延期申请操作】");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//0通过 1不通过
		String pass=map.get("pass").toString();
		String delayToApplyForDate=map.get("delayToApplyForDate").toString();   //延期日期
		String dangerId=map.get("dangerId").toString();   //隐患id		
		String traceId=map.get("traceId").toString();   //流转id
		String userId=map.get("userId").toString();   //用户id
		String userName=map.get("userName").toString();
		if ("0".equals(pass)) {   //当延期申请通过
			//更新隐患表日期
			DangerList dangerList=new DangerList();
			dangerList.setId(Integer.parseInt(dangerId));
			dangerList.setReqcompletedate(DateUtil.string2Date(delayToApplyForDate));
			int a=dangerListServie.updateDanger(dangerList);
			//更新流程表
			FlowAction flowAction=flowActionService.selectFlowAction(10);
			FlowActionTrace flowActionTrace=new FlowActionTrace();
			flowActionTrace.setId(Integer.parseInt(traceId));
			flowActionTrace.setActionid(flowAction.getActionid());
			flowActionTrace.setActioncode(flowAction.getActioncode());
			flowActionTrace.setActionname(flowAction.getActionname());
			flowActionTrace.setSubmituserid(userId);
			flowActionTrace.setSubmitusername(userName);
			flowActionTrace.setSubmituserdesc(Constant.QUE_REN_REN);
			int b=flowActionTraceService.updateFlowActionTrace(flowActionTrace);
			if (a==0 ||b==0) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
				return ResultUtil.result("-9999", resultMap, null);
			}
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "操作成功！");
			return ResultUtil.result("0", resultMap, null);
		}else {//延期申请不通过
			//更新流程表
			FlowAction flowAction=flowActionService.selectFlowAction(11);
			FlowActionTrace flowActionTrace=new FlowActionTrace();
			flowActionTrace.setId(Integer.parseInt(traceId));
			flowActionTrace.setActionid(flowAction.getActionid());
			flowActionTrace.setActioncode(flowAction.getActioncode());
			flowActionTrace.setActionname(flowAction.getActionname());
			flowActionTrace.setSubmituserid(userId);
			flowActionTrace.setSubmitusername(userName);
			flowActionTrace.setSubmituserdesc(Constant.QUE_REN_REN);
			int b=flowActionTraceService.updateFlowActionTrace(flowActionTrace);
			if (b==0) {
				resultMap.put("resultCode", "-1");
				resultMap.put("resultMsg", "操作失败！");
				return ResultUtil.result("-9999", resultMap, null);
			}
			resultMap.put("resultCode", "0");
			resultMap.put("resultMsg", "操作成功！");
			return ResultUtil.result("0", resultMap, null);
		}
		  
	  }
}
