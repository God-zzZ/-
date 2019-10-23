package com.atguigu.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.service.UserServiceFeign;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	UserServiceFeign userServiceFeign;
	//处理登录请求
	@PostMapping("/user/doLogin")
	public String doLogin(HttpSession session,Model model , String loginacct , String userpswd) {
		//由于用户操作的业务在scw-user项目中已经编写了
		AppResponse<Object> response = userServiceFeign.doLogin(loginacct, userpswd);
		log.info("响应结果："+response);
		if(response!=null && response.getCode()==10000) {
			//保持登录状态:登录成功response的响应数据保存的是 MemberResponseVo
			session.setAttribute("member", response.getData());
			//登录成功 response.getData就是我们登录成功的数据
			//如果是从结账页面跳转过来登录的，需要跳转回登录之前的页面，否则跳转到首页
			String path = (String) session.getAttribute("path");
			if(StringUtils.isEmpty(path)) {
				return "redirect:/index.html";//重定向
			}else {
				//删除使用完毕的path
				 session.removeAttribute("path");
				return "redirect:"+path;
			}
		}else {
			model.addAttribute("errorMsg", response.getMessage());
			return "user/login";//登录失败转发到login页面
		}
	}
	
	//跳转到登录页面
	@GetMapping("/login.html")
	public String toLoginPage() {
		return "user/login";
	}
}
