package com.atguigu.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.vo.request.ProjectVo;
import com.atguigu.scw.webui.vo.request.ReturnPayConfirmVo;
import com.atguigu.scw.webui.vo.request.TReturn;
@FeignClient(value="SCW-PROJECT")
public interface ProjectServiceFeign {

	@GetMapping("/project/return/info")
	public AppResponse<ReturnPayConfirmVo> returnInfo(@RequestParam("returnId")Integer returnId);
	
	
	@GetMapping("/project/info/all")
	public AppResponse<List<ProjectVo>> all() ;
	
	@GetMapping("/project/details/info/{projectId}")
	public AppResponse<ProjectVo> detailsInfo(@PathVariable("projectId") Integer projectId);
}
