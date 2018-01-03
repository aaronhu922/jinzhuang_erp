<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>test</title>
<%@include file="common/head.jsp"%>
<link rel='stylesheet' href='<c:url value="/resources/css/style.css" />'
	type='text/css' media='all' />
</head>
<body>
	<fieldset>
		<legend>User Input From</legend>
		<form:form action="saveUser" method="post" modelAttribute="user">
			<table>
				<tr>
					<th>登录名</th>
					<td><form:input path="userName" /> <form:errors path="userName"
							cssClass="error" /></td>
					<th>电话</th>
					<td><form:input path="phone" /> <form:errors path="phone"
							cssClass="error" /></td>
							<th>密码</th>
					<td><form:input path="password" /> <form:errors path="password"
							cssClass="error" /></td>
							<th>角色</th>
					<td><form:input path="roleName" /> <form:errors path="roleName"
							cssClass="error" /></td>
					<td><button type="submit">Submit</button></td>
				</tr>
			</table>
		</form:form>
	</fieldset>
	<br>
	<br>

	<fieldset>
		<legend>Users List</legend>
		<table class="resltTable">
			<tr>
				<th>Name</th>
				<th>Phone</th>
				<th>Password</th>
				<th>Role</th>
			</tr>
			<c:forEach items="${users}" var="user">
				<tr>
					<td><c:out value="${user.userName}">No name</c:out></td>
					<td>${user.phone}</td>
					<td>${user.password}</td>
					<td>${user.roleName}</td>
				</tr>
			</c:forEach>

		</table>
	</fieldset>

</body>
</html>