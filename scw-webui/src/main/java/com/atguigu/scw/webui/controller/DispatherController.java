package com.atguigu.scw.webui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.service.ProjectServiceFeign;
import com.atguigu.scw.webui.vo.request.ProjectVo;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class DispatherController {
	
	@Autowired
	ProjectServiceFeign projectServiceFeign;
	
	//跳转到项目首页的方法
	@GetMapping(value= {"/index.html" , "/"})
	public String toIndexPage(Model model) {
		//准备页面需要的数据
		//远程调用project服务获取项目列表
		AppResponse<List<ProjectVo>> response = projectServiceFeign.all();
		log.info("查询项目列表:"+response.getData());
		model.addAttribute("projects", response.getData());
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//有些controller映射方法没有方法内容，直接跳转到目标页面，之前在springmvc中可以通过<mvc:controller >处理代替方法
	//测试thymeleaf
	@RequestMapping("/hello")
	public String hello(Model model , HttpSession session) {
		/**
		 * 如何使用thymeleaf
		 * 如何取出域中属性值到页面中显示
		 * 如何使用thymeleaf遍历集合将数据显示到页面中
		 */
		model.addAttribute("requestKey", "requestValue");
		session.setAttribute("sessionKey", "sessionValue");
		session.getServletContext().setAttribute("appKey", "appValue");
		
		
		List<TMember> list = new ArrayList<TMember>();
		list.add(new TMember(1, "aa", "11", "sd", "aa", "1", "1", "aa", "aa", "aa"));
		list.add(new TMember(2, "bb", "11", "sd", "aa", "1", "1", "aa", "aa", "aa"));
		list.add(new TMember(3, "cc", "11", "sd", "aa", "1", "1", "aa", "aa", "aa"));
		list.add(new TMember(4, "dd", "11", "sd", "aa", "1", "1", "aa", "aa", "aa"));
		list.add(new TMember(5, "ee", "11", "sd", "aa", "1", "1", "aa", "aa", "aa"));
		list.add(new TMember(6, "ff", "11", "sd", "aa", "1", "1", "aa", "aa", "aa"));
		model.addAttribute("list", list);
		
		
		return "thymeleaf-hello";
	}
}
