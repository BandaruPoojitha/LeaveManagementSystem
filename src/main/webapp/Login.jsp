<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (request.getAttribute("Error") != null && request.getAttribute("Error").equals("Error Occured")) {
		out.print("Error Occured,,Please Login with  Valid Details!!");
	}
%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#f1 {
	width: 200px;
	height: 300px;
	padding:40px;
	background-color:"red";
}
#b{
backgroud-color:blue;

}
</style>

<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body id="b">
	<center>
		<fieldset id="f1">
			<table>
				<form action="Log" method="post">
					<tr>
						<td>Username</td>
						<td><input type="text" name="Username" /><br /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="Password" /><br /></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="submit" /></td>
					</tr>
				</form>
			</table>
		</fieldset>
	</center>
</body>
</html>