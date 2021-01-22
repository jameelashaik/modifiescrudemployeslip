<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addemp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    This is my JSP page. <br>
	<form action="addEmployee" method="post" align="center">

		Name:<input type="text" name="user" pattern="[A-Za-z]{4,12}"
			title="name must be between 4 and 12 charecters length and contains only letters and numbers"
			required><br> <br>
		Designation: <input type="text" name="designation" pattern="[A-Za-z]{1,50}"
			title="contains only letters and numbers"
			required><br> <br>
		address: <input type="textarea" name="address" pattern="[A-Za-z]{1,50}"
			title="contains only letters and numbers"
			required><br> <br>	
		mobileNumber: <input type="text" name="mobilenumber" ><br> <br>
		emailId: <input type="text" name="emailid" ><br> <br>
	 TechnicalSkills: <input type="checkbox" id="vehicle1" name="technicalskills" value="java">
	  <label for="vehicle1"> java</label><br>
	  <input type="checkbox" id="vehicle2" name="technicalskills" value="python">
	  <label for="vehicle2"> python</label><br>
	  <input type="checkbox" id="vehicle3" name="technicalskills" value="c">
	  <label for="vehicle3"> c</label><br><br>				
		 dob:<input type="date"
			name="dob" max="2000-12-31" min="1970-01-01"
			title="DOB should not be greater than 50 years of age with current date"
			required><br> <br>
		 Qualification:<select
			name="qualification">
			<option value="" name="qualification">Select</option>
			<option value="B.Tech" name="qualification">btech</option>
			<option value="B.com" name="qualification">bcom</option>
			<option value="bsc" name="qualification">bsc</option>
			<option value="other" name="qualification">other</option>


		</select> <br><br>
		 Gender: <input type="radio" id="male" name="gender"
			value="male" required>
			 <label for="male">Male</label><br><br>
		<input type="radio" id="female" name="gender" value="female" required>
		<label for="female">Female</label><br><br> 
		<input type="radio" id="other" name="gender" value="other" required> <label  for="other">Other</label><br><br>
		 Date of joining: <input
			type="date" min="1970-01-01" name="joiningdate"
			title="DOJ should not be less than 50 years of age with current date"
			required> <br><br>
		Pancard :<input type="text" name="pancard"
			pattern="[A-Z\d]{10}" title="incorrect pan card number" required><br><br>
		<input type="submit" value="submit">
	</form>
  </body>
</html>
