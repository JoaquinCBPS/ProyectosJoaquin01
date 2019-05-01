<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="plantillaOpen.html"></jsp:include>

<jsp:include page="plantillaMenu.html"></jsp:include>
<%out.println(session.getAttribute("usuario")); %>


<%@ page import="dao.ViajeDAO"%>
<%@ page import="modelo.Viaje"%>
<%@ page import="dao.CocheConductorDAO"%>
<%@ page import="modelo.CocheConductor"%>
<% 
		/*Rescatamos el dni de la sesion iniciada al logearnos*/
		String dniCliente=(String)session.getAttribute("dni");
		String dniConductor=(String)session.getAttribute("dniConductor");

	
		ViajeDAO v = new ViajeDAO();
		Viaje trayecto = v.getLastViaje(dniCliente);
		
		CocheConductorDAO c = new CocheConductorDAO();
		CocheConductor conductor = c.getCocheConductorByDni(dniConductor);
		
		String ubicacionInicial = trayecto.getUbicacionInicial().getLatitud()+","+trayecto.getUbicacionInicial().getLongitud();
		String ubicacionFinal = trayecto.getUbicacionFinal().getLatitud()+","+trayecto.getUbicacionFinal().getLongitud();

	%>

</br>
<a href="logout.jsp" style="menu a: hover{color:white;">Cerrar
	sesion</a>
</p>
</div>
</div>
<br />
<article id="solicitar">
	<hr />
	<div id="map" style="width: 600px; height: 300px; background: grey;"></div>
	<hr />
	<form action='enMarcha.jsp' method="POST" name="form4" id="form4">
		<fieldset id="solicita">
			<br /> Ubicacion inicial: <input type="text" id="ubicacioninicial"
				name="ubicacioninicial" readonly="readonly"
				value=<%=ubicacionInicial%> size="50"><br /> Ubicacion
			final: <input type="text" id="ubicacionfinal" name="ubicacionfinal"
				readonly="readonly" value=<%=ubicacionFinal%> size="50"><br />
			Tiempo de espera (min.): <input type="text" id="tiempoespera"
				name="tiempoespera" readonly="readonly"
				value=<%=trayecto.getTiempoEspera()%>><br /> Tiempo de ruta
			(min.): <input type="text" id="tiemporuta" name="tiemporuta"
				readonly="readonly" value=<%=trayecto.getTiempoViaje()%>><br />
			Distancia (km.): <input type="text" id="distancia" name="distancia"
				readonly="readonly" value=<%=trayecto.getDistanciaViaje()%>><br />
			Precio (euros.): <input type="text" id="precio" name="precio"
				readonly="readonly" value=<%=trayecto.getPrecio()%>><br />

			¿Desea realizar el viaje? <br /> <input class="boton" type="submit"
				value="SI">
	</form>
	<form action='solicitar.jsp' method="POST">
		<input class="boton" type="submit" value="NO">
	</form>
	<br />
	</fieldset>
</article>
</section>
<script type="text/javascript">
	
