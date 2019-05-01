package manejador;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.CocheConductorDAO;
import dao.CocheDAO;
import dao.ConductorDAO;
import modelo.Coche;
import modelo.CocheConductor;
import modelo.Conductor;
import modelo.Ubicacion;
import modelo.Viaje;

public class GestionViajes {
		
	private Ubicacion ubicacionInicial;
	private Ubicacion ubicacionFinal;
	static ArrayList<Conductor> conductores = new ArrayList<Conductor>();
	static ArrayList<Coche> coches = new ArrayList<Coche>();
	static ArrayList<CocheConductor> cochesConductores = new ArrayList<CocheConductor>();
	static ArrayList<Ubicacion> UbicacionesConductores = new ArrayList<Ubicacion>();
	

	/**
	 * Mediante las clases DAO CocheDAO y ConductorDAO accedemos a la base de datos creando listas de cochesDAO y conductoresDAO
	 * Posteriormente los introducimos en un array
	 */
	public static void rellenarArrays(){
		
		CocheDAO coche  = new CocheDAO();
		
		List<Coche> CochesDAO =coche.listCoches();
		
		for(Coche c: CochesDAO){
			coches.add(c);
			
		}
		ConductorDAO conductor = new ConductorDAO();
		
		List<Conductor> ConductoresDAO = conductor.listConductors();
		
		for(Conductor cd: ConductoresDAO){
			conductores.add(cd);
		}
	}
	
	
	/**
	 * El m�todo asignar coches crea un array CochesConductores(asignacion de que coche conduce cada conductor teniendo en cuenta el numero de coches y conductores)
	 * a partir de los coches y conductores de la base de datos.
	 * 
	 */
	private static void asignarCoches(){
		rellenarArrays();
		int contador = 0;
		
		CocheConductorDAO array = new CocheConductorDAO();
		
		while(contador<conductores.size()&&contador<coches.size()){
						
			CocheConductor carDriver = new CocheConductor();
			carDriver.setCoche(coches.get(contador));
		    carDriver.setConductor(conductores.get(contador));
		    carDriver.setFecha(new Date());
			cochesConductores.add(contador,carDriver );
			array.addCocheConductor(carDriver);
			contador++;	
		}
			
		}
	
	/**
	 * En este metodo asignamos una coordenada preestablecida a nuestros conductores.
	 * Con la particularidad de que va a ser u�nica para no tener dos conductores en la misma coordenada.
	 */
	public void asignaUbicacion(){
		asignarCoches();
		UbicacionesConductores.add(0,new Ubicacion(40.452631,-3.690678));
		UbicacionesConductores.add(1,new Ubicacion(40.446786,-3.718433));
		UbicacionesConductores.add(2,new Ubicacion(40.403856,-3.699642));
		UbicacionesConductores.add(3,new Ubicacion(40.407555,-3.711229));
		UbicacionesConductores.add(4,new Ubicacion(40.409444,-3.694170));
		UbicacionesConductores.add(5,new Ubicacion(40.410678,-3.692459));
		UbicacionesConductores.add(6,new Ubicacion(40.404771,-3.702501));
		UbicacionesConductores.add(7,new Ubicacion(40.403987,-3.748335));
		UbicacionesConductores.add(8,new Ubicacion(40.419998,-3.701257));
		UbicacionesConductores.add(9,new Ubicacion(40.446878,-3.799381));
		UbicacionesConductores.add(10,new Ubicacion(40.445008,-3.807813));
		UbicacionesConductores.add(11,new Ubicacion(40.431641,-3.795089));
		UbicacionesConductores.add(12,new Ubicacion(40.465214,-3.866672));
		UbicacionesConductores.add(13,new Ubicacion(40.428636,-3.529185));
		UbicacionesConductores.add(14,new Ubicacion(40.297477,-3.944376));
		UbicacionesConductores.add(15,new Ubicacion(40.332818,-3.396139));
		UbicacionesConductores.add(16,new Ubicacion(40.340801,-3.807813));
		UbicacionesConductores.add(17,new Ubicacion(40.376389,-3.750539));
		UbicacionesConductores.add(18,new Ubicacion(40.431070,-3.638729));
		UbicacionesConductores.add(19,new Ubicacion(40.362762,-3.589505));
		
		int[] noRepetir = new int[20];//Hago un array del tama�o de las ubicaciones esto se modificaria si tenemos mas conductores y ubicaciones
		int i =0;                     //Aqu� almaceno las ubicaciones que ya han salido para no tener dos coches en la misma coordenada.
		
		for(CocheConductor c:cochesConductores ){//Recorro los conductores
			
			int aleatorio =(int) Math.round(Math.random()*19);//Genero un numero entre 20 posibles
			
			int j=0;//inicio el contador j
			
			while (j <i){//Bucle para comparar repetidos
				
				if(aleatorio ==noRepetir[j]){//Si aleatorio esta dentro del array de coordenadas que ya han salido
					
					 aleatorio =(int) Math.round(Math.random()*19);//genero otro aleatorio
					 j=0;// reinicio el contador para volverlo a recorrer y generar uno no repetido
				}
				else j++;//si avanzo esque no es repetido asique aumentamos en 1 la iteracion.
				
			}
			noRepetir[i] = aleatorio;//Almacenamos la coordenada de la iteracion en el bucle para no repetirlo.
			i++;//anazo a la siguiente iteracion
			
			c.getConductor().setUbicacion(UbicacionesConductores.get(aleatorio));//Asigno la coordenada
			
		}
			
	}
	
		
	
