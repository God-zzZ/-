package com.atguigu.scw.order.service;

import com.atguigu.scw.order.bean.TOrder;

public interface OrderService {

	void createorder(TOrder order);

	void updateOrderState(String ordernum, Integer status);

}
