package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manejador.GestionUsuarios;
import modelo.Cliente;
import modelo.Persona;

/**
 * En este Servlet recogeremos todos los datos relativos a los usuarios y (tras las comprobaciones necesarias) seran incluidos
 * en la Base de Datos.
 */
@WebServlet("/Registrar")

public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Registrar() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	
	/**
	 * Recogeremos los datos relativos a los usuarios para redirigirles a la página que les corresponda.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        	Persona p = null;
        	Cliente c = null;
        	String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");
            String dni = request.getParameter("dni");
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            Date nacimiento = new Date();
            try {
            	//Parseamos la fecha de nacimiento para introducirla en la Base de Datos en formato yyyy-MM-dd
				nacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("nacimiento"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				String comprobacion = "El formato de fecha introducido no es el correcto";
				request.getRequestDispatcher("plantillaOpen.html").include(request, response);
            	out.println("<div id='registro'><p><b>"+comprobacion+" </b></p>");
            	out.println("<input class='boton' type='submit' value='REGISTRAR' onclick=\"location.href='registro.html'\"></div>");
            	request.getRequestDispatcher("plantillaClose.html").include(request, response);
			}
            String direccion = request.getParameter("direccion");
            String codigo = request.getParameter("codigo");
            String ciudad = request.getParameter("ciudad");
            String pais = request.getParameter("pais");
            String tarjeta = request.getParameter("tarjeta");
            String caducidad = request.getParameter("caducidad");
            
            GestionUsuarios gestor = new GestionUsuarios();
            //Instanciamos dos objetos, persona y cliente, a los que les pasaremos como atributo los valores pasados por el formulario
            p = new Persona(dni,nombre,apellidos,email,telefono,nacimiento,
            		direccion,codigo,ciudad,pais);
            c = new Cliente(p,usuario,password,tarjeta,caducidad);
            //Llamamos al metodo de Gestion de Usuarios correspondiente para introducir en la Base de Datos al nuevo cliente
            String comprobacion = gestor.nuevoUsuario(p, c);
            
            //Si la insercion en la Base de Datos a ido como esperabamos, instanciamos un objeto Session pasandole como atributo el nombre del usuario.
            if(comprobacion.equals("ok") && p!=null && c!=null){
            	HttpSession sesion = request.getSession(true);
            	sesion.setAttribute("usuario", usuario);
            	sesion.setAttribute("dni", dni);
            	request.getRequestDispatcher("plantillaOpen.html").include(request, response);
            	//Direccionamos al usuario a la página solicitar,html para que continue con su proceso de solicitud.
            	request.getRequestDispatcher("plantillaMenu.html").include(request, response);
            	out.println(usuario );
            	request.getRequestDispatcher("solicitarCoche.html").include(request, response);
            }else{
            	//En caso de que la insercion haya sido incorrecta. Se lo comunicaremos al usuario a traves de un mensaje, redirigiendole a l página de registro.
            	
            	request.getRequestDispatcher("plantillaOpen.html").include(request, response);
            	out.println("<div id='registro'><p><b>"+comprobacion+" </b></p>");
            	out.println("<input class='boton' type='submit' value='REGISTRAR' onclick=\"location.href='registro.html'\"></div>");
            	request.getRequestDispatcher("plantillaClose.html").include(request, response);
            }

            out.close(); 
	}

}