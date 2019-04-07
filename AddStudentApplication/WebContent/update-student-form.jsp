<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Update Student</title>

</head>

<body>

<div id=wrapper>

	<div id="header">
	Football University
	</div>




</div>

<div id="Container">
	<form action="StudentControllerServlet" method="GET">
	<input type="hidden" name="command" value='UPDATE'>
	<input type="hidden" name="studentid" value="${THE_STUDENT.id}">
	<table>
	<tbody>
		<tr>
			<td><label>FirstName:</label></td>
			<td><input type="text" name="firstname" value="${THE_STUDENT.firstname}"></td>
		</tr>
	
		<tr>
			<td><label>LastName:</label></td>
			<td><input type="text" name="lastname" value="${THE_STUDENT.lastname}"></td>
		</tr>
		
		<tr>
			<td><label>email:</label></td>
			<td><input type="text" name="email" value="${THE_STUDENT.email}" ></td>
		</tr>
		
		<tr>
			<td><input type="Submit" value="Save"></td>
		
		</tr>
	
	</tbody>
	
	
	
	
	
	
	
	
	</table>
	
	
	
	</form>



</div>



</body>





</html> 