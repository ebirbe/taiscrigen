package gps.interprete;

public class ComandoEV extends Comando {

	private String codigoEvento, fecha, diaDeSemana, hora, latitud, longitud,
			velocidad, direccion, edad, fuente;

	public String getCodigoEvento() {
		return codigoEvento;
	}

	public void setCodigoEvento(String codigoEvento) {
		this.codigoEvento = codigoEvento;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String semana) {
		this.fecha = semana;
	}

	public String getDiaDeSemana() {
		return diaDeSemana;
	}

	public void setDiaDeSemana(String diaDeSemana) {
		this.diaDeSemana = diaDeSemana;
	}

	public String getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad = velocidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public void setEdad(String edad) {
		this.edad = edad;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}

	public String getHora() {
		return hora;
	}

	public String getLatitud() {
		return latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public String getFuente() {
		return fuente;
	}

	public String getEdad() {
		return edad;
	}

	public ComandoEV(String texto) {
		super(texto);
	}

	@Override
	public void quitarPrevios() {
		// Eliminamos REV
		texto = texto.substring(3);
	}

	@Override
	public void desglosarComando() {
		// AA BBBB C DDDDD EEEFFFFF GGGGHHHHH III JJJ K L [EXTENDED-EV TAGS]
		codigoEvento = texto.substring(0, 2);
		fecha = texto.substring(2, 12);
		diaDeSemana = texto.substring(6, 7);
		hora = calcularHora(texto.substring(7, 12));
		latitud = texto.substring(12, 20);
		longitud = texto.substring(20, 29);
		velocidad = texto.substring(29, 32);
		direccion = texto.substring(32, 35);
		fuente = texto.substring(35, 36);
		edad = texto.substring(36, 37);
	}

	@Override
	public String imprimir() {

		impresion = "";
		impresion += "Evento: " + codigoEvento + "\n";
		impresion += "Fecha: " + calcularFecha(fecha) + "\n";
		impresion += "Latitud: " + latitud + "\n";
		impresion += "Longitud: " + longitud + "\n";
		impresion += "Velocidad: " + obtenerVelocidad(velocidad) + " Km/h\n";
		impresion += "Direccion: " + Integer.parseInt(direccion) + "º ("
				+ obtenerDireccion(direccion) + ")\n";
		impresion += "Fuente: " + obtenerFuente(fuente) + "\n";
		impresion += "Edad: " + obtenerEdad(edad) + "\n";

		return impresion;
	}
}
