<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Student Tracker App </title>
	

</head>




<body>
	<div id="wrapper">
		<div id="header">
		Football University
		</div>
	
	
	</div>
	
	<div id="Container">
		<div id="Content">
		
		<input type="button" value="Add Student"
		onclick="window.location.href='add-student-form.jsp';return false;"		
		
		
		/>
		
		<table>
				<tr>
				
				<th>firstname</th>
				<th>lastname</th>
				<th>email</th>
				<th>Action</th>
				
				
				</tr>
				
				<c:forEach var="tempstudent" items="${STUDENT_LIST}">
				<c:url var="templink" value="StudentControllerServlet">
					
				<c:param name="command" value="LOAD"/>
				<c:param name="studentid" value="${tempstudent.id}"/>
				
				
				
				</c:url>
				
				<c:url var="deletelink" value="StudentControllerServlet">
				
				<c:param name="command" value="DELETE"/>
				<c:param name="studentid" value="${tempstudent.id}"/>
				</c:url>
				<tr>
				
				<td>${tempstudent.firstname} </td>
				<td>${tempstudent.lastname} </td>
				<td>${tempstudent.email} </td>
				<td> <a href="${templink}">Update</a>
				     |
					<a href="${deletelink}" onclick="if(!(confirm('Are you sure to delete this student?'))) return false">Delete</a>
					
				
				
				 </td>
				
				</tr>
				</c:forEach>
			
			
			</table>
		
			
				
				
				
				
		</div>
	
	
	</div>

</body>


</html>
