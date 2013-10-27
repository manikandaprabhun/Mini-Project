<%@page import="com.ebix.domain.Cats,java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form action="cont.do" method="post">
	<fieldset title="Login" style="width: 250px">
		<c:choose>
			<c:when test="${empty upProd}">
				<legend>Add New Product:</legend>
				<p>
					<label>Name &nbsp;</label> <input type="text" name="name">
					</br> </br> <label>Category &nbsp;&nbsp;</label> <select name="catgry">
						<c:forEach var="cat" items="${requestScope.cats}">
							<option value="${cat.id}">${cat.name}</option>
						</c:forEach>
					</select> <input type="hidden" name="act" value="newproduct">
				</p>
			</c:when>
			<c:otherwise>
				<legend>update Product:</legend>
				<p>
					<label>Name &nbsp;</label> <input type="text" name="name"
						value="${upProd.name}"> </br> </br> <label>Category
						&nbsp;&nbsp;</label> <select name="catgry">
						<c:forEach var="cat" items="${requestScope.cats}">
							<c:choose>
								<c:when test="${cat eq  upProd.cats}">
									<option selected="selected" value="${cat.id}">${cat.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${cat.id}">${cat.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select> <input type="hidden" name="id" value="${upProd.id}"> <input
						type="hidden" name="act" value="update">
				</p>
			</c:otherwise>
		</c:choose>
		<p>
			<input type="submit" name="submit">
		</p>
	</fieldset>
</form>