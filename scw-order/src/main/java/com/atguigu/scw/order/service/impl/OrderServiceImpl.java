package com.atguigu.scw.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.scw.order.bean.TOrder;
import com.atguigu.scw.order.bean.TOrderExample;
import com.atguigu.scw.order.mapper.TOrderMapper;
import com.atguigu.scw.order.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	TOrderMapper orderMapper;
	
	@Override
	public void createorder(TOrder order) {
		orderMapper.insertSelective(order);
	}

	@Override
	public void updateOrderState(String ordernum, Integer status) {
		TOrderExample example = new TOrderExample();//封装条件
		example.createCriteria().andOrdernumEqualTo(ordernum);
		TOrder record = new TOrder();//封装修改信息的
		record.setStatus(status+"");
		orderMapper.updateByExampleSelective(record, example);
	}

}
