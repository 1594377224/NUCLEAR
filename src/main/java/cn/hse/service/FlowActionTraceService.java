package cn.hse.service;

import cn.hse.beans.FlowActionTrace;

public interface FlowActionTraceService {

	int insertFlowActionTrace(FlowActionTrace flowActionTrace);

	int insertFlowActionTrace(FlowActionTrace flowActionTrace, String responsiblePerson,String responsiblePersonId);

	int updateChangeInfo(FlowActionTrace flowActionTrace,Integer instanceId,String responsiblePersonId,String responsiblePerson);

	int updateChange(FlowActionTrace flowActionTrace,Integer instanceId);

	int updateChangeLast(FlowActionTrace flowActionTrace, Integer instanceId);

	int updateFlowActionTrace(FlowActionTrace flowActionTrace);

	int updateRetResubmit(FlowActionTrace flowActionTrace, Integer instanceId, String responsiblePersonId,
			String responsiblePerson);
}