	/**
	 * Metodo que aplica una vez aceptado el viaje una probabilidad de incidencia.
	 * En caso de no presentarse ninf�guna incidencia, el viaje se finaliza correctamente.
	 * @param trayecto
	 * @return
	 */
	public boolean aceptarViaje(Viaje trayecto)
	{
		boolean tenemosViaje = true;
		
		int probabilidadIncidencia = (int)Math.round(Math.random()*100000);
		
	    int probabilidad2 = 1;
		
		if (probabilidadIncidencia == probabilidad2){
			tenemosViaje = false;
		}
		else {
			ConductorDAO conductorViaje = new ConductorDAO();
			conductorViaje.comunicaFinTrayecto(trayecto);
			tenemosViaje= true;
		
		}
		return tenemosViaje;
	}
		
	
	/**
	 * Este metodo calcula la distancia entre dos puntos, estos puntos son objetos ubicacion, cuyas coordenadas se encuentran en formato WGS84, latitud y longitud.
	 * La formula empleada es la la formula de Haversine.
	 * @param inicial. La Ubicacion inicial aportada por el cliente.
	 * @param destino. La ubicacion final aportada por el cliente.
	 * @return El resultado del metodo se encuentra ya en kilometros.
	 */
	public static double calculaRuta(Ubicacion ubicacionInicial,Ubicacion ubicacionFinal )
	{
		
		double  PI =3.14159265358979323846;//Utilizamos una aproximacion de PI distinta que la de Math.PI
			
			
		double deg2rad = (PI/180);//pasamos a radianes.
			
			
		double lat1 =ubicacionInicial.getLatitud()*deg2rad;//Obtenemos la latitud
			
		double lon1 = ubicacionInicial.getLongitud()*deg2rad;//Obtenemos la longitud
			
		double lat2 = ubicacionFinal.getLatitud()*deg2rad;
			
		double lon2 = ubicacionFinal.getLongitud()*deg2rad;
			
		double radioTierra = 6378.137;//Radio ecuatorial de la tierra empleado para nuestras aproximaciones.
			
		double distancia = radioTierra*Math.acos(Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1)+
					Math.sin(lat1)*Math.sin(lat2));//F�rmula para clacular la distancia.
		
		distancia = (double)Math.round(distancia * 100d) / 100d;

