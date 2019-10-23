package com.atguigu.scw.project.vo.request;

import java.util.List;

import com.atguigu.scw.common.vo.BaseVo;
import com.atguigu.scw.project.bean.TProjectInitiator;
import com.atguigu.scw.project.bean.TReturn;

import lombok.Data;
@Data
public class ProjectRedisStorageVo extends BaseVo{ 
	// 使用BeanUtils对拷数据时，必须保证对拷的属性名和数据类型都一样
     //全量   增量    
     private String projectToken;//项目的临时token 
     private Integer memberid;//发布项目的会员id 
     private List<Integer> typeids; //项目的分类id 
     private List<Integer> tagids; //项目的标签id 
     private String name;//项目名称 
     private String remark;//项目简介 
     private Integer money;//筹资金额 
     private Integer day;//筹资天数 
     private String headerImage;//项目头部图片 url地址
     private List<String> detailsImage;//项目详情图片 url地址
     private List<TReturn> projectReturns;//项目回报 
     //发起人信息：自我介绍，详细自我介绍，联系电话，客服电话
     private TProjectInitiator projectInitiator; 
     //企业收款账号
     private String alipayAccount;
     //法人身份证号
     private String idcard;
}

