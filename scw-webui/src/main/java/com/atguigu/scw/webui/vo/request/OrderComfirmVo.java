package com.atguigu.scw.webui.vo.request;

import java.io.Serializable;

import com.atguigu.scw.common.vo.BaseVo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderComfirmVo implements Serializable{
	private String accessToken;//用户登录成功的token
	private Integer returnid;
	private String ordernum;
	private String createdate;
	private Integer money;
	private Integer rtncount;
	private Integer status;
	private String address;
	private Integer invoice;
	private String invoictitle;
	private String remark;
	private Integer memberid;//购买者的id
	private Integer projectid;
}
