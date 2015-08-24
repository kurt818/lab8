<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
Welcome ${username}

<form action="requestvaccine" method="post">
<input type="hidden" name="create" value="request"/>
                <table border="1" class="table table-striped">
                </tbody>
                    <tr>
                        <th>Vaccine id</th>
                        <th>Vaccine name</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Manufacture</th>
                        <th>Produce Date</th>
                        <th>Expire Date</th>
                        <th>Request Quantity</th>
                    </tr>
                    <tr>
                        <td align="center">${vaccine.id}<input type="hidden" name="id" value="${vaccine.id}" /></td>
                        <td align="center">${vaccine.name}<input type="hidden" name="name" value="${vaccine.name}" /></td>
                        <td align="center">${vaccine.price}<input type="hidden" name="price" value="${vaccine.price}" /></td>
                        <td align="center">${vaccine.availability}<input type="hidden" name="availability" value="${vaccine.availability}" /></td>
                        <td align="center">${vaccine.manufacture}<input type="hidden" name="manufacture" value="${vaccine.manufacture}" /></td>
                        <td align="center">${vaccine.producedate}<input type="hidden" name="producedate" value="${vaccine.producedate}" /></td>
                        <td align="center">${vaccine.expiredate}<input type="hidden" name="expiredate" value="${vaccine.expiredate}" /></td>
                        <td align="center"><input type="text" name="requestQuantity" placeholder="Quantity" /><input type="hidden" name="userid" value="${userid}" /><input type="hidden" name="user" value="${username}" /></td>
                       
                    </tr>
                    </tbody>
                </table>
                <br><input type="submit" name="submit" value="Request for vaccine" />
                </form>
</body>
</html>