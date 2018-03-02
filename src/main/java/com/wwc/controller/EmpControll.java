package com.wwc.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.wwc.bean.Employee;
import com.wwc.service.IService;

/*
 * ssm注解开发框架搭建	 http://blog.csdn.net/sinat_33982461/article/details/75202499
 * spring与mybatis三种整合方法 	https://www.cnblogs.com/wangmingshun/p/5674633.html
 */

@Controller
@SessionAttributes(value = { "see", "hh" }) // sesion域中的key，只能放在类上
public class EmpControll {

	// @Resource和@Autowired区别		 https://www.zhihu.com/question/39356740
	@Resource(name = "empService")
	// @Autowired
	private IService iservice;

	// 传参 https://www.cnblogs.com/helloworld-hyyx/p/5295514.html

	@RequestMapping("/emp")
	public String hello(String iid, String name, Model mode) {
		System.out.println("iid--" + iid);
		System.out.println("name--" + name);

		Employee emp = iservice.getEmployee(Integer.parseInt(iid));
		mode.addAttribute("obj", emp);

		mode.addAttribute("see", "哈哈哈session哈哈哈");
		mode.addAttribute("hh", "hh");

		return "aa";
	}

	@RequestMapping("/emp/{iid}")
	public String hello2(@PathVariable String iid, Model mode) {

		Employee emp = iservice.getEmployee(Integer.parseInt(iid));
		mode.addAttribute("obj", emp);

		return "aa";
	}

	@RequestMapping("/getjson")
	@ResponseBody
	public String json(String name) throws UnsupportedEncodingException {

		System.out.println("name-->" + name);

		Employee emp = iservice.getEmployee(1);
		String result = JSON.toJSONString(emp);

		System.out.println("result:" + result);

		return result;
	}

	/**
	 * 文件上传
	 * 
	 * @param file
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	public String upload(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

		if(file==null)
			return "error";
		
		String serverpath = request.getSession().getServletContext().getRealPath("upload");

		SimpleDateFormat smd = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = smd.format(new Date());

		 String path = "d:\\" + currentDate +"\\"+ file.getOriginalFilename();
//		String path = serverpath + "\\" + currentDate + "\\" + file.getOriginalFilename();

		System.out.println("path--" + path);

		File newFile = new File(path);

		if (!newFile.exists())
			newFile.mkdirs();

		file.transferTo(newFile);

		return "success";
	}
	
	/**
	 * 文件下载
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download")    
    public ResponseEntity<byte[]> download() throws IOException {    
    	
        String path="d:\\2018-03-01\\a.jpg.jpg";
        
        File file=new File(path);  
        HttpHeaders headers = new HttpHeaders();    
        //String fileName=new String("你好.xlsx".getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        String fileName="jedis-2.9.0.jar";
        
        headers.setContentDispositionFormData("attachment", fileName);  
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);  
        
        
    }    

}
