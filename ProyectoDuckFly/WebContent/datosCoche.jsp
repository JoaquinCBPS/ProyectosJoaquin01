<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Acceder</title>
	<link rel="stylesheet" type="text/css" href="assets/css/estilos.css">
	<link rel="shortcut icon" href="assets/images/patonejo-icon.png" />
</head>
<body>

		Importamos las clases de los paquetes dao y modelo -->
		<%@ page import = "dao.*" %>
		<%@ page import = "modelo.*" %>
		<%@ page import = "java.util.List" %>
		<%@ page import = "java.util.ArrayList" %>
		<% 
			String dni=(String)session.getAttribute("dniConductor");
			
			CocheConductorDAO c = new CocheConductorDAO();
			ConductorDAO conductorD = new ConductorDAO();
			PersonaDAO personaD = new PersonaDAO();
			CocheDAO cocheD = new CocheDAO();
			
			CocheConductor cocheCon = c.getCocheConductorByDni(dni);
			Conductor conductor = conductorD.getConductorById(dni);
			Persona persona = personaD.getPersonaById(dni);
			Coche coche = cocheD.getCocheById(cocheCon.getCoche().getMatricula());
			
			String marca = coche.getMarca();
			String modelo = coche.getModelo();
			String matricula = coche.getMatricula();
			String numeroLicencia = conductor.getNumeroLicencia();
			String nombre = persona.getNombre();
		%>
		
		
	<section class="contenedor">
		<br>
		<div class="logo">
			<img src="assets/images/patonejo-icon.png" height="100%" width="100%"></img>
		</div>
		<br><br>
			<form action="Aceptar" method="POST" name="form5" id="form5">
				<fieldset id="conectate">
					<legend align="center"></legend>
						<p style="text-align:center;">Estos son los datos de tu conductor:</p></br>
					    Marca: <input type="text" id ="marca" name="marca" readonly="readonly" value=<%=marca%>></br>
					    Modelo: <input type="text" id ="modelo" name="modelo" readonly="readonly" value=<%=modelo%>></br>					
					    Matricula: <input type="text" id ="matricula" name="matricula" readonly="readonly" value=<%=matricula%>></br>
					    Nombre Conductor: <input type="text" id ="nombre" name="nombre" readonly="readonly" value=<%=nombre%>></br>
						Numero Licencia: <input type="text" id ="numerolicencia" name="numerolicencia" readonly="readonly" value=<%=numeroLicencia%>></br>
						<br/><br/>
						<input class="boton" type="submit" value="ACEPTAR">
				</fieldset>
			</form>
		</article>
	</section>	
</body>
</html>