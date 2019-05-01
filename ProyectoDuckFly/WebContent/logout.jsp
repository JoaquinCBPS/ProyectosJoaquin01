<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>

<body>
	<jsp:include page="plantillaOpen.html"></jsp:include>
	
			<% session.removeAttribute("usuario");
			session.removeAttribute("dni");
			session.invalidate();%>
			
		<div id="registro">
			<p><b> Ha cerrado sesión correctamente. </b></p>
			<input class="boton" type="submit" value="VOLVER" onclick="location.href='acceder.html'">
		</div>
		
	<jsp:include page="plantillaClose.html"></jsp:include>
	
</body>
</html>