<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
        function validate(element) {
                
                if(element.value == "" && element.name == "bookName") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "author") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "lang") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "price") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "quantity") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else{
                    element.nextSibling.innerHTML="*";
                }
            }
        
        function validateAll() {
            var a = document.getElementsByClassName("validateMe");
            var flag = true;
            
            for(var i=0; i<a.length; i++) {
                var element = a[i];
                flag = validate(element);
                if(flag==false)
                    break;
        }
        return flag;
    }
    </script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<jsp:include page="Message.jsp"></jsp:include>
		
		<form action="BookServlet" method="post">
		<input type="hidden" name="process" value="addBook">
		
		<table style="text-align: left;" class="table table-secondary">
		<tr>
		<th>Enter Boook name </th>
		<td><input type="text" name="bookName" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Author name </th>
		<td><input type="text" name="author" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Language </th>
		<td><input type="text" name="lang" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Price </th>
		<td><input type="number" name="price" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		
		<tr>
		<td><input type="reset" value="Clear" class="btn btn-outline-warning"></td>
		<td><input type="submit" value="Add" onclick="return validateAll()" class="btn btn-outline-secondary"></td>
		</tr>
		</table>
		</form>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>