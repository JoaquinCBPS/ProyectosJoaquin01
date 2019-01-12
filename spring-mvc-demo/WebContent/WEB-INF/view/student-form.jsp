<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<title>Student registration form</title>
</head>
<body>

     <form:form  action="processForm" modelAttribute="student">
                             <!--El path llama al set  -->
     First name: <form:input path="firstName"/>
     
     <br><br>
     
      Last name: <form:input path="lastName"/>
     
     <br><br>
     
     <form:select path="country">
     
       <form:options items="${student.countryOptions}" />
       <
     
     </form:select>
     
     <br><br>
     
     Favorite Language:
     
    <form:radiobuttons path="favoriteLanguage" items="${student.favoriteLanguageOptions}"  />
     
     <br><br>
     
     Operating Systems:
     
     Linux <form:checkbox path="operatingSystems" value="Linux" />
     Mac OS <form:checkbox path="operatingSystems" value="Mac OS" />
     MS Windows <form:checkbox path="operatingSystems" value="MS Windows" />
     
     
     <br><br>
     
     <input type ="submit" value ="Submit" />
     
     </form:form>



</body>
</html>