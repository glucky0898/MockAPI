package com.atf.dto;

import com.alibaba.fastjson.JSONObject;
import com.atf.common.ValidationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * 
 * @ClassName: BaseDto
 * @Description: 数据传输对象基类
 * @author yangwei
 * @date 2017年4月11日 下午12:58:00 
 *
 */
public class BaseDto implements Serializable, Cloneable{
	/** 
	 * @Fields serialVersionUID :  
	 */
	private static final long serialVersionUID = -3216106500343224886L;
	
	/**
	 * 
	* @Title: validate 
	* @Description: 验证参数合法性
	* @return 返回null 或者空 "" 表示参数验证成功，其他则返回错误描述信息
	 */
	public void validate()  {
		ValidationUtil.validate(this);
	}
	
	/** 
	* @Title: toString 
	* @Description:  
	* @return    设定文件 
	*/
	@Override
	public String toString() {
		BaseDto req = null;

		return JSONObject.toJSONString(req);
	}

	
	/**
	 * 
	* @Title: invokeGet 
	* @Description: 获取字段值
	* @param fieldName
	* @return    设定文件
	 */
	private Object invokeGet(String fieldName){
		Method mget = ReflectionUtils.findMethod(this.getClass(), "get"+ StringUtils.capitalize(fieldName));
		return ReflectionUtils.invokeMethod(mget, this);
	}
	
	/**
	 * 
	* @Title: invokeSet 
	* @Description: 设置字段值
	* @param fieldName
	* @param param
	* @param paramType    设定文件
	 */
	private void invokeSet(String fieldName, Object param, Class<?>... paramType){
		Class<?> type = null;
		if(null!=param && (null==paramType || paramType.length==0)){
			type = param.getClass();
		}
		Method mset = ReflectionUtils.findMethod(this.getClass(), "set"+ StringUtils.capitalize(fieldName), type);
		ReflectionUtils.invokeMethod(mset, this, param);
	}
	
}