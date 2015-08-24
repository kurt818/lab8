<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search for User</title>
</head>
	<body>
	<h1>Searching for member: ${user.username}</h1>
		<form method="post" action="add">
				<table border="1">
                    <tr>
                        <th>User Name</th>
                        <th>Gender</th>
                        <th>City/State</th>
                        <th>Country</th>
                        <th>About User</th>
                        <th>Add to contact list</th>
                    </tr>
                    <tr>
                        <td align="center">${user.username}<input type="hidden" name="adduser" value="${user.username}" /></td>
                        <td align="center">${user.gender}</td>
                        <td align="center">${user.city}/${user.state}</td>
                        <td align="center">${user.country}</td>
                        <td align="center">${user.aboutme}</td>
                        <td align="center"><input type="submit" name="submit" value="Add to contact list"/></td>
                    </tr>
                </table>
			</form>
		</body>
</html>