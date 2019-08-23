<%@ page language="java" contentType="text/html; charset=UTF-8"
   import="java.util.*" import="beanclasses.*"  pageEncoding="UTF-8"%>
    <%
	ArrayList<String> al1 = (ArrayList<String>) session.getAttribute("ids");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
<td>ManagerId
</td>
<td>DepartmentId
</td>
</tr><%for(int i=0;i<al1.size();i++){ %>

<tr>
<td>
<%
out.print(al1.get(i));
%>
</td>
<td>
<%
out.print(al1.get(i+1));
%>
</td>
</tr>
<%
i++;} 
session.removeAttribute("ids");%>
</table>
</body>
</html>