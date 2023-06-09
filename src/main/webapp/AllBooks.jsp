<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String login=(String)session.getAttribute("login");
if(login==null){
	request.setAttribute("errorMsg", "Please login to have a better experience!!");
}
%>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Message.jsp"></jsp:include>
<table style="text-align: left;;" class="table table-tertiary">
		<tr>
		<th>Book</th><th>Author</th><th>Language</th><th>Price</th><th colspan="1"></th>
		</tr>
		<tr>
		<c:forEach items="${blist }" var="b">
		<tr>
		<td><c:out value="${b.bookName }"></c:out> </td>
		<td><c:out value="${b.author }"></c:out> </td>
		<td><c:out value="${b.lang }"></c:out> </td>
		<td><c:out value="${b.price }"></c:out> </td>
		<%if(login!=null && login.equals("customer")){ %>
		<td><a class="btn btn-outline-secondary" href="CartServlet?process=addToCart&bookId=${b.bookId}">Add to Cart</a></td>
		<%} %>
		<%if(login!=null && login.equals("admin")){ %>
		<td><a class="btn btn-outline-secondary" href="BookServlet?process=updateBook&bookId=${b.bookId}">Update</a></td>
		<td><a class="btn btn-outline-warning" href="BookServlet?process=deleteBook&bookId=${b.bookId}" onclick="return confirm('Are yoy sure you want to delete ?')">Delete</a></td>
		<%} %>
		</tr>
		</c:forEach>
		</tr>
		</table>

		<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>