		return distancia;
	}
		
		
 
    /**
     * Utiliza el metodo calcula ruta, para listar al conductor mas cercano en funcion de la disponibilidad y
     * del tipo de coche selecionado.
     * @param trayecto
     * @return
     */
    public CocheConductor calculaConductorCercano(Viaje trayecto){
      
      //Ponemos una distancia minima con un valor fuera de rango.
	  double distanciaMin = 999999999;
	  boolean  cocheXl= trayecto.getCocheXl();
	  
	  CocheConductor conductorCercano=null;
	  
	// Recorremos el array de CochesConductores 
	  for(CocheConductor c:cochesConductores){
		  //Sacamos el conductor de CocheConductor y comprobamos si esta disponible. Tambien comprobamos que el coche sea del mismo tipo que el del viaje.
		 Conductor conductor = c.getConductor();
		 if(conductor.isDisponible() && c.getCoche().getCocheXl() == cocheXl){
			 //Calculamos la distancia entre el conductor y la ubicacion inicial del cliente  para calcular la distancia minima
			 double distancia = calculaRuta(trayecto.getUbicacionInicial(),conductor.getUbicacion());
			 if(distancia<distanciaMin ){
					distanciaMin =distancia;
					conductorCercano=c;
				}
		 }	
	  }
	  return conductorCercano;		
    }
		
   
   /**
     * Este m�todo calcula el precio del viaje en funci�n de los kilometros, multiplicandolos por una tarifa definida.
     * tamb�n tiene una tarifa m�nima para los viajes demasiado cortos.
     * @param km kilometros pasados por el formulario.
     * @return factura, el precio del viaje en euros.
     */
   public double calculaPrecio(Viaje trayecto){
	   double distancia = trayecto.getDistanciaViaje();
	   double tarifaMinima = 5.50;//en euros
	   double tarifa, factura;
			
		tarifa = 1.75;//o lo que quieras
			
		factura = tarifa*distancia;
		
		if(factura<tarifaMinima){
			
			factura = tarifaMinima;;
		}
		
		factura = (double)Math.round(factura * 100d) / 100d;
		
		return factura;//en euros
		}
   /**
   * M�todo que comprueba si las coordenadas estan dentro del rango de trabajo de la aplicacion.
   * Latitud y longitud superior corresponde a Guadalajara.
   * Latitud y longitud inferior corresponde a Valdemojado
   * @param UbicacionFinal
   * @return Devuelve true o false, en funci�n de si esta dentro de nuestro rango o no.
   */
   public boolean ValidaCoordenadas(Viaje viajeSolicitado)
   {
	   boolean validacion=true;
	   //Limites de accion de nuestros coches en latitud
	   double latitudLimiteSuperior = 40.643413;
	   double latitudLimiteInferior = 40.206427;
	   //Limites de accion de nuestros coches en longitud
	   double longitudLimiteSuperior =-3.177482;
	   double longitudLimiteInferior = -4.100333;
	   Ubicacion ubicacionInicialViaje = viajeSolicitado.getUbicacionInicial();
	   Ubicacion ubicacionFinalViaje = viajeSolicitado.getUbicacionFinal();
	   //Aqui compruebo las coordenadas de la ubicacion final.
	   if(ubicacionFinalViaje.getLatitud()>latitudLimiteSuperior||ubicacionFinalViaje.getLatitud()<latitudLimiteInferior){
		   validacion = false;
	   }
	   if(ubicacionFinalViaje.getLongitud()>longitudLimiteSuperior||ubicacionFinalViaje.getLongitud()<longitudLimiteInferior){
		   validacion = false;
	   }
	   //Aqui compruebo las coordenadas de la ubicacion inicial solicitada por el cliente.
	   if(ubicacionInicialViaje.getLatitud()>latitudLimiteSuperior||ubicacionInicialViaje.getLatitud()<latitudLimiteInferior){
		   validacion = false;
	   }
	   if(ubicacionInicialViaje.getLongitud()>longitudLimiteSuperior||ubicacionInicialViaje.getLongitud()<longitudLimiteInferior){
		   validacion = false;
	   }
	   
	   return validacion;
	   
   }
   
   
   /**
    * en este metodo calculamos el tiempo que tarda el conductor en llegar a la ubicacion inicial de cliente.
 * @param trayecto
 * @param c
 * @return
 */
public int calculaTiempoEspera(Viaje trayecto, CocheConductor c){
		
	   double distancia = calculaRuta(trayecto.getUbicacionInicial(),c.getConductor().getUbicacion()); 
	   double tiempo = 1.2;//Minutos por cada kilometro de distancia
	   
	   int tiempoEspera;
			
		tiempoEspera = (int) Math.round(tiempo*distancia);
		
		    
		return tiempoEspera;//En minutos
		}


public int calculaTiempoRuta(Viaje trayecto){
	
	   double distancia = trayecto.getDistanciaViaje();
	   double tiempo = 1.2;//Minutos por cada kilometro de distancia
	   
	   int tiempoEspera;
			
		 tiempoEspera = (int) Math.round(tiempo*distancia);
		
		
		return tiempoEspera;//En minutos
		}
   
				

	@Override
	public String toString() {
		return "GestionViajes [ubicacionInicial=" + ubicacionInicial + ", ubicacionFinal=" + ubicacionFinal + "]";
	}


		

	

}



