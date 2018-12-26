package cn.hse.service;

import java.util.Map;

import cn.hse.beans.DangerList;

public interface DangerListServie {

	public int insertDanger(DangerList dangerList);

	public DangerList selectDangerByCheckId(int checkId);

	public int delCheckAndDanger(int dangerId);

	public int updateDanger(DangerList dangerList);
	//更新延期申请表中isDelay 延期申请审批标识（0未审批，1审批通过，2审批不通过，3发起审批）
	public int updateDelayNum(Map<String, Object> paramMap);

}
