package cn.hse.controller;

import java.util.Date;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.hse.beans.CheckAndDanger;
import cn.hse.beans.CheckList;
import cn.hse.beans.DangerList;
import cn.hse.beans.Flow;
import cn.hse.beans.FlowInstance;
import cn.hse.service.CheckAndDangerService;
import cn.hse.service.CheckListService;
import cn.hse.service.DangerListServie;
import cn.hse.service.FlowService;
import cn.hse.util.DateUtil;
import cn.hse.util.RandomUUID;
import cn.hse.util.Result;

@RequestMapping("checkList")
public class CheckListController {
	private static final Logger logger=LogManager.getLogger(CheckListController.class);
	@Autowired
	private CheckListService checkListService;
	@Autowired
	private DangerListServie DangerListServie;
	@Autowired
	private CheckAndDangerService checkAndDangerService;
	@Autowired
	private FlowService flowService;
	/**
	 * 新建检查单
	 * @return
	 */
	@RequestMapping(value="insertCheck",method=RequestMethod.POST)
	public Result insertCheck(@RequestBody Map<String, Object> map){
		logger.info("=======进入新建检查单========接收参数="+map);
		Result result=new Result();
		//封装检查单对象
		CheckList checkList=new CheckList();
		String checkId=RandomUUID.RandomID();
		checkList.setId(checkId);
		checkList.setProjno(map.get("projNo").toString());   //项目编号
		checkList.setState(Integer.valueOf(map.get("state").toString()));  //状态
		checkList.setRecordno(map.get("recordNo").toString());  //检查编号
		checkList.setCheckdate(DateUtil.string2Date(map.get("checkDate").toString()));//检查日期
		checkList.setCheckform(Integer.valueOf(map.get("checkForm").toString())); //检查形式
		checkList.setRecordtype(Integer.valueOf(map.get("recordType").toString()));  //检查单类型
	//	checkList.setCheckcontent(map.get("checkContent").toString());   //检查名称
		checkList.setCheckperson(map.get("checkPerson").toString());  //检查人
		checkList.setDraftunit(map.get("draftUnit").toString());   //编制单位
		checkList.setDraftdept(map.get("draftDept").toString());  //编制部门
		checkList.setDraftperson(map.get("draftPerson").toString());   //编制人
		checkList.setDraftdate(DateUtil.string2Date(map.get("draftDate").toString()));  //编制日期
		/*checkList.setApproveperson(map.get("approvePerson").toString());  //批准人
		checkList.setApprovedate(DateUtil.string2Date(map.get("approveDate").toString()));  //批准日期
*/		checkList.setIsdel(0);
		int a=checkListService.insertCheck(checkList);
		//封装隐患单对象
		DangerList dangerList=new DangerList();
		String dangerId=RandomUUID.RandomID();
		dangerList.setId(dangerId);
		dangerList.setLineno(map.get("lineNo").toString());   //序号
		dangerList.setNoticeno(map.get("noticeNo").toString());//整改单编号
		dangerList.setDistributdate(DateUtil.string2Date(map.get("distributDate").toString()));  //分发日期
		dangerList.setUnit(map.get("unit").toString());  //适用机组
		dangerList.setArea(map.get("area").toString());  //区域
		dangerList.setArea(map.get("unitID").toString());  //被检查单位
		dangerList.setArea(map.get("hseHiddenLevel").toString());  //隐患级别
		dangerList.setArea(map.get("hiddenCategory").toString());  //隐患属性
		dangerList.setArea(map.get("nonconformity").toString());  // 隐患类型
		dangerList.setArea(map.get("hiddenDescription").toString());  //隐患描述
		dangerList.setArea(map.get("hiddenDoc").toString());   //隐患附件
		dangerList.setArea(map.get("reqCompleteDate").toString());   //要求完成时间
		dangerList.setArea(map.get("correctiveRequest").toString());  //整改措施要求
		//dangerList.setArea(map.get("rectificationSituation").toString()); //整改情况描述
		dangerList.setResponsibledate(new Date());  //接收日期
		dangerList.setContractonpeople(map.get("contractonPeople").toString());  //整改单编制人
		dangerList.setResponsibleperson(map.get("responsiblePerson").toString());  //整改责任人
		dangerList.setCopyPerson(map.get("copyPerson").toString());   //抄送人
		int b=DangerListServie.insertDanger(dangerList);
		//生成检查单个隐患单的关系表数据
		CheckAndDanger checkAndDanger=new CheckAndDanger();
		checkAndDanger.setCheckid(checkId);
		checkAndDanger.setDangerid(dangerId);
		int c=checkAndDangerService.insertCheckAndDanger(checkAndDanger);
		
		if (map.get("state").toString()=="0") {  //0代表是保存；1则是提交
			if (a==0 && b==0 && c==0) {
				result.setRtnCode("0");
				result.setRtnMsg("保存成功！");
			}
		}else {  //等于1就是要提交
			//查询流程模板   查询模板1
			Flow flow=flowService.selectByPrimaryKey("1");
			//插入实例表
			FlowInstance flowInstance=new FlowInstance();
			String instanceId=RandomUUID.RandomID();
			flowInstance.setId(instanceId);
			flowInstance.setFlowid(flow.getId());
			flowInstance.setFlowname(flow.getFlowname());
			flowInstance.setUserid(map.get("userId").toString());
			flowInstance.setUsername(map.get("userName").toString());
			flowInstance.setApplyusername(map.get("userName").toString());
			flowInstance.setApplydatetime(new Date());
			flowInstance.setStatusid("0");
			
		}
		return result;
	}
}
