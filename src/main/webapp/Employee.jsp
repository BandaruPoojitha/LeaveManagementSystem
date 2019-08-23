<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <% if(request.getAttribute("Error")!=null && request.getAttribute("Error").equals("Error Occured")) {
    out.print("Your Leave Cannot be Applied....Sorry!!!!!!\n");
    }%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="EmployeeOperations?value=1" target="iframe_a1" />Apply for Leave</a><br/>
<a href="EmployeeOperations?value=2" target="iframe_a1" />Grant Leave</a><br/>
<a href="EmployeeOperations?value=3" target="iframe_a1" />Cancel Leave</a><br/>
<a href="EmployeeOperations?value=4" target="iframe_a1" />CheckStatus/TrackMyleave</a><br/>
<a href="EmployeeOperations?value=5" target="iframe_a1" />Leave Balance</a>
</body>
</html>