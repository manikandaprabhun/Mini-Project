<%@page import="java.util.List"%>
<%@page import="com.ebix.domain.Cats"%>
<%@page import="java.util.Set"%>
<%@page import="com.ebix.domain.Projects"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
			<%
				List<Projects> projects = (List<Projects>) request
						.getAttribute("projects");
				for (Projects project : projects) {
			%>
			<tr>
				<td><a href="cont.do?act=delete&&id=<%=project.getId()%>"><img src="/serv-jsp-hibernate/images/delete.ico"
					alt="Delete the Product" height="13px" width="13px""></a></td>
				<td><label><%=project.getName()%></label></td>
				<td><select name="catgry">
						<%
							Cats c = project.getCats();
								List<Cats> cats = (List<Cats>) request.getAttribute("cats");
								for (Cats cat : cats) {
						%>
						<option <%=(c.equals(cat)) ? " selected=\"selected\"" : ""%>
							value="<%=cat.getId()%>"><%=cat.getName()%></option>
						<%
							}
						%>
				</select></td>
			</tr>

			<%
				}
			%>
		</table>
	</fieldset>
	<%@ include file="/jsp/newproduct.jsp"%>
</body>
</html>