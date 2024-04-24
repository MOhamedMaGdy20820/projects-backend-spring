<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
 <title>luv2code Company Home Page </title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	<p>
	Welcome to the luv2code company home page
	</p>
	
	<hr>
	<!-- display user name and role(s) -->
	
	<p>
	 	User: <security:authentication property="principal.username"/>
	 	<br><br>
	 	Role(s): <security:authentication property="principal.authorities"/><!-- authorities == roles -->
	</p>
	
	
	<security:authorize access="hasRole('MANAGER')"> <!-- show for only manager -->
		<!-- add a link to point to / leaders... this is for the managers -->
		
		<p>
		<a href="${pageContext.request.contextPath}/leaders">leadership meeting</a>
		(only for manager peeps)
		</p>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')"> <!-- show for only admin -->
		<!-- add a link to point to /systems ... this is for the managers -->
		<p>
		<a href="${pageContext.request.contextPath}/systems">IT Systems meeting</a>
		(only for Admin peeps)
		</p>
	</security:authorize>
	<hr>
	
	<!-- Add a logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" 
    		   method="post" >
	
	<input type="submit" value = "logout" />
	
	</form:form>


</body>
</html>