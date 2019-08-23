<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%session.setAttribute("value", "0"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="AdminOperations" method="post">
Employee Name::<input type="text" name="name"/><br/>
Employee Id::<input type="text" name="EmployeeId"/><br/>
Department Id::<input type="text" name="departmentId"/><br/>
ManagerId::<input type="text" name="managerId"/><br/>
Address::<input type="text" name="address"/><br/>
Email::<input type="text" name="Email"/></br/>
Contact number::<input type="number" name="phonenumber"/><br/>
<input type="submit" name="add" value="Add"/>
</form>
</body>
</html>