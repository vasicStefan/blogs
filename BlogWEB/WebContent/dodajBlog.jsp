<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	aaaaaaaaaaaaaaaaaa
	<form action="BlogServlet" method="POST"></br>
	teext: <input type="text" name="text"></br>
	telikesext: <input type="text" name="likes">
	<select  name="katId"></br>
		<c:forEach items="${kategorije}" var="i">
			<option value="${i.id}">${i.naziv}</option>


		</c:forEach>
		</select>
		<input type="submit" value="Save">
	</form>
	</br>
	<a href="/BlogWEB/pretrazi.jsp"> Pretrazi blogove</a>
</body>
</html>