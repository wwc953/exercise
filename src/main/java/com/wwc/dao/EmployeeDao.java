package com.wwc.dao;

import org.mybatis.spring.annotation.MapperScan;

import com.wwc.bean.Employee;

@MapperScan
public interface EmployeeDao {
	public Employee getEmployee(int id);
}
