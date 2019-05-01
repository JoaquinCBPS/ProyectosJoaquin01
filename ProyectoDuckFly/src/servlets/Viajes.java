package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ClienteDAO;
import dao.ViajeDAO;
import manejador.GestionViajes;
import modelo.CocheConductor;
import modelo.Ubicacion;
import modelo.Viaje;

/**
 * Servlet implementation class Viajes
 */
@WebServlet("/Viajes")
public class Viajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Viajes() {
        super();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        
            //Guardamos la sesion
            HttpSession sesion = request.getSession(true);
            
            String dni = (String) sesion.getAttribute("dni");


            //Obtenemos los datos del formulario
            String ubicacionInicial =  request.getParameter("ubicacioninicialoculto");
            String ubicacionFinal =  request.getParameter("ubicacionfinaloculto");
            
            String tipoDeCoche = request.getParameter("seltipo");
            int tipoCoche = Integer.parseInt(tipoDeCoche);
            //Recogemos el tipo de coche
            boolean esXL;
            if (tipoCoche == (1)){
            	esXL =false;
            }
            else {
            	esXL=true;
            	}
            
            
            //Separamos las coordenadas 
            String[] coordenadasIn = ubicacionInicial.split(",");
            String[] coordenadasFi = ubicacionFinal.split(",");
            
            //Coordenadas de inicio
            String latitudIni = coordenadasIn[0];
            String longitudIni = coordenadasIn[1];
            
            //Coordenadas de fin
            String latitudFin = coordenadasFi[0];
            String longitudFin = coordenadasFi[1];
            
            //Casteamos a double la ubicacion inicial
            double latitudInicial = Double.parseDouble(latitudIni);
            double longitudInicial = Double.parseDouble(longitudIni);
            //Casteamos a double la ubicacion final
            double latitudFinal = Double.parseDouble(latitudFin);
            double longitudFinal = Double.parseDouble(longitudFin);
            
            //Lo pasamos a dos objetos ubicacion
            Ubicacion ubicacionInicio = new Ubicacion(latitudInicial,longitudInicial);
            Ubicacion ubicacionFin = new Ubicacion(latitudFinal,longitudFinal);
            
            ClienteDAO clienteDAO = new ClienteDAO();            
            Viaje trayecto = clienteDAO.pedirCoche(ubicacionInicio, ubicacionFin, esXL ,dni);
            
            GestionViajes manejador = new GestionViajes();
            
            	if(manejador.ValidaCoordenadas(trayecto)){
            		
            		manejador.asignaUbicacion();
            		CocheConductor conductor = manejador.calculaConductorCercano(trayecto);
            		if(conductor==null){
            			request.getRequestDispatcher("plantillaOpen.html").include(request, response);
                    	out.println("<div id='registro'><p><b>"+"No hay ningún conductor disponible, vuelve a intentarlo más tarde."+" </b></p>");
                    	out.println("<input class='boton' type='submit' value='VOLVER' onclick=\"location.href='solicitar.jsp'\"></div>");
                    	request.getRequestDispatcher("plantillaClose.html").include(request, response);
            			
            		}else{
            			trayecto.setDistanciaViaje(manejador.calculaRuta(trayecto.getUbicacionInicial(), trayecto.getUbicacionFinal()));
            			trayecto.setTiempoEspera(manejador.calculaTiempoEspera(trayecto, conductor));
            			trayecto.setPrecio(manejador.calculaPrecio(trayecto));
            			trayecto.setTiempoViaje(manejador.calculaTiempoRuta(trayecto));
            			trayecto.setAcabado(false);
            			sesion.setAttribute("dniConductor", conductor.getConductor().getDniConductor());
            			ViajeDAO viaje = new ViajeDAO();
            			viaje.addViaje(trayecto);
            			
                    	//Direccionamos al usuario a la página solicitar,html para que continue con su proceso de solicitud.
                    	request.getRequestDispatcher("mostrarRuta.jsp").forward(request, response);
            		}
         
            	}else{
            		request.getRequestDispatcher("plantillaOpen.html").include(request, response);
                	out.println("<div id='registro'><p><b>"+"Las ubicaciones introducidas se encuentran fuera de nuestra area de trabajo. Por favor,"
                			+ " introduzca unas ubicaciones validas."+" </b></p>");
                	out.println("<input class='boton' type='submit' value='VOLVER' onclick=\"location.href='solicitar.jsp'\"></div>");
                	request.getRequestDispatcher("plantillaClose.html").include(request, response);
            	}

	}

}
