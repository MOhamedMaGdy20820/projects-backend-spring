<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>
	Student Registration Form 
	</title>
</head>

<body>
<form:form action="processForm" modelAttribute = "student">

	First name : <form:input path = "firstName"/>
	<br><br>
	Last name : <form:input path = "lastName"/>
	<br><br>
	 Country :
	
	  <%--  <form:select path ="country">

		  	<form:option value="Egypt" label="Egypt" />
		  	<form:option value="Brazil" label="Brazil" />
		  	<form:option value="India" label="India" />
		  	<form:option value="Germany" label="Germany" />
	 			
	  </form:select> --%>
	   
	    <form:select path ="country">

		  	<form:options items= "${student.countryOptons}" />
		  	
	   </form:select>
	   <br>
	   
	   favorite language : 
	   Java <form:radiobutton path ="favoraiteLanguage" value = "java"/>
   	   C# <form:radiobutton path ="favoraiteLanguage" value = "C#"/>
   	   PHP <form:radiobutton path ="favoraiteLanguage" value = "PHP"/>
   	   Ruby <form:radiobutton path ="favoraiteLanguage" value = "Ruby"/>
	   
	   <br>
	   operatingSystem : 
	   Linux <form:checkbox path ="operatingSystem" value = "Linux"/>
   	   Mac os <form:checkbox path ="operatingSystem" value = "Mac os"/>
   	   MS windows <form:checkbox path ="operatingSystem" value = "MS windows"/>
   	  
	<br><br>
	<input type ="submit" value ="Submit"/>
	
	</form:form>	

</body>



</html>