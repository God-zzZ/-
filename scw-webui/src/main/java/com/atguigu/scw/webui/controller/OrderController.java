package com.atguigu.scw.webui.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.atguigu.scw.common.utils.AppDateUtils;
import com.atguigu.scw.common.utils.AppResponse;
import com.atguigu.scw.webui.config.AlipayConfig;
import com.atguigu.scw.webui.service.OrderServiceFeign;
import com.atguigu.scw.webui.service.ProjectServiceFeign;
import com.atguigu.scw.webui.service.UserServiceFeign;
import com.atguigu.scw.webui.vo.request.OrderComfirmVo;
import com.atguigu.scw.webui.vo.request.ReturnPayConfirmVo;
import com.atguigu.scw.webui.vo.request.TMemberAddress;

import lombok.extern.slf4j.Slf4j;
//处理订单和结账请求
@Controller
@Slf4j
public class OrderController {

	@Autowired
	ProjectServiceFeign projectServiceFeign;
	@Autowired
	UserServiceFeign userServiceFeign;
	@Autowired
	OrderServiceFeign orderServiceFeign;
	@PostMapping("/order/notify_url")
	public String notifyUrl(HttpServletRequest request) throws Exception {
		//异步通知时，如果支付没有问题，不做任何操作
		//如果响应的是支付异常，修改订单状态，并生成异常信息
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		
		boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

		//——请在这里编写您的程序（以下代码仅作参考）——
		
		/* 实际验证过程建议商户务必添加以下校验：
		1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
		3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		4、验证app_id是否为该商户本身。
		*/
		if(signVerified) {//验证成功
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			}else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}
			
			//out.println("success");
			
		}else {//验证失败
			//out.println("fail");
		
			//调试用，写文本函数记录程序运行情况是否正常
			//String sWord = AlipaySignature.getSignCheckContentV1(params);
			//AlipayConfig.logResult(sWord);
		}
		return "";
	}
	
	
	
	//用户支付成功后 支付宝的回调方法： return_url   同步通知
	@ResponseBody
	@GetMapping(value="/order/return_url" , produces="text/html")
	public String returnUrl(HttpSession session,HttpServletRequest request) {
		//获取支付宝GET过来反馈信息
		try {
			Map<String,String> params = new HashMap<String,String>();
			Map<String,String[]> requestParams = request.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

			//——请在这里编写您的程序（以下代码仅作参考）——
			if(signVerified) {
				//商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//付款金额
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
				//更新订单的状态：变成已支付:out_trade_no  , 1 
				AppResponse<String> response = orderServiceFeign.updateOrderState(out_trade_no, 1);
				if(response.getCode()!=10000) {
					return "订单状态更新失败";
				}
				//清空session域中保存的订单的其他信息：
				session.removeAttribute("confirmReturn");
				//orderServiceFeign.updateOrderState();
				//给用户响应
				return "<a href='"+request.getContextPath()+"/'><br/></a>trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
				/*out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);*/
			}else {
				return "验签失败";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "支付发生异常";
		}
	}
	
	///order/paystep1/1
	@GetMapping("/order/paystep1/{returnId}")
	public String payStep1(HttpSession session , @PathVariable("returnId")Integer returnId) {
		//1、查询购买的回报档位信息
		AppResponse<ReturnPayConfirmVo> response = projectServiceFeign.returnInfo(returnId);
		//2、将数据存到session域中共享[用完后记得要删除]
		session.setAttribute("confirmReturn", response.getData());
		log.info("查询到的确认回报信息：{}" , response.getData());
		//3、跳转到页面显示数据
		return "order/pay-step-1";
	}
	@GetMapping("/order/paystep2/{count}")
	public String payStep2(@RequestHeader("Referer") String referer,Model model , HttpSession session , @PathVariable("count")Integer count) {
		//获取session中保存的 确认回报对象
		ReturnPayConfirmVo vo = (ReturnPayConfirmVo) session.getAttribute("confirmReturn");
		vo.setNum(count);//修改购买的回报数量
		//修改总金额
		Integer totalPrice = vo.getFreight()+vo.getNum()*vo.getPrice();
		vo.setTotalPrice(new BigDecimal(totalPrice+""));
		
		//获取当前用户的收货地址:从session中获取用户登录的信息，如果有代表已经登录   ，必须验证登录状态
		Map member = (Map) session.getAttribute("member");
		if(member==null) {
			//获取referer地址，共享到session中
			session.setAttribute("path", referer);
			
			//未登录
			model.addAttribute("errorMsg", "结账操作必须登录");
			return "user/login";//登录失败转发到login页面
		}
		//已登录，查询用户的收货地址: 调用scw-user服务根据accessToken查询当前用户的地址信息
		AppResponse<List<TMemberAddress>> response = userServiceFeign.addressInfo((String)(member.get("accessToken")));
		model.addAttribute("addresses", response.getData());
		log.info("查询到的地址列表：{}",  response.getData() );
		//跳转到pay-step-2.html 显示用户的订单信息
		return "order/pay-step-2";
	}
	
	
	//处理结账请求的方法
	@ResponseBody  //@ResponseBody 让springmvc不解析return的内容，produces告诉浏览器响应体内容类型[response.setContenType("text/html")]
	@PostMapping(value="/order/checkout" , produces="text/html") 
	public String checkout(HttpSession session , OrderComfirmVo vo) {
		//登录验证
		//使用accessToken提交请求给scw-user项目去查询，验证登录是否失效过期
		//收集订单数据
		log.info("接受到的订单信息："+vo);
		//初始化其他通过业务逻辑设置的数据
		ReturnPayConfirmVo confirmReturn = (ReturnPayConfirmVo) session.getAttribute("confirmReturn");
		Integer returnid = confirmReturn.getReturnId();
		Map member = (Map) session.getAttribute("member");
		String ordernum = System.currentTimeMillis()+""+UUID.randomUUID().toString().replace("-", "").substring(0, 10);
		String createdate = AppDateUtils.getFormatTime();
		Integer status = 0;// 0代表未支付 ， 1代表已支付 ， 2代表支付失败 ，3 代表交易取消
		Integer projectid = confirmReturn.getProjectId();
		Integer price = confirmReturn.getPrice();
		Integer freight = confirmReturn.getFreight();
		String projectname = confirmReturn.getProjectName();
		String returncontent = confirmReturn.getReturnContent();
		//将参数绑定给OrderComfirmVo
		vo.setReturnid(returnid);
		vo.setOrdernum(ordernum);
		vo.setProjectid(projectid);
		vo.setCreatedate(createdate);
		vo.setStatus(status);
		vo.setAccessToken((String) member.get("accessToken"));
		vo.setMoney(vo.getRtncount()*price+freight);
		//在用户支付之前将订单数据持久化存到mysql中[状态是0，未支付]
		AppResponse<String> appResp = orderServiceFeign.createOrder(vo);
		if(appResp.getCode()!=10000) {
			return "error/order_error";
		}
		//使用alipaySDK处理数据，生成表单和js代码
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = vo.getOrdernum();//
		//付款金额，必填
		String total_amount = vo.getRtncount()*price+freight+"";//根据提交的参数计算
		//订单名称，必填
		String subject = projectname;//使用订单名称
		//商品描述，可空
		String body = returncontent;//使用订单的回报内容
		
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		//请求
		String result = "";
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		//用户支付成功了，可以在return_url方法中 修改订单状态改为已支付
		//将表单和js写给浏览器，让浏览器执行[浏览器会将数据提交给支付宝再跳转到支付页面]
		//response.getW
		return result;
	}
	
	
	
}
