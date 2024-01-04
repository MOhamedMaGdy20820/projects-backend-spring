<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
  <title>list Customers</title>
  <!-- reference our style sheet -->
  <link
  type="text/css"
  rel ="stylesheet"
  href="${pageContext.request.contextPath}/resources/css/style.css"/>
  
  
  
</head>

<body>
	
	<div id= "wrapper">
		<div id = "header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	</div>
	
	
	
	
	
	<div id = "container" >
		<div id = "content">
		
		<!-- put new button: Add Customer -->
		
		<input type="button" value="Add Customer"
			onclick="window.location.href='showFormForAdd' ; return false; "
			class = "add-button" />  <!-- css style  --> 
		
		
		
		
		
			<!-- add our html table here -->
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
					
				</tr>
				
				<!-- loop over and print our cusomers -->
				<c:forEach var="tempCustomer" items="${customers}">
				
					<tr>
					<!-- construct an "update link with customer id" -->
					<c:url var ="updateLink" value="/customer/showFormForUpdate" >
 						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
 					</c:url>
 					<!-- badef parametar fe el model esmo customerId ba5zen 
						feh el id el5as ll customer ele 3ayez a3melo update or delete -->
					
					
					
 					<!-- construct an "Delete link with customer id" -->
 					<c:url var ="deleteLink" value="/customer/delete" >
 						<c:param name="customerId" value="${tempCustomer.id}"></c:param>
 					</c:url>
					
						<td>${tempCustomer.firstName}</td>
						<td>${tempCustomer.lastName}</td>
						<td>${tempCustomer.email}</td>
						
						<td>
						 <!-- display the update link -->
						<a href="${updateLink}">Update</a>
						|
						 <!-- display the delete link -->
						<a href="${deleteLink}"
						onclick="if(!(confirm('Are you sure you want to delete this cusomer?'))) return false"
						>Delete</a>
						</td>
						
						
					</tr>
				
				</c:forEach>
				
			</table>
			
		</div>
		
	</div>
	
</body>
</html>