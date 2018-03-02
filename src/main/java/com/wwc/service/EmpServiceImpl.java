package com.wwc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wwc.bean.Employee;
import com.wwc.dao.EmployeeDao;

@Service("empService")
public class EmpServiceImpl implements IService {

	@Autowired
	private EmployeeDao empdao;
	
	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		return empdao.getEmployee(id);
	}

}
