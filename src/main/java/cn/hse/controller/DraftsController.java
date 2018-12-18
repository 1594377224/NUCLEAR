package cn.hse.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hse.beans.CheckList;
import cn.hse.service.CheckListService;
import cn.hse.util.DateUtil;

@RestController
@RequestMapping(value="/drafts")
public class DraftsController {
	private static final Logger logger=LogManager.getLogger(CheckListController.class);
	@Autowired
	private CheckListService checkListService;
	//草稿箱保存操作
	@RequestMapping(value="/draftsSave",method=RequestMethod.POST)
	public String draftsSave(@RequestBody Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		//封装检查单对象
	    CheckList checkList=new CheckList();
	    //String checkId=RandomUUID.RandomID();
	    //checkList.setId(checkId);
	    checkList.setId(Integer.parseInt(map.get("id").toString()));
	    checkList.setUserId(map.get("userId").toString());
	    checkList.setProjno(map.get("projNo").toString());   //项目编号
	    checkList.setState(Integer.valueOf(map.get("state").toString()));  //状态
	    checkList.setRecordno("2");  //检查编号
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
		return null;
	}
	//草稿箱保存操作
	@RequestMapping(value="/draftsSubmit",method=RequestMethod.POST)
	public String draftsSubmit(@RequestBody Map<String, Object> map) {
		return null;
	}
}
