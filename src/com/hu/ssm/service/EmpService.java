package com.hu.ssm.service;

import java.util.List;

import com.hu.ssm.bean.Dept;
import com.hu.ssm.bean.Emp;

public interface EmpService {

	List<Emp> getAllEmp();

	Emp getEmpByEid(String eid);

	void updateEmp(Emp emp);

	List<Dept> getAllDept();
	
	void deleteEmpByEid(String eid);
	
	void deleteMoreEmp(String eids);

}
