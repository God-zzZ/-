package com.atguigu.scw.order.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.common.utils.ScwUtils;
import com.atguigu.scw.order.bean.TMember;
import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.service.OrderService;
import com.atguigu.scw.order.vo.request.OrderComfirmVo;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {
	/**
	 * 远程服务调用时：
	 * 	1、多个简单类型的参数
	 * 		@RequestParam("")绑定参数
	 * 	2、传递对象或map或list
	 * 		对象必须实现序列化接口
	 * 		必须使用@RequestBody注解标注
	 * @param vo
	 * @return
	 */
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	@Autowired
	OrderService orderService;
	
	//更新订单状态的请求
	@PostMapping("/order/updateOrderState")
	public AppResponse<String> updateOrderState(@RequestParam("ordernum")String ordernum , @RequestParam("status")Integer status){
		orderService.updateOrderState(ordernum , status);
		return AppResponse.ok(null, "修改订单状态成功");
	}
	
	
	
	//创建订单保存到数据库的方法
	@PostMapping("/order/createorder")
	public AppResponse<String> createOrder(@RequestBody OrderComfirmVo vo){
		log.info("传入的参数vo:{}" , vo);
		//vo  memberid：没有设置，可以根据accessToken去查询，查询失败代表未登录
		TMember member = ScwUtils.getBeanFromRedis(stringRedisTemplate, vo.getAccessToken(), TMember.class);
		vo.setMemberid(member.getId());
		//将vo转为TOrder
		TOrder order = new TOrder();
		BeanUtils.copyProperties(vo, order);
		order.setStatus(vo.getStatus()+"");
		order.setInvoice(vo.getInvoice()+"");
		
		log.info("转换后的订单对象:{}" , order);
		//调用业务层将TOrder存到数据库中
		orderService.createorder(order);
		//给webui项目响应结果
		return AppResponse.ok(null, "订单创建成功");
	}
	
}
