<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Request</title>
</head>
<body>
This is the ftl page<br><br>
Welcome ${hospitalusername}

<form action="requestvaccine" method="post">
<input type="hidden" name="create" value="request"/>
                <table border="1">
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
                        <td align="center"><input type="text" name="requestQuantity" placeholder="Quantity" /><input type="hidden" name="user" value="${username}" /></td>
                    </tr>
                </table>
                <br><input type="submit" name="submit" value="Request for vaccine" />
                </form>
</body>
</html>