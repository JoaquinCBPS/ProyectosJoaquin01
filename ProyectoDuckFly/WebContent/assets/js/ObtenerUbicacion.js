var origen,destino;
ubicacioninicial.onblur = function() 
                            {
                              origen = document.getElementById('ubicacioninicial').value;
                               
                            };
                       
                            ubicacionfinal.onblur = function() 
                            {
                             
                              destino = document.getElementById('ubicacionfinal').value;
                             
                            };
                       
     

//La función geocodeinicial convierte el string generado a través del formulario en geocoordenadas
function geocodeinicial(platform)
{

var direccioninicial =origen;

	// Obtenemos la ubicación inicial introducida por el cliente a través del
	// formulario
	

	var geocoder = platform.getGeocodingService(), geocodingParameters =
	{
		
	    searchText :direccioninicial,

	    jsonattributes : 1
	};

	geocoder.geocode(geocodingParameters, onSuccess, onError);

}
// La función geocodefinal convierte el string generado a través del formulario
// en geocoordenadas
function geocodefinal(platform)
{
var direccionfinal =destino;

	// Obtenemos la ubicación final introducida por el cliente a través del formulario
	
	var geocoder = platform.getGeocodingService(), geocodingParameters =
	{
	    searchText : direccionfinal,

	    jsonattributes : 1
	};

	geocoder.geocode(geocodingParameters, onSuccess, onError);

}
/**
 * Se llamará a esta función una vez que la API REST de enrutamiento proporcione una respuesta
 * 
 * @param {Object}
 *            result Un objeto JSONP que representa la ruta calculada
 */
function onSuccess(result)
{

	var locations = result.response.view[0].result;

	ObtenerUbicacion(locations);

}
//En caso de que geocode no devuelva nada, mostramos una alerta
function onError(error)
{
	alert('Problema de conexión');
}
// Iniciamos la sesión en Here
var platform = new H.service.Platform(
{
    app_id : 'vv0uGpi49kggYlPgl70r',
    app_code : 'SmSf6kiEB8q17DHgk3YCSQ',

    useCIT : true,
    useHTTPS : true
});

//El método ObtenerUbicación recibe como parámetro el objeto locations
//recorre el objeto y devuelve latitud y longitud
var ubicaciones = [];
function ObtenerUbicacion(locations)
{
//	alert("Obteniendo ubicación");
	var nodeOL = document.createElement('ul'), i, ubicacion;

	nodeOL.style.fontSize = 'small';
	nodeOL.style.marginLeft = '5%';
	nodeOL.style.marginRight = '5%';

	var latitud, longitud;
		for (i = 0; i < locations.length; i += 1)
	{
		var li = document.createElement('li'), divLabel = document
		        .createElement('div'), content = "";

		latitud = locations[i].location.displayPosition.latitude;
		 longitud = locations[i].location.displayPosition.longitude;
		ubicacion =
		[ latitud, longitud
		]
		
		ubicaciones.push(ubicacion);
		divLabel.innerHTML = content;
		li.appendChild(divLabel);
		nodeOL.appendChild(li);

	}
	
	document.forms[0].elements[3].value = ubicaciones[0];
	document.forms[0].elements[5].value = ubicaciones[1];

	


}

// Invocamos el geocode inicial y final
function invocarMapa(){
	geocodeinicial(platform);
	geocodefinal(platform);
	
}

