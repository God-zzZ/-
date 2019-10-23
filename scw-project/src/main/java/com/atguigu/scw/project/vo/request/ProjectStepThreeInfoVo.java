package com.atguigu.scw.project.vo.request;

import com.atguigu.scw.common.vo.BaseVo;

import lombok.Data;
@Data
public class ProjectStepThreeInfoVo extends BaseVo{
	 //企业收款账号
    private String alipayAccount;
    //法人身份证号
    private String idcard;
    
    private String projectToken;
}
