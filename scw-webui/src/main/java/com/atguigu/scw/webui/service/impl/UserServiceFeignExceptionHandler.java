package com.atguigu.scw.webui.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.service.UserServiceFeign;
import com.atguigu.scw.webui.vo.request.TMemberAddress;
@Service 
public class UserServiceFeignExceptionHandler implements UserServiceFeign{

	@Override
	public AppResponse<Object> doLogin(String loginacct, String userpswd) {
		//远程服务调用失败
		return AppResponse.fail(null, "远程服务调用失败");
	}

	@Override
	public AppResponse<List<TMemberAddress>> addressInfo(String accessToken) {
		return AppResponse.fail(null, "远程服务调用失败");
	}

}
