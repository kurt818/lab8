<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Create User</title>
			<style type="text/css">
            div#list{height:300px;width:100px;float:left;text-decoration: underline;color: blue}
            div#createuser{height:300px;width:450px;float:left} 
            </style>
            
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<div class = "container">
<div class="demo-type-example col-sm-3">
		<div class="demo-heading-note row" id="list">
            <a href="logout">Logout</a><br><br>
        </div>
        </div>
        <div id="createuser">
			<form action="createuser" method="post">
			<p1>Please create a user:</p1><br /><br />
			<table  class="table table-striped">
			</tbody>
			<tr><td>User Name: </td><td colspan="2"><input type="text" name="username" placeholder="username" /></td></tr>
			<tr><td>Password: </td><td colspan="2"><input type="text" name="password" placeholder="password" /></td></tr>
			<tr><td>Institution: </td><td colspan="2"><input type="text" name="from" placeholder="institution" /></td></tr>
			<tr><td>Role: </td><td colspan="2"><select name="role">
				<option value="CDC">CDC</option>
				<option value="Hospital">Hospital</option>
			</select></td></tr>
			</tbody>
			</table>
			  
			<input type="submit" value="create user" />
	</form>
	</div>
	</div>
</body>
</html>