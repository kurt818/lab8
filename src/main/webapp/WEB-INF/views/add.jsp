<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add</title>
</head>
<body>
	<form action="addMember" method="post">
		<table border="1">
			<tr>
				<th>Contact to be Added:</th>
				<td>${contactName}<input type="hidden" name="contactName" value="${contactName}" /></td>
				
			</tr>
			<tr>
				<th>Message</th>
				<td>​<textarea name="message" id="message" rows="10" cols="20"></textarea></td>
			</tr>
		</table>
		<input type="submit" name="add" value="Add" /><br />
		<input type="reset" value="clear">
	</form>
</body>
</html>