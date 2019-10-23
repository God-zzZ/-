package com.atguigu.scw.scwproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.scw.project.bean.TTag;
import com.atguigu.scw.project.mapper.TTagMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ScwProjectApplicationTests {
	@Autowired
	TTagMapper tagMapper;
	
	@Test
	public void contextLoads() throws Exception {
		tagMapper.insertSelective(new TTag(null, 0, "手机"));
		
		
		/*// Endpoint以杭州为例，其它Region请按实际情况填写。
		String scheme = "http://";
		String endpoint = "oss-cn-shanghai.aliyuncs.com";
		// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录
		// https://ram.console.aliyun.com 创建。
		String accessKeyId = "LTAI4FwAXErToEmigWfnnLy1";
		String accessKeySecret = "RRaiNGNIJAgmpcwJr1USdbEkat1K2P";
		String bucketName = "scw-20190615";
		String imgsDir = "imgs/";
		// 创建OSSClient实例。
		OSS ossClient = new OSSClientBuilder().build(scheme+endpoint, accessKeyId, accessKeySecret);
		File file = new File("C:\\Users\\86185\\Desktop\\2.gif");
		// 上传文件流。
		InputStream inputStream = new FileInputStream(file);
		String fileName = System.currentTimeMillis()+"_"+UUID.randomUUID().toString().replace("-", "")+"_"+file.getName();
		ossClient.putObject(bucketName, imgsDir+fileName, inputStream);
		//https://scw-20190615.oss-cn-shanghai.aliyuncs.com/imgs/1.gif
		// schemebucketName.endpoint/imgsDirfileName 
		log.info("图片上传成功的地址：{}" ,scheme+bucketName+"."+endpoint+"/"+imgsDir+fileName );
		// 关闭OSSClient。
		ossClient.shutdown();*/
	}

}
