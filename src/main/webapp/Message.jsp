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
String msg=(String)request.getAttribute("msg");
String errorMsg=(String)request.getAttribute("errorMsg");
%>

<% if(msg!=null){ %>
<marquee direction="right" style="text-align: center;"><span class="badge bg-secondary">${ msg}</span></marquee>
<%}else if(errorMsg!=null){ %>
<marquee direction="right" style="text-align: center;"><span class="badge bg-secondary">${ errorMsg}</span></marquee>
<%} %>
</body>
</html>