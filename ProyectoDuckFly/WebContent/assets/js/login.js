$(document).ready(function() {

    var $docRoot = $(document);

    var loginFormSuccess = (response) => {
        var msgDom = $docRoot.find('.msg');        
        msgDom.addClass(response.status).html(response.text);   
    }

    var loginFormError = (error) => {
        console.error("Formulario: Error desde servidor");
    }    
    
    // Inicializamos formulario
    Formulario.init("#loginForm", loginFormSuccess, loginFormError);

});