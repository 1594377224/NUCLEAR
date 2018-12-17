package cn.hse.service;

import cn.hse.beans.FlowActionTrace;

public interface FlowActionTraceService {

	int insertFlowActionTrace(FlowActionTrace flowActionTrace);

	int insertFlowActionTrace(FlowActionTrace flowActionTrace, String responsiblePerson,String responsiblePersonId);

	int updateChangeInfo(FlowActionTrace flowActionTrace,Integer instanceId,String responsiblePersonId,String responsiblePerson);

	int updateChange(FlowActionTrace flowActionTrace,Integer instanceId);

}
