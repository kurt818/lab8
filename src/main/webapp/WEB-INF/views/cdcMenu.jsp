<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CDC Work Area</title>
<style type="text/css">
            div#list{height:300px;width:100px;float:left;text-decoration: underline;color: blue}
            div#createvaccine{height:300px;width:450px;float:left}
            div#showRequests{height:300px;width:900px;float:left}
            div#viewvaccine{height:300px;width:450px;float:left}
        </style>
        
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script>$(document).ready(function(){
    $("p").click(function(){
        $(this).hide();
    });
});
</script>

<script type="text/javascript" src="<c:url value="/resources/vaccineArray.js" />"></script>
</head>
<body>
		<div class="col-sm-3" id="list">
        	<a onclick="displayPage('createvaccine')">create vaccine</a><br><br>
            <a onclick="displayPage('showRequests')">show requests</a><br><br>
            <a onclick="displayPage('viewvaccine')">view vaccine</a><br><br>
            <a href="logout">Logout</a><br><br>
        </div>
        
        <div id="show">
                Welcome ${username}
            </div>
            
        <script type="text/javascript">
            var id_list = ["createvaccine", "showRequests", "viewvaccine"];
        
            function displayPage(id){
                for(var i=0; i<id_list.length; i++){
                    if(id_list[i] === id){
                        document.getElementById(id_list[i]).style.display = "inline";
                        document.getElementById('show').style.display = "none";
                    }
                    else{
                        document.getElementById(id_list[i]).style.display = "none";
                    }                    
                }               
            }
                
            </script>
            
        <div id="createvaccine" style="display: none">
			<form action="createvaccine" method="post">
			<p1>Please create a vaccine:</p1><br /><br />
			<input type="hidden" name="create" value="vaccine"/>
			<table  class="table table-striped">
			</tbody>
			<tr><td>Vaccine Name: </td><td colspan="2"><input onchange="process()" type="text" name="vaccinename" placeholder="Vaccine Name" id="vaccinename" /></td></tr>
			<tr><td>price: </td><td colspan="2"><input type="text" name="price" placeholder="price" /></td></tr>
			<tr><td>Availability:  </td><td colspan="2"><input type="text" name="availability" placeholder="Availability" /></td></tr>
			<tr><td>Expiredate: </td><td colspan="2"><input type="text" name="expiredate" placeholder="MM/dd/yyyy" />&nbsp;&nbsp;</td></tr>
			</tbody>
			</table>
			<div id="underInput" ></div>
			<input type="submit" value="create vaccine" />
	</form>
	</div>
	
	<div class="demo-type-example col-xs-12" id="showRequests" style="display: none">
            <form action="approverequest" method="post">
                Welcome ${user.username}
                <table border="1" class="table table-striped">
                <thead>
                <tr>
                        <th>Request Id</th>
                        <th>Vaccine Id</th>
                        <th>Vaccine Name</th>
                        <th>Availability</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total Price</th>
                        <th>Status</th>
                        <th>From</th>
                        <th>Approve</th>
                        </tr>
                   </thead>
			<tbody>
                    <c:forEach items = "${requestList}" var="request" >
                    <tr>
                        <td align="center">${request.id}<input type="hidden" name="id" value="${request.id}" /></td>
                        <td align="center">${request.vaccineid}<input type="hidden" name="vaccineid" value="${request.vaccineid}" /></td>
                        <td align="center">${request.vaccinename}<input type="hidden" name="vaccinename" value="${request.vaccinename}" /></td>
                        <td align="center">${request.availability}<input type="hidden" name="availability" value="${request.availability}" /></td>
                        <td align="center">${request.quantity}<input type="hidden" name="quantity" value="${request.quantity}" /></td>
                        <td align="center">${request.price}<input type="hidden" name="price" value="${request.price}" /></td>
                        <td align="center">${request.totalprice}<input type="hidden" name="totalprice" value="${request.totalprice}" /></td>
                        <td align="center">${request.status}<input type="hidden" name="status" value="${request.status}" /></td>
                        <td align="center">${request.username}<input type="hidden" name="userid" value="${request.username}" /><input type="hidden" name="user" value="${user.username}" /></td>
                        <td align="center"><input type="checkbox" name="approve" value="${request.id}"/></td>
                    </tr>
                    </c:forEach>
                    </tbody>
                    </table>
                    
                <br><input type="submit" value="Approve selected requests" />
                </form>
            </div>
            
            <div id="viewvaccine" style="display: none">
            <form action="deletevaccine" method="post">
                Welcome ${username}
                <br><br><input type="button" onclick="document.getElementById('date').innerHTML = Date()" value="Today's date"/>
                <p id="date"></p>
                <table border="1" class="table table-striped">
                    <thead>
					<tr>
                        <th>Id</th>
                        <th>Vaccine Name</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Manufacture</th>
                        <th>Produce Date</th>
                        <th>Expire Date</th>
                        <th>Delete</th>
                    </tr>
			</thead>
			<tbody>
                    <c:forEach var="vaccine" items = "${producedvaccineList}" >
                    <tr>
                        <td align="center">${vaccine.id}</td>
                        <td align="center">${vaccine.name}</td>
                        <td align="center">${vaccine.price}</td>
                        <td align="center">${vaccine.availability}</td>
                        <td align="center">${vaccine.manufacture}</td>
                        <td align="center">${vaccine.producedate}</td>
                        <td align="center">${vaccine.expiredate}</td>
                        <td align="center"><input type="checkbox" name="deletevaccine" value="${vaccine.id}"/>delete</td><br>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br><input type="submit" value="delete selected vaccine" /> 
                </form>
            </div>
</body>
</html>