<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>custom login page</title>
    
    <style >
     .faild{
     color: red
     }
    </style>
    
</head>
<body>

     <h2>My Custom login page </h2>
    <form:form action="${pageContext.request.contextPath}/authenticationTheUser" 
    	method="post" >
      
		
		<!-- check for login error  -->
		
		<c:if test="${param.error != null}">
		<i class = "faild"> Sorry! you entered invalid username/password</i>
		</c:if>
      
       <p>
      		User name: <input type="text" name = "username" />
       </p>
      
       <p>
      		Password:  <input type="password" name = "password" />
       </p>
        
        
        <input type="submit" value="Login" />
    </form:form>
    
    
</body>
</html>
