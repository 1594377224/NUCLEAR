package cn.hse.service;

import net.sf.json.JSONObject;
/**
 * 延期申请
 * @author 
 *
 */
public interface DelayToApplyForService {

	String findDelayToApplyFor(JSONObject inputJson);

	String findDelayNum(JSONObject inputJson);

}
