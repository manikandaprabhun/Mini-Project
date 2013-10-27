<%@page
	import="java.util.List,com.ebix.domain.Cats,java.util.Set,com.ebix.domain.Projects"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Products</title>
</head>
<body>
	<fieldset style="width: 250px">
		<legend>Products:</legend>
		<table>
			<tr>
				<th>Delete</th>
				<th>Name</th>
				<th>Category</th>
			</tr>
			<c:forEach var="project" items="${requestScope.projects}">
				<tr>
					<td><a href="cont.do?act=delete&&id=${project.id}"><img
							src="/serv-jsp-hibernate/images/delete.ico"
							alt="Delete the Product" height="13px" width="13px"></a></td>
					<td><a href="cont.do?act=updateform&&id=${project.id}"> <label>${project.name}</label>
					</a></td>
					<td><select name="catgry">
							<c:forEach var="cat" items="${requestScope.cats}">
								<c:choose>
									<c:when test="${cat eq  project.cats}">
										<option selected="selected" value="${cat.id}">${cat.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${cat.id}">${cat.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select></td>
				</tr>
			</c:forEach>
		</table>
	</fieldset>
	<%@ include file="/jsp/newproduct.jsp"%>
</body>
</html>