package com.atguigu.scw.webui.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		public static String app_id = "2016092900621804";
		
		// 商户私钥，您的PKCS8格式RSA2私钥
	    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDmjMJ4rk7sxPkr43QKDJydvyscHnHtK8OA1U6IJoJvI3TTObKIvGJNRdXl4pOPMgz+Lh6+qfzGOhT4G6M+AMO+Z8hNMnNhuGNtGZANxif09JHBuHKoccSThqqkOCjkG+Suxv2AAgqcloftIOn9uQ8lGE6tmlSamNIG03YCZMRUOimn+WXCV2kLNA99rVq+LUpHkz9VuRG1I4llVKDfOtRMJqzf3j68o3uHjfMBR3Zyv0kGQ/eARuq0Ehgbn02SySSmClP4YJh87XEGKZOYBCqd+q7f8zBqhib3WhUs/08pPM3B74686cZu9OLvXiTmsywjAl7Nb72/A+99gBz3iCHhAgMBAAECggEALhrkISIqomrZsY8w+3l+MIW0Z1EnU1i3JiKCK/QXjYY3rK4TKAkUV4OpvOlMvQwzUPmEKHjyFeozm1JbmOCqRH+YrOdz74ziRUUha7++6dum8miidVEbEK2Tnnekpcl2OAWPWHQQDzSDRkaYxKangrGTul/sn2YESl0rclsYcAc4cmhc+cppS5gENosZKooLm9UNds/l/IEVEt8PZID99DVFFJq1twR7IZCMluhDx7etkpk78RN81SAxoXxJxVI6cfkIv6Ms0NZj2rSESB3ppLuPdVNlFC+TZOOz0v78mNKx/Qv0zXswoqylQ+vbzFbFePB8RnZ7FS+CF9jEX5C9YQKBgQD3Vn7/RKfF9i+Q+DvDkHDhmyp6qOTT7FR88CetVSUD6ioKSfAfn/BXTCq9iRiM6f6FXGsiH/cGP2nziZZBzBfCWs3ONHe2S9Nm/yQfYUK9d88947+hBHcwGA4kI0PjfUUNFGTwGBURoJgO4qJC2YcnDpklFGvbFXxg6nOYEOjcXQKBgQDun8AvNZ8DQz8E28sOulmlnnMib7P/CVM3qmuHr7zGAb5Crk+9zchP/C9Te5q4n7IC3fIFQ+IUVrZPHqK/GNlQZc+/m+hT6bRWL1SLIh/4kDXoRpEJkQSyu1+d7kq5ELunNjwZjoY4Yh/i7D+AieYYYk9DCn4Dy/lo2WPd2QtjVQKBgC0r1dvG7SEhWgSO4VNRENyTGwn1yHzPv4plzclBMJZrcKwaXslXy2wWFAQzgpFRttMiUsqTreitMitxKEjx/jDXppiPSoG3jW9VD4LPn4G83NN/waKljyLwcby12BM28KJ35AtZSpfCjaLCT0IIOj+u0ddMyHnljaahiuTtyaldAoGBAK2vjZ1PNDyVt8r7iGNh9Fp+aGNKVwNtcCr2Wmpa2dZt8mRqqBQJMqI1duUW9NkpM9KuZbi7XlVbgDs6m07o8VR5pqta+dIKK38H2EulPQwZO4OjyXHjIj5N8BzuE2l86nmdGkWn3b4wffEX8GjI9rgM5pBvlKWC1Dki8nPJxWY5AoGBANc/8z3Dcmf6GWn2YLsp7ZhP0GiYWWmTiX9HZ2j9dt+OmJlkEidSNkaBOd2bECzGviSvTPYI+PkjnoHZ91eLW6jzOE+F9oraBOG5mceVkpcbh2gdqjlN8mE9BQKxxLre33U3m42WlmvPSMo8KIDVKgLyZA6ZaSwVNpdwJZoplUUF";
		
		// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAibeLGhTLL3yfHCCtlVz9H538HI3xkL9UEriXsiX6rTaE+Tvx1uALV1IAkIDYTiLQEz1YhCfdsNL4VypcxaHCjF6Z7kRVIM/bYhdBJXESKpGUKP1ASFOiytdhdeYGxXEQk5NYfLAdRN/COFOAcWianJC2vao2woNbLyIK4wX8G+x4OostJi3kA00pUh/rYdiJbLdcpF7KMKhtTbEUHKKHeXRlZ7ItFko7VSwVOAkKe3a/70uoTKVwn7MAvYa7gP8/NLPboKEaQC+Zu02QZtq7r2w8+Jha+tn4vHPHlSYTLVl+bmZupiDzx02/qIgioatqj7st8rjVmDvwYj3Ph8OLBwIDAQAB";

		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "http://101.132.138.187:10000/order/notify_url";
	//内网穿透软件 启动地址映射后，2x46f41192.qicp.vip 访问时就相当于访问本机上的localhost:8080 
		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String return_url = "http://101.132.138.187:10000/order/return_url";

		// 签名方式
		public static String sign_type = "RSA2";
		
		// 字符编码格式
		public static String charset = "utf-8";
		
		// 修改为测试的支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
		
		// 保存日志的路径
		public static String log_path = "F:\\";


	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	    /** 
	     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	     * @param sWord 要写入日志里的文本内容
	     */
	    public static void logResult(String sWord) {
	        FileWriter writer = null;
	        try {
	            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
	            writer.write(sWord);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (writer != null) {
	                try {
	                    writer.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	}
