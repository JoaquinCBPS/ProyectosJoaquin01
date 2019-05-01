<!DOCTYPE html>
<html lang="en">
<body onload="setTimeout('cargar()',10000)">
	<jsp:include page="plantillaOpen.html"></jsp:include>
	
	<jsp:include page="plantillaMenu.html"></jsp:include>
	
			<%out.println(session.getAttribute("usuario")); %>
			<br/>
			<a href="logout.jsp" style="menu a:hover{color:white;">Cerrar sesion</a></p>
		</div>
		</br></br>
		<div id="index" align="center" size="100px">
			<!-- <p id="titulo" onclick="location.href='acceder.html'"><b> Bienvenidos a DUCK FLY</b></p> -->
			<img id="gif" src="https://i.redd.it/jurmzxsb19zz.gif" alt="En ruta!" height="100%" width="100%"></img>
		</div>
		
		<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
		<script src="http://localhost:8080/ProyectoDuckFlyHibernate/assets/js/valoracion.js"></script>
		
	<jsp:include page="plantillaClose.html"></jsp:include>
	
</body>
</html>