<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Vaccine</title>
</head>
<body>
	<form action="assignvaccine" method="post">
		<table border="1">
			<tr>
				<th>Patient Id:</th>
				<td align="center">${patientid}<input type="hidden" name="patientid" value="${patientid}" /></td>
				
			</tr>
			<tr>
				<th>Hospital:</th>
				<td align="center">${username}</td>
			</tr>
		</table>
		<table border="1">
                    <tr>
                        <th>Inventory id</th>
                        <th>Vaccine id</th>
                        <th>Vaccine name</th>
                        <th>Quantity</th>
                        <th>User</th>
                        <th>Assign Vaccine</th>
                    </tr>
                    
                    <c:forEach items = "${inventoryList}" var="inventory" >
                    <tr>
                        <td align="center">${inventory.id}</td>
                        <td align="center">${inventory.vaccineid}</td>
                        <td align="center">${inventory.vaccinename}</td>
                        <td align="center">${inventory.quantity}</td>
                        <td align="center">${inventory.user}</td><br>
                        <td align="center"><input type="checkbox" name="assignvaccine" value="${inventory.id}" /></td><br>
                    </tr>
                    </c:forEach>
                </table>
		<input type="submit" name="submit" value="Assign Vaccine" />
	</form>
</body>
</html>