function cargar(){
	document.getElementById("index").innerHTML='<div style="width:90%; margin-left:auto; margin-right:auto; padding:3%; border-radius:15px; background-color:silver; ">'
	+'<p><b>Lamentamos comunicarle que han surgido una incidencia durante el trayecto:</b></p></br></br>'
	+'<p>El motivo de la incidencia ha sido: </p></br>'
	+'<pre>'+ document.getElementById('incidencia').value +  '</pre></br>'
	+ '<form action="solicitar.jsp" method="POST"><input class="boton" type="submit" value="VOLVER"></form>'
	+'</form></div>';
}