(function(){

var app = angular.module('universidadApp',[ ]);

app.controller('listadoCtrl', ['$scope', function($scope){

	$scope.listado = ["Fernando Herrera","Melissa Flores","Juan Carlos Pineda","Top otamadre"];
	
	$scope.listadoProfesores = { 
		profesores: [{
			nombre: "Fernando Herrera",
			edad: 29,
			clase: "PEE"
		},
		{
			nombre: "Melissa Flores",
			edad: 24,
			clase: "ICE"
		},
		{
			nombre: "Juan Carlos",
			edad: 42,
			clase: "Fire"
		}
		]
	};	



}]);





})();
