package gps.interprete;

public class ComandoCP extends Comando {
	
	private String hora, latitud, longitud, fuente, edad;
	
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

	public ComandoCP(String texto) {
		super(texto);
	}

	@Override
	public void quitarPrevios() {
		// Eliminamos RED
		texto = texto.substring(3);
	}

	@Override
	public void desglosarComando() {
		hora = calcularHora(texto.substring(0,5));
		latitud = texto.substring(5,12);
		longitud = texto.substring(12,20);
		fuente = texto.substring(20,21);
		edad = texto.substring(21,22);
	}
	
	@Override
	public String imprimir() {
		
		impresion = "Hora: "+hora+"\n";
		impresion += "Latitud: "+latitud+"\n";
		impresion += "Longitud: "+longitud+"\n";

		impresion += "Fuente: "+obtenerFuente(fuente)+"\n";
		impresion += "Edad: "+obtenerEdad(edad)+"\n";
		
		return impresion;
	}
}
