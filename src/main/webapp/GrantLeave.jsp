<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*" import="beanclasses.*" pageEncoding="ISO-8859-1"%>
<%
	ArrayList<String> ld = (ArrayList<String>) session.getAttribute("ids");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
		<td>sno</td>
			<td>EmployeeId</td>
			<td>LeaveType</td>
			<td>startdate</td>
			<td>enddate</td>
			<td>applyTo</td>
			<td>Reason</td>
			<td>status</td>
		</tr>
		<form action="UpdateStatus" method="post">
			<%!int i = 0;%>
			<%
				//session.setAttribute("sizeal", al1.size());
				//out.println(al1.size() + "size");
				for (i = 0; i < ld.size(); i++) {
					

					if (ld.get(6).equals("processing")) {
						out.println("<tr><td>" + ld.get(i+7) + "</td><td>"+ ld.get(i) + "</td><td>" + ld.get(i+1) + "</td><td>"
								+ ld.get(i+2) + "</td><td>" + ld.get(i+3) + "</td><td>" + ld.get(i+4)
								+ "</td><td>" + ld.get(i+5) + "</td><td>" + ld.get(i+6) + "</td>");
						session.setAttribute("sno", ld.get(7));
			%>
			<td>Accept::<input id="<%out.print("A" + i);%>"
				name="JK<%=i%>"  type="radio" value="accept" />
				Reject:: <input id="<%out.print("B" + i);%>" name="JK<%=i%>"
				type="radio"  value="reject" /> 
				Forward::<input id="<%out.print("C" + i);%>" name="JK<%=i%>"
				type="radio"  value="forward" /> 


			</td>
			</tr>

			<% i=i+7;
				}
				}
			%>
			
	</table><input type="submit" />
		</form>
</body>
</html>