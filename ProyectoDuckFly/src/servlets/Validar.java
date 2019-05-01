package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manejador.GestionUsuarios;

/**
 * Servlet implementation class Validar
 */
  @WebServlet("/Validar")
	public class Validar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validar() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * Método doPost del servlet Validar. Nos redirecciona a diferentes archivos HTML dependiendo del resultado de las comprobaciones en la Base de Datos
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        //Sacamos los atributos del usuario y de la contraseña del formulario de validacion.
        	String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            
            //Instanciamos el objeto GestionUsuarios
            GestionUsuarios gestor = new GestionUsuarios();
            
            //Si el atributo usuario o el atributo password se encuentra vacío redireccionamos al usuario de vuelta a la página de acceso.
            if(usuario=="" || password==""){
            	request.getRequestDispatcher("acceder.html").forward(request, response);
            	//Si el cliente se encuentra en la base de datos pero la contrasena introducida no le enviaremos un mensaje de aviso al usuario.
            }else if(gestor.comprobarCampo("Cliente", "nombreUsuario", usuario) && !gestor.comprobarCampo("Cliente", "contrasenia", password)){
            	request.getRequestDispatcher("errorContrasenia.jsp").forward(request, response);
            }else{
            	//Si el cliente y la contraseña son correctos inicializamos sesion dandole el atributo de usuario con el nombre del usuario que acaba de acceder
            	if (gestor.comprobacionUsuario(usuario, password)){
                	HttpSession sesion = request.getSession(true);
                	sesion.setAttribute("usuario", usuario);
                	sesion.setAttribute("dni", gestor.extraerDni(usuario));
                	//Direccionamos al usuario a la página solicitar,html para que continue con su proceso de solicitud.
                	request.getRequestDispatcher("plantillaOpen.html").include(request, response);
                	request.getRequestDispatcher("plantillaMenu.html").include(request, response);
                	out.println(usuario );
                	request.getRequestDispatcher("solicitarCoche.html").include(request, response);
                }else{
                	//Si el usuario y la contraseña introducida no son correctos se redirecciona al usuario a un pagina de error.
                	request.getRequestDispatcher("accederError.jsp").forward(request, response);
                }
            }
           
            
            out.close(); 
	}

}
