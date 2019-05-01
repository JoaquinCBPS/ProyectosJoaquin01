package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CocheConductorDAO;
import dao.CocheDAO;
import dao.ConductorDAO;
import dao.ViajeDAO;
import modelo.CocheConductor;
import modelo.Viaje;



/**
 * Servlet implementation class Aceptarviaje
 */
@WebServlet("/Aceptar")
public class AceptarViaje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AceptarViaje() {
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
        
        

        //Guardamos la sesion
        HttpSession sesion = request.getSession(true);
        
        //Recogemos datos almecenados en la sesion
        String dni = (String) sesion.getAttribute("dni");
        
        String dniConductor = (String) sesion.getAttribute("dniConductor");
        
        //Instanciamos los DAO
        ViajeDAO viaje = new ViajeDAO();
        
        CocheConductorDAO CocheConductorDAO = new CocheConductorDAO();
        
        ConductorDAO conductorDAO = new ConductorDAO();
        
        CocheDAO cocheDAO = new CocheDAO();
        
        
        //Obtenemos el viaje en curso
        Viaje trayecto = viaje.getLastViaje(dni);
		
        //Metemos el dni del conductor en el objeto viaje
        trayecto.setDniConductor(dniConductor);
        
        //Y lo guardamos en la base de datos
        viaje.updateViaje(trayecto);
        
        //Sacamos el objeto CocheConductor a trav�s del objeto conductor 
        CocheConductor carDriver = CocheConductorDAO.getCocheConductorByDni(dniConductor);
        //Obtenemos el conductor
       // Conductor driver = carDriver.getConductor();
        //Obtenemos el coche
        String matricula = carDriver.getCoche().getMatricula();
       
        //Comprobamos que no hay una avria en el coche
        boolean averia = cocheDAO.averiado(matricula);
        if(averia){
        	trayecto.setIncidencia(conductorDAO.tipoDeAveria());
        	conductorDAO.comunicaFinTrayecto(trayecto);
        	
        	request.getRequestDispatcher("incidenciaRuta.jsp").forward(request, response);

        }
        else{
        	conductorDAO.comunicaFinTrayecto(trayecto);
        	
        	request.getRequestDispatcher("rutaFinalizada.jsp").forward(request, response);
        }
        	
        
        
       
          
      
        
        
        
		
	}

}
