package cn.hse.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.hse.beans.User;
import net.sf.json.JSONObject;
@Mapper
public interface UserMapper {
	//查询所有的用户
	public List<User> findAll();
	//添加用户
	public int addUser(JSONObject paramsJson);
	public int addUserTest(@Param("i")int i,@Param("j")int j);
	public List<Map<String, Object>> findUser(JSONObject paramsJson);
}
