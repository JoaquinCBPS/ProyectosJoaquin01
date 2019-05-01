<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</head>

<body onload="setTimeout('cargar()',1900)">
	<jsp:include page="plantillaOpen.html"></jsp:include>
	
	<jsp:include page="plantillaMenu.html"></jsp:include>
			
	<%out.println(session.getAttribute("usuario")); %>
			<br/>
			<a href="logout.jsp" style="menu a:hover{color:white;">Cerrar sesion</a></p>
		</div>
		</br></br>
		<div id="index" align="center" size="100px">
			</br>
			<h1 style="background-color:silver; color:black;">¡Alla vamos!</h1>
			</br></br>
			<img id="gif" src="https://cdn.dribbble.com/users/1024572/screenshots/4342525/delorean.gif" alt="En marcha!" height="100%" width="100%"></img>
		</div>
		
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
	<script src="http://localhost:8080/ProyectoDuckFlyHibernate/assets/js/marcha.js"></script>

	<jsp:include page="plantillaClose.html"></jsp:include>
	
</body>
</html>