<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
</head>
<body>
<h1>Simple Employee Management</h1>
<form method="POST" action="ManageEmployees">
	<table>
	
		<tr>
		<td>Employee ID:</td>
		<td><input type="text" id="eid" name="eid" required placeholder="Enter unique id" /></td>
		</tr>
		
		<tr>
		<td>Name:</td>
		<td><input type="text" id="ename" name="ename" required placeholder="Enter name of the employee" /></td>
		</tr>	
		
		<tr>
		<td>Age:</td>
		<td><input type="number" id="age" name="age" min="18" max="65"  required /></td>
		</tr>	
		
		<tr>
		<td>Phone Number:</td>
		<td><input type="text" id="phno" name="phno" required placeholder="Enter phone number" /></td>
		</tr>	
		
		<tr>
		<td>Email:</td>
		<td><input type="email" id="email" name="email" required placeholder="Eg. abc@yahoo.com" /></td>
		</tr>	
		
		<tr>
		<td>Department: </td>
		<td><input type="text" id="dept" name="dept" required placeholder="Enter department name" /></td>
		</tr>	
		
		<tr>
		<td>
			<input type="submit" value="Add Employee" name="addemployee" id="addemployee" /> 
		</td>
		</tr>
	</table>
</form>
<h2>Inserted Data</h2>


<% 
ArrayList<String> reqdata = (ArrayList<String>)request.getAttribute("data");
if(reqdata != null) {
%>

<h3>Employee ID: <%= reqdata.get(0) %></h3>
<h3>Name: <%= reqdata.get(1) %></h3>
<h3>Age: <%= reqdata.get(2) %></h3>
<h3>Phone: <%= reqdata.get(3) %></h3>
<h3>Email: <%= reqdata.get(5) %></h3>
<h3>Department: <%= reqdata.get(4) %></h3>

<% } %>


<form method="POST" action="ManageEmployees">
<h1>Employee Details</h1>
<input type="submit" name="displayemp" id="displayemp" value="Click to view" />
</form>

<% 
ArrayList<ArrayList<String>> employees = (ArrayList<ArrayList<String>>)request.getAttribute("Employees");
if(employees != null ) {
System.out.println(employees.size());
%>
<table border="1" cellspacing="1" >
	<tr><td>ID</td>
	<td>Name</td>
	<td>Age</td>
	<td>Phone</td>
	<td>Email</td>
	<td>Department</td>
	</tr>

<%

for(int i=0;i<employees.size();i++) {
System.out.println(employees.get(i).get(0));
%>
<tr>
<td><%= employees.get(i).get(0) %></td>
<td><%= employees.get(i).get(1) %></td>
<td><%= employees.get(i).get(2) %></td>
<td><%= employees.get(i).get(3) %></td>
<td><%= employees.get(i).get(4) %></td>
<td><%= employees.get(i).get(5) %></td>
</tr>

<% }
}
%>
</table>
</body>
</html>