package com.hu.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.ssm.bean.Dept;
import com.hu.ssm.bean.Emp;
import com.hu.ssm.service.EmpService;
import com.hu.ssm.util.PageUtil;

@Controller
public class EmpController {
	@Autowired
	private EmpService empService;

	@RequestMapping(value = "/emps/{pageNum}", method = RequestMethod.GET)
	public String getAllEmp(Map<String, Object> map, @PathVariable("pageNum") Integer pageNum,
			HttpServletRequest request) {
		// 通过PageHelper来设置页面信息，第一个参数为页数，第二个参数为每页显示的条数
		PageHelper.startPage(pageNum, 7);
		List<Emp> allEmp = empService.getAllEmp();
		// PageInfo中封装了分页相关的所有信息，当前页的页数，上一页的页数，下一页的页数，总页数，总条数...
		PageInfo<Emp> pageInfo = new PageInfo<Emp>(allEmp, 5);
		String page = PageUtil.getPageInfo(pageInfo, request);
		map.put("page", page);
		map.put("allEmp", allEmp);
		return "list";
	}

	@RequestMapping(value = "/emp/{eid}", method = RequestMethod.GET)
	public String toUpdate(Map<String, Object> map, @PathVariable("eid") String eid) {
		// 要修改的员工信息
		Emp empByEid = empService.getEmpByEid(eid);
		// 所以的部门信息
		List<Dept> allDept = empService.getAllDept();
		// 获取存储性别的map集合
		Map<String, String> sex = new HashMap<String, String>();
		sex.put("男", "男");
		sex.put("女", "女");
		map.put("sex", sex);
		map.put("allDept", allDept);
		map.put("empByEid", empByEid);

		return "update";
	}

	@RequestMapping(value = "/emp", method = RequestMethod.PUT)
	public String updateEmp(Emp emp) {
		System.out.println(emp);
		empService.updateEmp(emp);
		return "redirect:/emps/1";
	}

	@RequestMapping(value = "/emps", method = RequestMethod.DELETE)
	public String deleteMoreEmp(String ck) {
		// 获取客户端name属性相同的多个元素值的值，可以通过字符串直接获取，每个值中间以逗号分隔
		// 也可以以数组直接获取  String[] ck System.out.println(Arrays.tpString(ck))
		System.out.println(ck);
		if (ck == null || ck == "") {
			return "redirect:/emps/1";
		} else {
			empService.deleteMoreEmp(ck);
			return "redirect:/emps/1";
		}
	}

	@RequestMapping(value = "/emp/{eid}", method = RequestMethod.DELETE)
	public String deleteSingleEmp(@PathVariable("eid") String eid) {
		empService.deleteEmpByEid(eid);

		return "redirect:/emps/1";
	}

}
