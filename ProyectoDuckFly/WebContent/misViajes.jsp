<!DOCTYPE html>
<html lang="en">
<body>
	<jsp:include page="plantillaOpen.html"></jsp:include>
		<!-- Inicio del codigo java
		Importamos las clases de los paquetes dao y modelo -->
		<%@ page import = "dao.ViajeDAO" %>
		<%@ page import = "modelo.Viaje" %>
		<%@ page import = "java.util.List" %>
		<%@ page import = "java.util.ArrayList" %>
		<% 
			/*Rescatamos el dni de la sesion iniciada al logearnos*/
			String dni=(String)session.getAttribute("dni");
			
			/*Creamos un cliente y una persona de la base de datos*/
			ViajeDAO v = new ViajeDAO();
			
			/*Creamos un cliente y una persona */
			List<Viaje> viajesDao = v.getViajeByDni(dni);
			ArrayList<Viaje> viajes = new ArrayList<Viaje>();
			
			for(Viaje h:viajesDao){
				viajes.add(h);
			}
		%>

	<jsp:include page="plantillaMenu.html"></jsp:include>
	
		<%out.println(session.getAttribute("usuario")); %>
			</br>
			<a href="logout.jsp" style="menu a:hover{color:white;">Cerrar sesion</a></p>
		</div>
		</br></br>
		
		<div id="verViajes">
			<p><b> Datos viajes </b></p>
			<br/>
			<table>
				<!-- Fila 1 - DNI -->
				<th>
					<td>Fecha Viaje</td>
					<td>Latitud Inicial</td>
					<td>Longitud Inicial</td>
					<td>Latitud Final</td>
					<td>Longitud Final</td>
					<td>Tiempo espera</td>
					<td>Tiempo del viaje</td>
					<td>Precio</td>
					<td>Valoracion</td>
				</th>
				<!-- Fila 2 - Nombre -->
				<% 
				/*Si la longitud del viaje es 0 signofica que no ha realizado ninguno*/
				if(viajes.size() == 0) {
					out.println("Aun no ha realizado ningun viaje");
				}
				else{
					/*Aqui recorremos el arrayList de viajes y dependiendo de cuantos viajes halla, crearemos mas o menos filas*/
					
					for (Viaje w:viajes){%>
						<% %>
						<tr>
							<td></td>
							<td><%=w.getFechaViaje()%></td>
							<td><%=w.getLatitudInicial() %></td>
							<td><%=w.getLongitudInicial() %></td>
							<td><%=w.getLatitudFinal() %></td>
							<td><%=w.getLongitudFinal() %></td>
							<td><%=w.getTiempoEspera() %></td>
							<td><%=w.getTiempoViaje() %></td>
							<td><%=w.getPrecio() %></td>
							<td><%=w.getValoracionConductor() %></td>
						</tr>
					<%} %>
				<%
				} %>
			</table>
		</div>
		
	<jsp:include page="plantillaClose.html"></jsp:include>
 
</body>
</html>