/**
 * Mostramos la fluencia de las carreteras
 */
 function enableTrafficInfo (map) {
	  // Center map on New York
	  map.setCenter({lat: 40.42028, lng: -3.70578});
	  map.setZoom(7);

	  // Show traffic tiles
	  map.setBaseLayer(defaultLayers.normal.traffic);

	  // Enable traffic incidents layer
	  map.addLayer(defaultLayers.incidents);
	}
	/**
	 * Añadimos marcas en la posición inicial y final del fiaje
	 */
 function addMarkersToMap(map) {
	 
	 var ubiinicial = document.getElementById('ubicacioninicial').value;
	  var ubifinal = document.getElementById('ubicacionfinal').value;
	   [latini, lngini] = ubiinicial.split(',');
	   [latfin, lngfin] = ubifinal.split(',');

	  
	  var UbicacionInicial = new H.map.Marker({lat: latini, lng: lngini});
	  map.addObject(UbicacionInicial);
	  var UbicacionFinal = new H.map.Marker({lat: latfin, lng: lngfin});
	  map.addObject(UbicacionFinal);

	}
	/**
	 * CalculeRouteFromAtoB genera una ruta desde la ubicación inicial hasta la final
	 */
	
	function calculateRouteFromAtoB (platform) {
		
	  var ubiinicial = document.getElementById('ubicacioninicial').value;
	  var ubifinal = document.getElementById('ubicacionfinal').value;

	  var router = platform.getRoutingService(),
	    routeRequestParams = {
	      mode: 'fastest;car',
	      representation: 'display',
	      routeattributes : 'waypoints,summary,shape,legs',
	      maneuverattributes: 'direction,action',
	      waypoint0: ubiinicial, // Brandenburg Gate
	      waypoint1: ubifinal
								
	/*
	 * waypoint0: ObtenerUbicacion(), waypoint1: ObtenerUbicacion()
	 */
	 
	    };

	  router.calculateRoute(
	    routeRequestParams,
	    onSuccess,
	    onError
	  );
	}
	/**
	 * Se llamará a esta función una vez que la API REST de enrutamiento proporcione una respuesta
	 * 
	 * @param {Object}
	 *            result Un objeto JSONP que representa la ruta calculada
	 */

	function onSuccess(result) {
		 
	// el objeto route recibe la ruta, la añade al mapa y al panel par amostrar los datos (tiempo de ruta, distancia en km)
	  var route = result.response.route[0];

	  addRouteShapeToMap(route);
	  //addManueversToMap(route);
	 
	}

	/**
	 * Se llamará a esta función si se produce un error de comunicación durante la solicitud JSON-P
	 */
	function onError(error) {
	  alert('Ooops!');
	}

	/**
	 * El código de inicialización del mapa de Boilerplate comienza a continuación:
	 */

	// Configuramos los contenedores que almacenarán el mapa y el panel en el HTML
	var mapContainer = document.getElementById('map'),
	  routeInstructionsContainer = document.getElementById('panel');

	// Paso 1: inicializar la comunicación con la plataforma.
	var platform = new H.service.Platform({
	  app_id: 'DemoAppId01082013GAL',
	  app_code: 'AJKnXv84fjrb0KIHawS0Tg',
	  useCIT: true,
	  useHTTPS: true
	});
	var defaultLayers = platform.createDefaultLayers();

	// inicializar un mapa: este mapa se centra en Madrid
	var map = new H.Map(mapContainer,
			  defaultLayers.normal.map,{
		  center: {lat:40.42028, lng:-3.70578},
		  zoom: 7
			});

	// Paso 3: hacer el mapa interactivo
	// MapEvents habilita el sistema de eventos
	// Comportamiento implementa interacciones predeterminadas para panorámica / zoom (también en entornos móviles táctiles)
	var behavior = new H.mapevents.Behavior(new H.mapevents.MapEvents(map));

	// Crear los componentes de la interfaz de usuario predeterminados
	var ui = H.ui.UI.createDefault(map, defaultLayers);


	/**
	 * Crea un H.map.Polyline a partir de la forma de la ruta y lo agrega al mapa.
	 * 
	 * @param {Objeto}
	 *            ruta Una ruta tal como se recibió del servicio H.Service.RoutingService
	 */
	function addRouteShapeToMap(route){
	  var strip = new H.geo.Strip(),
	    routeShape = route.shape,
	    polyline;

	  routeShape.forEach(function(point) {
	    var parts = point.split(',');
	    strip.pushLatLngAlt(parts[0], parts[1]);
	  });

	  polyline = new H.map.Polyline(strip, {
	    style: {
	      lineWidth: 4,
	      strokeColor: 'rgba(0, 128, 255, 0.7)'
	    }
	  });
	  // Añade la polilinea al mapa
	  map.addObject(polyline);
	  // 
	 // Y zoom a su rectángulo delimitador.
	  map.setViewBounds(polyline.getBounds(), true);
	}


	//Invocamos el método calcularRuta
	calculateRouteFromAtoB (platform);
	enableTrafficInfo(map);
	addMarkersToMap(map);

	</script>
<script language="javascript" type="text/javascript"
	src="assets/js/ObtenerUbicacion.js"></script>
</body>
</html>