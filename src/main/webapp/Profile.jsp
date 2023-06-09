<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function updateGender(element) {
		var g = element.value;
		document.getElementById("custGender").value = g;
	}
	function validate(element) {

		if (element.value == "" && element.name == "cname") {
			element.nextSibling.innerHTML = " This field is mandatory";
			return false;
		} else if (element.value == "" && element.name == "email") {
			element.nextSibling.innerHTML = " This field is mandatory";
			return false;
		} else if (element.value == "" && element.name == "password") {
			element.nextSibling.innerHTML = " This field is mandatory";
			return false;
		} else if (element.value == "" && element.name == "contactNo") {
			element.nextSibling.innerHTML = " This field is mandatory";
			return false;
		} else if (element.value == "" && element.name == "age") {
			element.nextSibling.innerHTML = " This field is mandatory";
			return false;
		} else {
			element.nextSibling.innerHTML = "*";
		}
	}

	function validateAll() {
		var a = document.getElementsByClassName("validateMe");
		var flag = true;

		for (var i = 0; i < a.length; i++) {
			var element = a[i];
			flag = validate(element);
			if (flag == false)
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
		<input type="hidden" name="process" value="editCustomer">
		<input type="hidden" name="cId" value="${custObj.cId }">

		<table style="text-align: left;" class="table table-tertiary">
		<tr>
		<th>Customer name</th>
		<td><input type="text" name="cname" value="${custObj.cname }"><span style="color: red;">*</span></td>
		</tr>

		<tr>
		<th>Customer email</th>
		<td><input type="email" name="email" value="${custObj.email }"><span style="color: red;">*</span></td>
		</tr>

		<tr>
		<th>Customer password</th>
		<td><input type="text" name="password" value="${custObj.password }"><span style="color: red;">*</span></td>
		</tr>

		<tr>
		<th>Customer contactNo</th>
		<td><input type="number" name="contactNo" value="${custObj.contactNo }"><span style="color: red;">*</span></td>
		</tr>

		<tr>
		<th>Customer age</th>
		<td><input type="number" name="age" value="${custObj.age }"><span style="color: red;"> *</span></td>
		</tr>

		<tr>
		<th>Enter customer gender</th>
		<td><input type="text" name="gender" id="custGender" readonly="readonly" value="${custObj.gender }"> 
		<select onchange="updateGender(this)">
		<option>Click</option>
		<option>Male</option>
		<option>Female</option>
		</select></td>
		</tr>

		<tr>
		<td><input type="reset" value="Clear" class="btn btn-outline-warning"></td>
		<td><input type="submit" value="Update" class="btn btn-outline-success"></td>
		<td><a href="CustomerServlet?process=deleteProfile&cId=${custObj.cId }"class="btn btn-danger" onclick="return confirm('Are you sure you want to delete your account')">Delete my profile</a></td>
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