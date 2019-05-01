window.Formulario = ((form) => {

    var form = form || {};
    var $docRoot = $(document);
    var formDom = null;

    /**
     * Validacion del formulario
     * @param String formId
     */
    form.validar = () => {

        var datos = formDom.find("input, select, textarea");
        var errores = new Array();

        datos.each((idx, el) => {

            var errorMsg = "";
            var fieldDom = $(el);
            var field = {
                name: fieldDom.attr('name'),
                type: fieldDom.attr('type'),
                value: fieldDom.val(),
                required: fieldDom.attr('required'),
				minlength: fieldDom.attr('minlength')
            };

            // Limpiamos DOM de validacion
            fieldDom.parent().removeClass("error");
            fieldDom.parent().find('.form-helper').html(""); 

            // Comprobaciones
            if (field.type !== "checkbox" && field.required && field.value === "") {
                errorMsg = "Este campo es obligatorio";
            } else if (field.minlength > field.value.length) {
                errorMsg = "Longitud incorrecta. Longitud mínima " + field.minlength;
            } else if (field.type === 'email' && isValidEmail(field.value) === false) {
                errorMsg = "El email es incorrecto";
            } else if (field.name === 'nacimiento' && isValidNacimiento(field.value) === true) {
                errorMsg = "La Fecha debe tener el formato: dd/mm/aaaa (dia/mes/año) y ser una fecha válida";
            } else if (field.name === 'dni' && isValidDNI(field.value) === false) {
                errorMsg = "Debe introducir un dni valido.";
            } else if(field.name === 'caducidad' && isCaducada(field.value)=== true){
            	errorMsg ="Por favor, introduzca una fecha en formato MM/YY";
            }

            // Si existe mensaje error actualizamos DOM
            if (errorMsg !== "") {
                fieldDom.parent().addClass("error");
                fieldDom.parent().find('.form-helper').html(errorMsg); 
                errores.push(errorMsg);
            }

        });

        return (errores.length === 0);

    };

    /**
     * Inicializa el formulario con eventos
     * @param string formId
     * @param function successCallback
     * @param function errorCallback
     */
    form.init = (formId, successFn, errorFn) => {

        formDom = $docRoot.find(formId);

        // Evento actualizar valor, se resetea estado de validacion
        formDom.find('input, select, textarea').each((idx, el) => {
            $(el).on('change', (ev) => {
                $(el).parent().removeClass("error");
                $(el).parent().find('.form-helper').html("");
            });
        });

        // Evento para envio de formulario
        formDom.on('submit', (ev) => {
            ev.preventDefault;
            if (Formulario.validar() === true) {
                enviar(successFn, errorFn);            
            } 
            return false;    
        });          

    };

  /**
     * Validacion de emails ( privada )
     * @param String email 
     */

    var isValidEmail = (email) => {

        var countArroba = (email.match(/@/g) || []).length;
        var emailParts = email.split('@');
        var userName = emailParts[0] !== undefined? emailParts[0] : "";
        var domain = emailParts[1] !== undefined ? emailParts[1] : "";
        var baseDomain = domain.substr(0, domain.lastIndexOf("."));      
        var extDomain = domain.substr(domain.lastIndexOf(".") + 1); 

        /** Comprobaciones:
         * Tiene una sola @, no tiene espacios, tiene un usuario y un dominio, 
         * el dominio contiene almenos un punto, el dominio tiene contenido
         * la extensión tiene al menos 2 caracteres
         */
        if (
            countArroba !== 1 || email.indexOf(" ") > 0 || 
            domain === "" || userName === "" || domain.indexOf('.') < 0 ||
            baseDomain.length === 0 || extDomain.length < 2
        ) {
            return false;
        } 

        return true;

    }

    
    /**
     * Validacion de nacimiento ( privada )
     * 
     */
    var isValidNacimiento = (nacimiento) => {
    	

        // Mediante el delimitador "/" separa dia, mes y año
    	var error = false;
        var fecha = nacimiento.split("-");
        var day = parseInt(fecha[2]);
        var month = parseInt(fecha[1]);
        var year = parseInt(fecha[0]);

        var f = new Date();
        var currentYear = f.getFullYear();
        if((currentYear-year) < 18)error = true;
        

        
        return error;

    }
    
    var isCaducada = (caducidad) =>{
    	var error = false;
    	var rexp = /^\d{2}\/\d{2}$/i;
        var date = caducidad.split("/");
        var month = parseInt(date[0]);
        var year = parseInt(date[1]);
        
        if (!rexp.test(caducidad)){
        	error=true;
        }else if(month>12 || month<1 || year<18){
        	error=true;
        }
        
    	return error;
    }
	
	var isValidDNI = (dni) => {

		var validChars = 'TRWAGMYFPDXBNJZSQVHLCKET';
		var nifRexp = /^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]$/i;
		var nieRexp = /^[XYZ][0-9]{7}[TRWAGMYFPDXBNJZSQVHLCKET]$/i;
		var str = dni.toString().toUpperCase();

		if (!nifRexp.test(str) && !nieRexp.test(str)) return false;

		var nie = str
		.replace(/^[X]/, '0')
		.replace(/^[Y]/, '1')
		.replace(/^[Z]/, '2');

	var letter = str.substr(-1);
	var charIndex = parseInt(nie.substr(0, 8)) % 23;

	if (validChars.charAt(charIndex) === letter) return true;

	return false;
	}
	
	var isValidTelefono = (telefono) => {
		var regExp2=/\s/;//<--- con esto vamos a validar que no tenga espacios en blanco
		var regExp3=/^(6)\d{8}$/;
		error === false;
		if(telefono.value==''){
			return false;
		}else if(!regExp2.test(telefono.value)){
			return false;
		}else if(!regExp3.test(telefono.value)){
			return false;
		}
		
        return true;
	}



    /**
     * Envio de información mediante AJAX ( privada )
     * @param string formId
     * @param function successCallback
     * @param function errorCallback
     */
    var enviar = (successCallback, errorCallback) => {

        var metodo = formDom.attr("method");
        var actionURl = formDom.attr("action");
        var datos = formDom.serializeArray();

        // Petición AJAX
        $.ajax({
            url: actionURl,
            method: metodo,
            data: datos,
            type: 'json'
        }).then(
            successCallback, 
            errorCallback
        );

    };    

    return form;

})(window.Formulario || undefined);