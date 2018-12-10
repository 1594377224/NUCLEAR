package cn.mvtech.beans;

import java.io.Serializable;

import org.apache.ibatis.annotations.Mapper;
/**
 * 测试
 * @author 徐童
 *
 */
@Mapper
public class User implements Serializable{
	private Integer id;
	private String name;
	private Integer age;
	private String sex;
	private String address;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
