<%-- 
    Document   : menu
    Created on : Mar 25, 2015, 12:20:36 AM
    Author     : mengqingwang
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- <%
	Map<String, Object> model = (MAP<String, Object>)request.getAttribute("model");
	
%> --%>
<!DOCTYPE html>
<html>
    <head>
        <title>Menu</title>
        <style type="text/css">
            div#list{height:300px;width:100px;float:left;text-decoration: underline;color: blue}
            div#show{height:300px;width:450px;float:left}
            div#search{height:300px;width:450px;float:left}
            div#showmessages{height:300px;width:450px;float:left}
            div#viewcontacts{height:300px;width:450px;float:left}
            div#changepassword{height:300px;width:650px;float:left}
        </style>
    </head>
    <body>
        
        
        <div id="list">
        
            <a onclick="displayPage('search')">Search</a><br><br>
            <a onclick="displayPage('showmessages')">Show Messages</a><br><br>
            <a onclick="displayPage('viewcontacts')">View My Contacts</a><br><br>
            <a onclick="displayPage('changepassword')">Change Password</a><br><br>
            <a href="logout">Logout</a><br><br>
			<!-- onclick="displayPage('logout')" -->
        </div>
        
            <div id="show">
            <form action="delete" method="post">
                Welcome ${user.username}
                <table border="1">
                    <tr>
                        <th>Message ID</th>
                        <th>From User</th>
                        <th>Message</th>
                        <th>Date</th>
                        <th>Reply to User</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items = "${messageList}" var="message" >
                    <tr>
                        <td align="center">${message.messageID}</td>
                        <td align="center">${message.fromUser}</td>
                        <td align="center">${message.message}</td>
                        <td align="center">${message.messageDate}</td>
                        <td align="center"><a href="reply?toUser=${message.fromUser}">Reply</a></td>
                        <td align="center"><input type="checkbox" name="delete" value="${message.messageID}"/>delete</td><br>
                    </tr>
                    </c:forEach>
                    <tr>
                    	<th>You have ${messageSize} messages</th>
                    </tr>
                </table>
                <br><input type="submit" value="delete selected messages" /> 
                </form>
            </div>
            
            <script type="text/javascript">
            var id_list = ["search", "showmessages", "viewcontacts", "changepassword", "logout"];
        
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
//               
            }
                
            </script>
            <div id="search" style="display: none">
            <form action="search" method="post">
            Look up by username<hr>
            <br><br>
            Please enter the username of the person you want to look up<br><br>
            <input type="hidden" name="page" value="search"/> 
            <input type="text" name="search"/>
            <input type="submit" value="See profile" />  
            </form>
        	</div>

        
            <div id="showmessages" style="display: none">
            <form action="delete" method="post">
                Welcome ${user.username}
                <table border="1">
                    <tr>
                        <th>Message ID</th>
                        <th>From User</th>
                        <th>Message</th>
                        <th>Date</th>
                        <th>Reply to User</th>
                        <th>Delete</th>
                    </tr>
                    <c:forEach items = "${messageList}" var="message" >
                    <tr>
                        <td align="center">${message.messageID}</td>
                        <td align="center">${message.fromUser}</td>
                        <td align="center">${message.message}</td>
                        <td align="center">${message.messageDate}</td>
                        <td align="center"><a href="reply?toUser=${message.fromUser}">Reply</a></td>
                        <td align="center"><input type="checkbox" name="delete" value="${message.messageID}"/>delete</td><br>
                    </tr>
                    </c:forEach>
                    <tr>
                    	<td align="center">You have ${messageSize} messages</td>
                    </tr>
                </table>
                <br><input type="submit" value="delete selected messages" /> 
                </form>
            </div>
        <div id="viewcontacts" style="display: none">
            <form action="deleteMember" method="post">
            Welcome ${username}<hr>
            <input type="hidden" name="page" value="viewcontacts"/> 
            <br><br>
				<table border="1">
                    <tr>
                        <th>Contact ID</th>
                        <th>Contact Name</th>
                        <th>Comments</th>
                        <th>Data</th>
                        <th>Delete Contacts</th>
                    </tr>
                    <c:forEach items = "${contactsList}" var="contacts" >
                    <tr>
                        <td align="center">${contacts.contactID}</td>
                        <td align="center">${contacts.contactName}</td>
                        <td align="center">${contacts.comments}</td>
                        <td align="center">${contacts.dateAdded}</td>
                        <td align="center"><input type="checkbox" name="remove" value="${contacts.contactID}" />delete</td>
                    </tr>
                    </c:forEach>
                </table>
            <input type="submit" value="Delete selected contacts" />
            </form>
        </div>
        
        <div id="changepassword" style="display: none">
        <form action="changepassword" method="post">
            <input type="hidden" name="page" value="changepassword"/>
            <p1>Change password</p1>
				Please Enter your old password: <input type="text" name="oldpassword"/><br />
				Enter NEW password: <input type="text" name="newpassword"/><br />
				Enter NEW password again: <input type="text" name="newpassword1"/><br />
            <input type="submit" value="change password" />
            </form>
        </div>
    </body>
</html>