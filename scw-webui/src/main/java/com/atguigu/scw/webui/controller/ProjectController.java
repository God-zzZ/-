package com.atguigu.scw.webui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.service.ProjectServiceFeign;
import com.atguigu.scw.webui.vo.request.ProjectVo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class ProjectController {
	
	@Autowired
	ProjectServiceFeign projectServiceFeign;
	
	@GetMapping("/project/info/{id}")
	public String projectDetails(@PathVariable("id")Integer id , Model model) {
		
		//远程调用project服务，查询指定id的project详情
		AppResponse<ProjectVo> response = projectServiceFeign.detailsInfo(id);
		//共享到request域中
		log.info("查询id={}的项目详情：{}" , id , response.getData());
		model.addAttribute("project", response.getData());
		//转发到页面中显示
		return "project/project";
	}
	
	
}
