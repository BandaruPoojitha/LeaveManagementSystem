<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   import="java.util.*,beanclasses.*" pageEncoding="ISO-8859-1"%>
    <%ArrayList<Integer>  al=(ArrayList<Integer>)session.getAttribute("leavebal");
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
<td>PaidLeaves
</td>
<td>LOPLeaves
</td>
</tr>
<tr>
<td>
<%
out.print(al.get(1));
%>
</td>
<td>
<%
out.print(al.get(0));
%>
</td>
</tr>
</table>
<%session.removeAttribute("leavebal"); %>

</body>
</html>