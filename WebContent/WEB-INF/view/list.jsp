<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="${pageContext.servletContext.contextPath }/">
<meta charset="UTF-8">
<title>展示员工信息</title>
<link rel="stylesheet" href="css/table.css">
<script type="text/javascript" src="js/jquery-3.5.0.js"></script>
<script type="text/javascript">
	$(function() {
		$("#selectAll").click(function() {
			$("[name='ck']").prop("checked", $(this).prop("checked"));
		});

		$("#deleteMore").click(function() {
			if (confirm("确认删除所选项吗？")==false) {
				return false;
			}
			$(".delMore").attr("action", $(this).attr("href")).submit();
			return false;
		})
		
		$("#deleteSingle").click(function() {
			var empName=$(this).parents("tr").children().next("td").next("td").html();
			if (confirm("确认删除"+empName+"吗？")==false) {
				return false;
			}
			$(".delSingle").attr("action", $(this).attr("href")).submit();
			return false;
		})
	})
</script>
</head>
<body>
	<form class="delMore" method="post">
		<input type="hidden" name="_method" value="delete" />
		<table>
			<tr>
				<th><input type="checkbox" id="selectAll"></th>
				<th>Eid</th>
				<th>Ename</th>
				<th>Age</th>
				<th>Sex</th>
				<th>DepartmentName</th>
				<th>Option</th>
			</tr>
			<c:forEach items="${allEmp }" var="emp">
				<tr>
					<td><input type="checkbox" name="ck" value="${emp.eid }" /></td>
					<td>${emp.eid }</td>
					<td>${emp.ename }</td>
					<td>${emp.age }</td>
					<td>${emp.sex }</td>
					<td>${emp.dept.dname }</td>
					<td><a id="deleteSingle" href="emp/${emp.eid }">删除</a> <a href="emp/${emp.eid }">修改</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="10" align="center"><a id="deleteMore" href="emps">批量删除</a>&nbsp;&nbsp;
					${page }</td>
			</tr>
		</table>
	</form>

	<form method="post" class="delSingle">
		<input type="hidden" name="_method" value="delete" />
	</form>

</body>
</html>