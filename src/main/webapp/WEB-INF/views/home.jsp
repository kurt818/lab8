<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix = "form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<!-- <h1>Welcome Lab 8, Please Login In:</h1> -->
<%-- <form:form method = "POST" commandName="user">
     <table>
         <tr>
              <td>Enter Your Name:</td>
              <td><form:input path="username"/></td>
              <td><form:errors path="username" cssStyle="color:#ff0000"></form:errors>
         </tr>
         <tr>
             <td>Enter Your Password</td>
             <td><form:password path="password"/></td>
             <td><form:errors path="password" cssStyle="color:#ff0000"/></td>
         </tr>
         <tr>
             <td><input type = "submit" name="submit" value="Submit"/></td>
         </tr>
         
     </table>
</form:form> --%>
<h1>Please Login</h1>
        <form:form method="post" commandName="user" action="login">
        <!-- <form method="post" action="logincontroller.htm"> -->
        <table  class="table table-striped">
			</tbody>
			<tr><td>Username: </td><td colspan="2"><form:input path="username"/><form:errors path="username"/></td></tr>
			<tr><td>Password: </td><td colspan="2"><form:password path="password"/><form:errors path="password"/></td></tr>
			<tr><td>Remember: </td><td colspan="2"><input type="checkbox" name="remember" value="remember for a week"/>Remember me for a week</td></tr>
            <!-- <input type="checkbox" name="remember" value="remember for a week"/>Remember me for a week -->
            </tbody>
			</table>
            <input type="submit" value="Member Login" />
        <!-- </form> -->
        </form:form>


</body>
</html>
