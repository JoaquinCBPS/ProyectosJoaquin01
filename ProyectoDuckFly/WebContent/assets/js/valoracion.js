function cargar(){
	document.getElementById("index").innerHTML='<div style="width:90%; margin-left:auto; margin-right:auto; padding:3%; border-radius:15px; background-color:silver; ">'
	+'<p><b>¡ENHORABUENA! El viaje se ha realizado sin imprevistos</b></p></br></br>'
	+'<form action="Validacion" method="POST" name="form6" id="form6"><p>Valore a nuestro conductor: </p>'
		+'1 <input type="radio" name="nota" id="nota" value=1 checked> &nbsp'
		+'2 <input type="radio" name="nota" id="nota" value=2> &nbsp'
		+'3 <input type="radio" name="nota" id="nota" value=3> &nbsp'
		+'4 <input type="radio" name="nota" id="nota" value=4> &nbsp'
		+'5 <input type="radio" name="nota" id="nota" value=5> </br></br>'
		+'<textarea name="incidencia" id="incidencia" style="width:50%;">Cuentenos tu experiencia</textarea></br></br>'
		+ '<form action="solicitar.jsp" method="POST"><input class="boton" type="submit" value="VALORAR"></form>'
	+'</form></div>';
}