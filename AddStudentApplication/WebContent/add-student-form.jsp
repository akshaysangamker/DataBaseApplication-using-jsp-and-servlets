<!DOCTYPE html>
<html>
<head>
<title>Add Student</title>

</head>

<body>

<div id=wrapper>

	<div id="header">
	Football University
	</div>




</div>

<div id="Container">
	<form action="StudentControllerServlet" method="POST">
	<input type="hidden" name="command" value='ADD'>
	<table>
	<tbody>
		<tr>
			<td><label>FirstName:</label></td>
			<td><input type="text" name="firstname"></td>
		</tr>
	
		<tr>
			<td><label>LastName:</label></td>
			<td><input type="text" name="lastname"></td>
		</tr>
		
		<tr>
			<td><label>email:</label></td>
			<td><input type="text" name="email"></td>
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