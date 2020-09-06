package com.hu.ssm.mapper;

import java.util.List;

import com.hu.ssm.bean.Emp;

public interface EmpMapper {

	// 获取所有的员工信息
	List<Emp> getAllEmp();

	// 根据eid获取员工信息
	Emp getEmpByEid(String eid);

	// 修改员工信息
	void updateEmp(Emp emp);
	
	//根据eid删除某一个员工信息
	void deleteEmpByEid(String eid);
	
	//批量删除员工信息
	void deleteMoreEmp(String eids);

}
