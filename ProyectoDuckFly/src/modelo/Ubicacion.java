package modelo;

/**
 * @author Ana Alvarez Lodeiro - German Sandoval
 * Fecha: 26/11/18
 * Atributos que va a tener la clase Ubicacion
 */
public class Ubicacion {
	private Double latitud;
	private Double longitud;
	
	/**
	 * METODO CONSTRUCTOR SIN PARAMETROS
	 */
	public Ubicacion() {
		
	}
	/**
	 * METODO CONSTRUCTOR Pasamos por parametro los siguientes atributos
	 * @param latitud
	 * @param longitud
	 */
	public Ubicacion(Double latitud, Double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	/**
	 * METODOS GET
	 * @return latitud
	 * @return longitud
	 * Nos devuelve el valor de los atributos
	 */
	public Double getLatitud() {
		return latitud;
	}
	public Double getLongitud() {
		return longitud;
	}
	/**
	 * METODOS SET
	 * Nos permite modificar el valor de los atributos
	 */
	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	/**
	 * A CONTINUACION IRAN LOS DIFERENTES METODOS QUE NECESITEMOS
	 */
	
}
