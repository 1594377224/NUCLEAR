package cn.hse.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.hse.beans.DelayToApplyFor;

@Mapper
public interface DelayToApplyForMapper {

	int addDelayToApplyFor(DelayToApplyFor delayToApplyForList);

	int upCheckAndDanger(Map<String, Object> paramMap);

	Map<String, Object> findFlowMap();

	int addFlowInstance(Map<String, Object> flowInstanceMap);

	int addFlowActionTraceSubmit(Map<String, Object> flowActionTraceSubmitMap);

	Map<String, Object> findFefundMap(Map<String, Object> paramMap);

	Map<String, Object> findFlowflowDalayMap();

	int addFlowActionTrace(Map<String, Object> paramMap);

}
