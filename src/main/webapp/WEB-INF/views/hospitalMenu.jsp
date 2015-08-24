<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hospital Work Area</title>
		<style type="text/css">
            div#list{height:300px;width:100px;float:left;text-decoration: underline;color: blue}
            div#show{height:300px;width:450px;float:left}
            div#createpatient{height:300px;width:450px;float:left}
            div#viewinsuredpatient{height:300px;width:450px;float:left}
            div#viewuninsuredpatient{height:300px;width:450px;float:left}
            div#viewinventory{height:600px;width:700px;float:left}
            div#requestvaccine{height:300px;width:650px;float:left}
            div#viewhistory{height:600px;width:650px;float:left}
        </style>
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
		<div class="col-sm-3" id="list">
        	<a onclick="displayPage('createpatient')">create patient</a><br><br>
            <a onclick="displayPage('viewinsuredpatient')">view insured patient</a><br><br>
            <a onclick="displayPage('viewuninsuredpatient')">view uninsured patient</a><br><br>
            <a onclick="displayPage('viewinventory')">view inventory</a><br><br>
            <a onclick="displayPage('requestvaccine')">request vaccine</a><br><br>
            <a onclick="displayPage('viewhistory')">view history</a><br><br>
            <a href="logout">Logout</a><br><br>
			<!-- onclick="displayPage('logout')" -->
        </div>
        
        	<div id="show">
                Welcome ${user.username}
            </div>
            
            <script type="text/javascript">
            var id_list = ["createpatient", "viewinsuredpatient", "viewuninsuredpatient", "viewinventory", "requestvaccine", "viewhistory"];
        
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
            
        <div id="createpatient" style="display: none">
			<form action="createpatientanduploadpicture" method="post" >
			<p1>Please create a patient:</p1><br /><br />
			<input type="hidden" name="create" value="patient"/>
			<table  class="table table-striped">
			</tbody>
			<tr><td>Name: </td><td colspan="2"><input type="text" name="name" placeholder="Name" /></td></tr>
			<tr><td>Gender: </td><td colspan="2"><input type="text" name="gender" placeholder="Male/Female" /></td></tr>
			<tr><td>Date of Birth: </td><td colspan="2"><input type="text" name="dob" placeholder="MM/dd/yyyy" /></td></tr>
			<tr><td>Insurance: </td><td colspan="2"><select name="insurance">
				<option value="Insured">Insured</option>
				<option value="Uninsured">Uninsured</option>
			</select></td></tr>
			<tr><td>Amount: </td><td colspan="2"><input type="text" name="amount" placeholder="Amount" /></td></tr>
			</tbody>
			</table>
			<input type="submit" value="create patient" />
	</form>
	</div>
	
	<div id="viewinsuredpatient" style="display: none">
            <form action="deleteinsuredpatient" method="post">
                Welcome ${user.username}
                <table border="1" class="table table-striped">
                    <thead>
					<tr>
                        <th>Patient id</th>
                        <th>Picture</th>
                        <th>Account</th>
                        <th>Hospital</th>
                        <th>Vaccine</th>
                        <th>Delete</th>
                    </tr>
			</thead>
			<tbody>
                    <c:forEach items = "${insuredPatientList}" var="ip" >
                    <tr>
                        <td align="center">${ip.id}</td>
                        <td align="center"><img src="<c:url value="/resources/picture/${ip.picture}"/>" /></td>
                        <td align="center">${ip.insuredamount}</td>
                        <td align="center">${ip.hospital}</td>
                        <td align="center"><a href="vaccine?patientid=${ip.id}">Vaccine</a></td>
                        <td align="center"><input type="checkbox" name="delete" value="${ip.id}"/>delete</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br><input type="submit" value="delete selected patients" /> 
                </form>
            </div>
            
            <div id="viewuninsuredpatient" style="display: none">
            <form action="deleteuninsuredpatient" method="post">
                Welcome ${user.username}
                <table border="1" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Patient id</th>
                        <th>Picture</th>
                        <th>Account</th>
                        <th>Hospital</th>
                        <th>Vaccine</th>
                        <th>Delete</th>
                    </tr>
			</thead>
			<tbody>
                    <c:forEach items = "${uninsuredPatientList}" var="up" >
                    <tr>
                        <td align="center">${up.id}</td>
                        <td align="center"><img src="<c:url value="/resources/picture/${up.picture}"/>" /></td>
                        <td align="center">${up.account}</td>
                        <td align="center">${up.hospital}</td>
                        <td align="center"><a href="vaccine?patientid=${up.id}">Vaccine</a></td>
                        <td align="center"><input type="checkbox" name="delete" value="${up.id}"/>delete</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <br><input type="submit" value="delete selected patients" /> 
                </form>
            </div>
            
             <div id="viewinventory" style="display: none">
            <form action="delete" method="post">
                Welcome ${user.username}
                <table border="1" class="table table-striped">
                    <thead>
                    <tr>
                        <th>Inventory id</th>
                        <th>Vaccine id</th>
                        <th>Vaccine name</th>
                        <th>Expire date</th>
                        <th>Quantity</th>
                        <th>User</th>
                    </tr>
			</thead>
			<tbody>
                    <c:forEach items = "${inventoryList}" var="inventory" >
                    <tr>
                        <td align="center">${inventory.id}</td>
                        <td align="center">${inventory.vaccineid}</td>
                        <td align="center">${inventory.vaccinename}</td>
                        <td align="center">${inventory.expiredate}</td>
                        <td align="center">${inventory.quantity}</td>
                        <td align="center">${inventory.user}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </form>
            </div>
            
            <div id="requestvaccine" style="display: none">
            <form action="requestvaccine" method="post">
                Welcome ${user.username}
                <table border="1" class="table table-striped">
                    <thead>
					<tr>
                        <th>Vaccine id</th>
                        <th>Vaccine name</th>
                        <th>Price</th>
                        <th>Availability</th>
                        <th>Manufacture</th>
                        <th>Produce Date</th>
                        <th>Expire Date</th>
                        <th>Request</th>
                    </tr>
			</thead>
			<tbody>
                    <c:forEach items = "${vaccineList}" var="vaccine" >
                    
                    <tr>
                    
                        <td align="center">${vaccine.id}</td>
                        <td align="center">${vaccine.name}</td>
                        <td align="center">${vaccine.price}</td>
                        <td align="center">${vaccine.availability}</td>
                        <td align="center">${vaccine.manufacture}</td>
                        <td align="center">${vaccine.producedate}</td>
                        <td align="center">${vaccine.expiredate}</td>
                        <%-- <input type="hidden" name="id" value="${vaccine.id}" />
                        <input type="hidden" name="name" value="${vaccine.name}" />
                        <input type="hidden" name="price" value="${vaccine.price}" />
                        <input type="hidden" name="availability" value="${vaccine.availability}" />
                        <input type="hidden" name="manufacture" value="${vaccine.manufacture}" />
                        <input type="hidden" name="producedate" value="${vaccine.producedate}" />
                        <input type="hidden" name="expiredate" value="${vaccine.expiredate}" /><input type="hidden" name="userid" value="${user.id}" /><input type="hidden" name="user" value="${user.username}" /> --%>
                        <td align="center"><a href="requestforvaccine?vaccine=${vaccine.id}">Request</a></td><br>
                    
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- <br><input type="submit" name="submit" value="Request for vaccine" /> -->
              </form>  
            </div>

	<div id="viewhistory" style="display: none">
			Welcome ${user.username}
			<table border="1" class="table table-striped">
				<thead>
				<tr>
					<th>Id</th>
					<th>Vaccine id</th>
					<th>Vaccine name</th>
					<th>Patient id</th>
					<th>Price</th>
					<th>Date</th>
					<th>Paid Amount</th>
					<th>Hospital</th>
				</tr>
			</thead>
			<tbody>
			<c:set var="totalpaid" value="0" />
				<c:forEach items="${usedvaccineList}" var="usedvaccine">
					<tr>
						<td align="center">${usedvaccine.id}</td>
						<td align="center">${usedvaccine.vaccineid}</td>
						<td align="center">${usedvaccine.vaccinename}</td>
						<td align="center">${usedvaccine.patientid}</td>
						<td align="center">$${usedvaccine.vaccineprice}</td>
						<td align="center">${usedvaccine.date}</td>
						<td align="center">$${usedvaccine.payamount}</td>
						<td align="center">${usedvaccine.hospital}</td>
					</tr>
					<c:set var="totalpaid" value="${totalpaid + usedvaccine.payamount}" />
				</c:forEach>
				</tbody>
			</table>
			<p><font size="5" face="verdana" color="green">The total amount paid: $<c:out value="${totalpaid}" /></font></p>
	</div>
</body>
</html>