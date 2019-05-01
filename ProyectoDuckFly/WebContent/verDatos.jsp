<html>
<body>
<jsp:include page="plantillaOpen.html"></jsp:include>
	<!-- Inicio del codigo java
		Importamos las clases de los paquetes dao y modelo -->
		<%@ page import = "dao.ClienteDAO" %>
		<%@ page import = "modelo.Cliente" %>
		<%@ page import = "dao.PersonaDAO" %>
		<%@ page import = "modelo.Persona" %>
		<% 
			/*Rescatamos el dni de la sesion iniciada al logearnos*/
			String dni=(String)session.getAttribute("dni");
		
			/*Creamos un cliente y una persona de la base de datos*/
			ClienteDAO c = new ClienteDAO();
			PersonaDAO p = new PersonaDAO();
			
			/*Creamos un cliente y una persona */
			Cliente cliente = c.getClienteById(dni);
			Persona persona = p.getPersonaById(dni);
		%>
<jsp:include page="plantillaMenu.html"></jsp:include>
		<%out.println(session.getAttribute("usuario")); %>
			</br>
			<a href="logout.jsp" style="menu a:hover{color:white;">Cerrar sesion</a></p>
		</div>
		</br></br>
<div id="verDatos">
	<p><b> Datos del usuario: </b></p>
	<br/>
	<table>
		<!-- Fila 1 - DNI -->
		<tr>
			<td>Dni: </td>
			<td> <%out.println(persona.getDniPersona()); %> </td>
		</tr>
		<!-- Fila 2 - Nombre -->
		<tr>
			<td>Nombre: </td>
			<td> <%out.println(persona.getNombre()); %> </td>
		</tr>
		<!-- Fila 3 - Apellidos -->
		<tr>
			<td>Apellido: </td>
			<td> <%out.println(persona.getApellidos()); %> </td>
		</tr>
		<!-- Fila 4 - Email -->
		<tr>
			<td>Email: </td>
			<td> <%out.println(persona.getEmail()); %> </td>
		</tr>
		<!-- Fila 5 - Telefono -->
		<tr>
			<td>Telefono: </td>
			<td> <%out.println(persona.getTelefono()); %> </td>
		</tr>
		<!-- Fila 6 - Fecha Nacimiento -->
		<tr>
			<td>Fecha Nacimiento: </td>
			<td> <%out.println(persona.getFechaNacimiento()); %> </td>
		</tr>
		<!-- Fila 7 - Direccion -->
		<tr>
			<td>Direccion: </td>
			<td> <%out.println(persona.getDireccion()); %> </td>
		</tr>
		<!-- Fila 8 - Codigo postal -->
		<tr>
			<td>Codigo postal: </td>
			<td> <%out.println(persona.getCodigoPostal()); %> </td>
		</tr>
		<!-- Fila 9 - Ciudad -->
		<tr>
			<td>Ciudad: </td>
			<td> <%out.println(persona.getCiudad()); %> </td>
		</tr>
		<!-- Fila 10 - Pais -->
		<tr>
			<td>Pais: </td>
			<td> <%out.println(persona.getPais()); %> </td>
		</tr>
		<!-- Fila 11 - Numero tarjeta -->
		<tr>
			<td>Nº tarjeta: </td>
			<td> <%out.println(cliente.getNumeroTarjeta()); %> </td>
		</tr>
		<!-- Fila 12 -->
		<tr>
			<td>Fecha caducidad: </td>
			<td> <%out.println(cliente.getFechaCaducidad()); %> </td>
		</tr>
	</table>
</div>

<jsp:include page="plantillaClose.html"></jsp:include>
 
</body>
</html>