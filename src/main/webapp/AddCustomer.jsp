<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
        function validate(element) {
                
                if(element.value == "" && element.name == "cname") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "email") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "password") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "contactNo") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "age") {
                    element.nextSibling.innerHTML=" This field is mandatory";
                    return false;
                }
                else if(element.value == "" && element.name == "gender") {
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
		
		<form action="CustomerServlet" method="post">
		<input type="hidden" name="process" value="addCustomer">
		
		<table style="text-align:  left;" class="table table-secondary">
		<tr>
		<th>Enter Customer name </th>
		<td><input type="text" name="cname" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Customer email </th>
		<td><input type="email" name="email" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter password </th>
		<td><input type="password" name="password" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Customer Contact No. </th>
		<td><input type="number" name="contactNo" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Customer age </th>
		<td><input type="number" name="age" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<th>Enter Customer gender</th>
		<td>Male <input type="radio" name="gender" value="male" onblur="validate(this)" class="validateMe"> Female <input type="radio" name="gender" value="female" onblur="validate(this)" class="validateMe"><span style="color: red;"> *</span></td>
		</tr>
		
		<tr>
		<td><input type="reset" value="Clear" class="btn btn-outline-warning"></td>
		<td><input type="submit" value="Add" onclick="return validateAll()" class="btn btn-outline-success"></td>
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