<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String login=(String)session.getAttribute("login");
%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="BookServlet?process=allBooks">BOOKS</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="Index.jsp">Home</a>
        </li>
        <% if(login!=null && login.equals("admin")){ %>
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="AddBook.jsp">Add Book</a>
        </li>
        <%} %>
        <% if(login==null){ %>
        <li class="nav-item"><a class="nav-link" href="Login.jsp">Login</a></li>
        <li class="nav-item"><a class="nav-link" href="AddCustomer.jsp">Register</a></li>
        <%} %>
        	<% if(login!=null){ %>
        	<% if(login!=null && login.equals("admin")){ %>
        	<li class="nav-item"><a class="nav-link active" href="CartServlet?process=allCart">Inventory</a></li>
        	<%} %>
			<% if(login!=null && login.equals("customer")){ %>
			<li class="nav-item"><a class="nav-link active" href="CartServlet?process=myCart">MyInventory</a></li>
			<li class="nav-item"><a class="nav-link active" href="CustomerServlet?process=myProfile">My Profile</a></li>
			<%} %>
			<li class="nav-item"><a class="nav-link" onclick="return confirm('Are you sure you want to logout?')" href="LoginServlet">logout</a></li>
			<%} %>
      </ul>
      	<form class="d-flex" role="search" action="BookServlet" method="post">
      	<input type="hidden" name="process" value="searchBook">
        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-outline-success" type="submit" >Search</button>
      </form>
    </div>
  </div>
</nav>
</body>
</html>