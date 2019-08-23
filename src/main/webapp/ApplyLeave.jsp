<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.time.LocalDate" %>
    <%session.setAttribute("value","1"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<form action="Employees" method="Post">
LeaveType::<select name ="LeaveType"><br/>
            <option value = "Paid" selected>Paid</option>
            <option value = "LOP">LOP</option>
            </select>
From date::<input type="date" name="startdate" value="<%= LocalDate.now() %>" placeholder="yyyy-mm-dd"><br/>
To date::<input type="date" name="enddate" placeholder="yyyy-mm-dd"/><br/>

Reason::<input type="text" name="reason"/><br>

<input type="submit" name="Apply"/>

</form>
</body>
</html>
