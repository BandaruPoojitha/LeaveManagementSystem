<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,beanclasses.*,java.time.*,java.text.*" import="leave.*" pageEncoding="ISO-8859-1"%>
<%
	ArrayList<String> ld = (ArrayList<String>) session.getAttribute("cancel");
%>

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
		<form action="UpdateStatus" method="get">
			<%
				
				for (int i = 0; i < ld.size(); i++) {
					
					System.out.println("   leave    " + ld.get(i+6));
				
						out.println("<tr><td>" + ld.get(i+7) + "</td><td>"+ ld.get(i) + "</td><td>" + ld.get(i+1) + "</td><td>"
								+ ld.get(i+2) + "</td><td>" + ld.get(i+3) + "</td><td>" + ld.get(i+4)
								+ "</td><td>" + ld.get(i+5) + "</td><td>" + ld.get(i+6) + "</td>");
						//session.setAttribute("sno", ld.get(7));
					
			%>
			<td>Cancel::
			
			<input id="<%out.print("A" + i);%>" type="radio"
				name="jk<%= i%>"  value="cancel" />
				
				Dont Cancel::<input
				id="<%out.print("B" + i);%>" type="radio" name="jk<%=i %>"
				value="dontcancel" /> 
				
			
				
			</td>
			<%
					
					i=i+7;
				}
			%></table>
			<input type="submit" />

		</form>
		
</body>
</html>