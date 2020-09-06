package com.hu.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hu.ssm.bean.Dept;
import com.hu.ssm.bean.Emp;
import com.hu.ssm.mapper.DeptMapper;
import com.hu.ssm.mapper.EmpMapper;
import com.hu.ssm.service.EmpService;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;

	@Autowired
	private DeptMapper deptMapper;

	@Override
	public List<Emp> getAllEmp() {
		return empMapper.getAllEmp();
	}

	@Override
	public Emp getEmpByEid(String eid) {
		return empMapper.getEmpByEid(eid);
	}

	@Override
	public void updateEmp(Emp emp) {
		empMapper.updateEmp(emp);
	}

	@Override
	public List<Dept> getAllDept() {
		return deptMapper.getAllDept();
	}

	@Override
	public void deleteEmpByEid(String eid) {
		empMapper.deleteEmpByEid(eid);
	}

	@Override
	public void deleteMoreEmp(String eids) {
		empMapper.deleteMoreEmp(eids);
	}

}
