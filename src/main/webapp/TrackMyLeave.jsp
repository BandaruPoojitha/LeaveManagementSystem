<%@page import="java.time.temporal.ChronoUnit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,beanclasses.*" 
	pageEncoding="ISO-8859-1"%>
<% 	ArrayList<String>  ld=(ArrayList<String>)session.getAttribute("track");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr><td>sno</td>
			<td>EmployeeId</td>
			<td>LeaveType</td>
			<td>startdate</td>
			<td>enddate</td>
			<td>applyTo</td>
			<td>Reason</td>
			<td>status</td>
		</tr>
<% 
//session.setAttribute("sizeal",al1.size());
//out.println(al1.size()+"size");
for (int i = 0; i < ld.size(); i++) {
					
						out.println("<tr><td>" + ld.get(i+7) + "</td><td>"+ ld.get(i) + "</td><td>" + ld.get(i+1) + "</td><td>"
								+ ld.get(i+2) + "</td><td>" + ld.get(i+3) + "</td><td>" + ld.get(i+4)
								+ "</td><td>" + ld.get(i+5) + "</td><td>" + ld.get(i+6) + "</td>");
i=i+7;
}
session.removeAttribute("track");
%>
</body>
</html>