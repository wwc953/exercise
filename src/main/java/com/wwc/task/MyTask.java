package com.wwc.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时器 
 * 
 * 	http://www.jb51.net/article/110541.htm
 * 	http://blog.csdn.net/zp437734552/article/details/51899275
 * 
 */
//@Component
public class MyTask {
	
	public MyTask() {
		System.out.println("定时任务开启！！");
	}
	
	@Scheduled(cron = "0/1 * * * * ?")  
    void schedule() {  
       System.out.println("--"+new Date());
    } 
	
	@Scheduled(cron = "0/2 * * * * ?")  
    void schedule2() {  
       System.out.println(new Date());
    }
}
