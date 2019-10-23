package com.atguigu.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.service.impl.UserServiceFeignExceptionHandler;
import com.atguigu.scw.webui.vo.request.TMemberAddress;
@FeignClient(value="SCW-USER" , fallback=UserServiceFeignExceptionHandler.class)
public interface UserServiceFeign {
	
	/**
	 * 远程服务调用：
	 * 	1、没有传过多个参数
	 * 		 Method has too many Body parameters: 方法传递的参数过多
	 * 			传递参数，必须使用@RequestParam 注解绑定参数
	 * 	2、返回值结果不复杂
	 * 		如果返回数据复杂，自定义类型必须实现序列化接口
	 * 		消费者必须有生产者响应数据的类型
	 * @param loginacct
	 * @param userpswd
	 * @return
	 */
	@PostMapping("/user/doLogin")
	public AppResponse<Object> doLogin(@RequestParam("loginacct")String loginacct , @RequestParam("userpswd")String userpswd);


	@PostMapping("/user/info/address")
	public AppResponse<List<TMemberAddress>> addressInfo(@RequestParam("accessToken")String accessToken);

}
