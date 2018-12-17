package cn.hse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WithdrawTerminateMapper {

	List<Map<String, Object>> findWithdrawTerminate(Map<String, Object> map);

	Map<String, Object> findFlowMap();

	int updateFlowActionTrace(Map<String, Object> paramMap);


}
