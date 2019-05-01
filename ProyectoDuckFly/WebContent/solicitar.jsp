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
			<a href="logout.jsp"> <!--style="menu a:hover{color:white;"-->Cerrar sesion</a></p>
		</div>
		</br>
		<article id="solicitar">
			
			
               <form action="Viajes" method="POST" name="form4" id="form4">
                  <fieldset id="solicita">
                   <label id="conectado"></label><br /> ¿Donde necesitas volar? <br /> <br /> 
                   Tipo coche: <select name = "seltipo" id="seltipo">
                    <option  value=1>Normal</option>
                    <option  value=2>XL</option>
                   </select> <br /> 
                   Ubicacion inicial: <input class="input" type="text" name="ubicacioninicial" id="ubicacioninicial" value="" size=30> 
                   	<input type="hidden" id = "ubicacioninicialoculto" name="ubicacioninicialoculto"  value="...." required>         
                   <br /> 
                   Ubicacion final: <input class="input" type="text" name="ubicacionfinnalfinal" id="ubicacionfinal" value="" size=30>
                   <input type="hidden" id = "ubicacionfinaloculto" name="ubicacionfinaloculto" value="....." required>
                    
                   <br /> <br /> 
                                    <input  class="boton" type="button" value="Confirmar Ruta" onclick="invocarMapa()" >
                                     <br /> 
                  
                       <!-- SCRIPT QUE PINTA LAS MARCAS DE ORIGEN Y DESTINO Y DEVUELVE SUS COORDENADAS-->
                           <script type="text/javascript">
                                                 
                           </script>
                   
                   <input  class="boton" type="submit" value="SOLICITAR" >
                  </fieldset>
                 </form>
		</article>
	</section>

<script language="javascript" type="text/javascript" src="assets/js/ObtenerUbicacion.js"></script>



</body>
</html>