<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Page</title>
</head>
<body>
<h1>Edit Employee Details</h1>
<form method="POST" action="ManageEmployees">
<label for="eid" >Enter Employee ID:</label>

<input type="text" name="eid" id="eid" required placeholder="Enter unique id" />
<input type="submit" name="editemp" id="editemp" value="Get Emplopyee"/>

</form>
<% String msg = (String)request.getAttribute("Messageup"); 
	if(msg != null) {
		%>
<h4 style="color:red;"><%=msg %></h4>
<%

	}
%>

<% ArrayList<String> empeditdata = (ArrayList<String>)request.getAttribute("empeditdata");
	if(empeditdata != null && empeditdata.size() == 6) {
//	System.out.println(empeditdata);
	System.out.println("In edit page"+empeditdata);
%>
<form method="POST" action="ManageEmployees">
<table>
		<tr>
		<td>Employee ID:</td>
		<td><input type="text" id="eid" name="eid" value=<%= empeditdata.get(0) %> /></td>
		</tr>
		
		<tr>
		<td>Name:</td>
		<td><input type="text" id="ename" name="ename" value=<%= empeditdata.get(1) %> /></td>
		</tr>	
		
		<tr>
		<td>Age:</td>
		<td><input type="number" id="age" name="age" min="18" max="65" value=<%= empeditdata.get(2) %> /></td>
		</tr>	
		
		<tr>
		<td>Phone Number:</td>
		<td><input type="text" id="phno" name="phno" value= <%=   empeditdata.get(3) %> /></td>
		</tr>	
		
		<tr>
		<td>Email:</td>
		<td><input type="email" id="email" name="email" value= <%= empeditdata.get(4) %> /></td>
		</tr>	
		
		<tr>
		<td>Department: </td>
		<td><input type="text" id="dept" name="dept" value=<%= empeditdata.get(5) %> /></td>
		</tr>	
		
		<tr>
		<td>
			<input type="submit" value="Update Employee" name="updateemployee" id="updateemployee" /> 
		</td>
		</tr>
	</table>
	
	
</form>
<%

	}
	else {
		
	
	%>
	<h4 style="color:red;">Employee not found</h4>
	<%
	}
	%>
<h1>Delete Employee Details</h1>
<form method="POST" action="ManageEmployees">
<label for="eid" >Enter Employee ID:</label>

<input type="text" name="eid" id="eid" required placeholder="Enter unique id" />
<input type="submit" name="deleteemp" id="deleteemp" value="Delete"/>

</form>
<% String msgdel = (String)request.getAttribute("Message"); 
	if(msgdel != null) {
	
		%>
<h4 style="color:red;"><%= request.getAttribute("Message")  %></h4>
<%

	}
%>


</body>
</html>