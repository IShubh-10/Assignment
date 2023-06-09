
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<table  class="table table-tertiary" style="text-align: left;">
<tr>
<th>Cart Id</th><th>Book</th><th>Price of Book</th><th>No.of Books</th><th>Sub-total</th><th>User Email</th><th>Action</th>
</tr>

<c:forEach var="c" items="${clist }">
<tr>
<td>${c.cartId }</td>
<td>-${c.b.bookName }</td>
<td>${c.price }</td>
<td>${c.quantity }</td>
<td>${c.subtotal}</td>
<td>${c.email}</td>
<% if(login!=null && login.equals("admin")){ %>
<td><a class="btn btn-secondary" href="CartServlet?process=deleteCartItem&cartId=${c.cartId }">Delete</a></td>
<%} %>
<% if(login!=null && login.equals("customer")){ %>
<td><a class="btn btn-secondary" href="#">Buy</a></td>
<%} %>
</tr>
</c:forEach>

</table>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>