<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改页面</title>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath }/css/table.css">
</head>
<body>
	<form:form action="${pageContext.servletContext.contextPath }/emp"
		method="post" modelAttribute="empByEid">
		<form:hidden path="eid" />
		<input type="hidden" name="_method" value="PUT" />
		<table>
			<tr>
				<th colspan="2">修改员工信息</th>
			</tr>
			<tr>
				<td>Ename</td>
				<td><form:input path="ename" /></td>
			</tr>
			<tr>
				<td>Age</td>
				<td><form:input path="age" /></td>
			</tr>
			<tr>
				<td>Sex</td>
				<td><form:radiobuttons path="sex" items="${sex }" /></td>
			</tr>
			<tr>
				<td>部门</td>
				<td><form:select path="dept.did" items="${allDept }"
						itemLabel="dname" itemValue="did" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="修改" /></td>
			</tr>

		</table>
	</form:form>
</body>
</html>