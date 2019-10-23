package com.atguigu.scw.webui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.vo.request.OrderComfirmVo;
@FeignClient("SCW-ORDER")
public interface OrderServiceFeign {

	//创建订单保存到数据库的方法
	@PostMapping("/order/createorder")
	public AppResponse<String> createOrder(@RequestBody OrderComfirmVo vo);
	
	//更新订单状态
	@PostMapping("/order/updateOrderState")
	public AppResponse<String> updateOrderState(@RequestParam("ordernum")String ordernum , @RequestParam("status")Integer status);
}
