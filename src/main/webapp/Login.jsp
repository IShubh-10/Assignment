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
		
		<form action="LoginServlet" method="post">
		<table style="text-align: center;" class="table table-tertiary">
		<tr>
		<th>Enter email id : </th>
		<td><input class="form-control-sm" type="email" name="username"></td>
		</tr>
		
		<tr>
		<th>Enter password : </th>
		<td><input class="form-control-sm"type="password" name="password"></td>
		</tr>
		
		<tr>
		<td><input type="reset" value="Clear" class="btn btn-outline-warning"></td>
		<td><input type="submit" value="Login" class="btn btn-outline-success"></td>
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