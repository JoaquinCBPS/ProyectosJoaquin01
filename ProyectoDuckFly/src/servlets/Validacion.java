package servlets;

import java.util.List;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConductorDAO;
import dao.ViajeDAO;
import modelo.Conductor;
import modelo.Viaje;

/**
 * Servlet implementation class Validacion
 */
@WebServlet("/Validacion")

public class Validacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validacion() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    ViajeDAO viaje = new ViajeDAO();
    public static double  obtenerMedia(ViajeDAO viaje, String dniConductor){
    	double contador = 0;
    	
    	List<Viaje> misViajes = viaje.listViajes();

		int i = 0;    	
    	for(Viaje v:misViajes){
    		String dni = v.getDniConductor();
    		if(dni!=null && dni.equals(dniConductor) && v.getValoracionConductor()!=null){
        		contador = Double.parseDouble(v.getValoracionConductor()) + contador;
        		i++;
    		}
    	}
    	double mediaConductor = (contador/i);
    	
    	return mediaConductor;
    	
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		//Guardamos la sesion
		HttpSession sesion = request.getSession(true);
		
		 //Recogemos datos almecenados en la sesion
        String dni = (String) sesion.getAttribute("dni");
        String dniConductor = (String) sesion.getAttribute("dniConductor");
        //Recogemos la incidencia
        String incidencia = request.getParameter("incidencia");
       //Recogemos la valoracion del conductor
        String valoracionConductor = request.getParameter("nota");
                
        ViajeDAO viaje = new ViajeDAO();
        ConductorDAO c = new ConductorDAO();
        Viaje trayecto = viaje.getLastViaje(dni);
        trayecto.setValoracionConductor(valoracionConductor);
        trayecto.setIncidencia(incidencia);
        viaje.updateViaje(trayecto);
        //sacamos al conductor del viaje por la sesion
        Conductor driver = c.getConductorById(dniConductor);
        
        //planchamos valoracion en la base de datos
        
        driver.setValoracionMedia(Validacion.obtenerMedia(viaje, dniConductor));
        ConductorDAO conductorDAO = new ConductorDAO();
        conductorDAO.updateConductor(driver);
       
		request.getRequestDispatcher("solicitar.jsp").forward(request, response);
	

         
        
	}
   
    	
    }


