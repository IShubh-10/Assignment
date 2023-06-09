<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
 <jsp:include page="Message.jsp"></jsp:include>
		
		<form action="BookServlet" method="post">
		<input type="hidden" name="process" value="editBook">
		<input type="hidden" name="bookId" value="${bookObj.bookId }">
		
		<table style="text-align: left;;" class="table table-secondary">
		<tr>
		<th>Enter Book name </th>
		<td><input type="text" name="bookName" value="${bookObj.bookName }"></td>
		</tr>
		
		<tr>
		<th>Enter Author name </th>
		<td><input type="text" name="author" value="${bookObj.author }"></td>
		</tr>
		
		<tr>
		<th>Enter Price </th>
		<td><input type="number" name="price" value="${bookObj.price }"></td>
		</tr>
		
		<tr>
		<th>Enter Language </th>
		<td><input type="text" name="lang" value="${bookObj.lang }"></td>
		</tr>
			
		
		<tr>
		<td><input type="reset" value="Clear" class="btn btn-outline-warning"></td>
		<td><input type="submit" value="Update" class="btn btn-outline-secondary"></td>
		</tr>
		</table>
		</form>
		
		</div>
		</div>
		</div>
		</section>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>