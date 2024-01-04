<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>

<head>
		<title>Student Confirmation</title>
</head>

<body>
	
	the student is confirmed: ${student.firstName} ${student.lastName}
	<br><br>
	Country : ${student.country}
	<br><br>
	Favoraite Language: ${student.favoraiteLanguage }
	<br><br>
	operatingSystem : 
	<ul>
		<c:forEach var="temp" items="${student.operatingSystem}">
			<li> ${temp} </li>
		</c:forEach>
	</ul>
</body>


</html>