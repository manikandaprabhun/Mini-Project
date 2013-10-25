<%@page import="com.ebix.domain.Cats"%>
<%@page import="java.util.List"%>
<form action="cont.do" method="post">
	<fieldset title="Login" style="width: 250px">
		<legend>Add New Product:</legend>
		<p>
			<label>Name &nbsp;</label><input type="text" name="name"> </br> </br> <label>Category
				&nbsp;&nbsp;</label><select name="catgry">
				<%
					List<Cats> cats = (List<Cats>) request.getAttribute("cats");
					for (Cats cat : cats) {
				%>
				<option value="<%=cat.getId()%>"><%=cat.getName()%></option>
				<%
					}
				%>
			</select> <input type="hidden" name="act" value="newproduct">
		</p>
		<p>
			<input type="submit" name="submit">
		</p>
	</fieldset>
</form>