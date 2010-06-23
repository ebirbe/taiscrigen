package gps.interprete;

import java.text.DecimalFormat;
import java.util.Date;

public abstract class Comando {
	String texto;
	protected String impresion;
	public String getTexto() {
		return texto;
	}
	
	public Comando(String texto) {
		this.texto = texto;
		quitarInicioFin();
		desglosarComando();
	}

	public void quitarInicioFin(){
		texto = texto.substring(1, texto.length()-1);
		quitarPrevios();
	}
	
	public String calcularHora(String segundos){
		String tiempo=null;
		Float t;
		Integer h,m,s;
		
		try {
			
			t = Float.parseFloat(segundos);
			
			//Restar Uso horario de Venezuela
			// GMT -4.30
			t -= ((4*3600)+(30*60));
			// eliminamos las horas negativas
			if(t<0) t+=(12*(3600));
			
			// Obtenemos la hora
			h = (int)(t / 3600);
			tiempo = rellenarNumero(h)+":";
			
			// convertimos los segundos a minutos
			// y le restamos las horas q ya estan calculadas
			// (transformadas en minutos)
			m = (int)(t / 60) - (60*h);
			tiempo += rellenarNumero(m)+":";
			
			// Le restamos a los segundos las horas y los minutos q ya
			// estan calculadas (transformados en segundos)
			s = (int)(t - (h*3600) - (m*60));
			tiempo += rellenarNumero(s)+"";
			
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		return tiempo;
	}
	
	private String rellenarNumero(int i){
		if(i < 10 && i >= 0)
			return "0"+i;
		else
			return Integer.toString(i);
	}
	
	@SuppressWarnings("deprecation")
	protected String calcularFecha(String semana2) {
		String f = "";
		
		long sem = Integer.parseInt(semana2.substring(0,4));
		long dias = Integer.parseInt(semana2.substring(4,5));
		long segundos = Integer.parseInt(semana2.substring(5,10));
		long mili = 0;
		
		// convertimos a milisegundos
		sem *= 7 * 24 * 60 * 60 * 1000;
		dias *= 24 * 60 * 60 * 1000;
		segundos *= 1000;
		mili = (sem + dias + segundos);
		
		// Inicio de Relog GPS Sunday January 06 1980
		Date FechaInicioGPS = new Date(Date.UTC(1980-1900, 0, 6, 0, 0, 0));
		
		// Fecha de evento
		Date d = new Date(mili + FechaInicioGPS.getTime());
		
		f = d.toString();
		return f;
	}
	
	protected String obtenerEstadoRastreo(String estadoTracking2) {

		if(estadoTracking2.equals("0")) return "Posicion Fijada (Fixed).";
		if(estadoTracking2.equals("1")) return "No tiene aun el tiempo GPS.";
		if(estadoTracking2.equals("2")) return "No usado.";
		if(estadoTracking2.equals("3")) return "PDOP es muy alto.";
		if(estadoTracking2.equals("8")) return "No hay satelites usables.";
		if(estadoTracking2.equals("9")) return "Solo 1 satelite.";
		if(estadoTracking2.equals("A")) return "Solo 2 satelites.";
		if(estadoTracking2.equals("B")) return "Solo 3 satelites.";
		if(estadoTracking2.equals("C")) return "El satelite seleccionado no es usable.";
		
		return "Desconocido("+estadoTracking2+").";
		
	}
	
	protected String obtenerDireccion(String direccion2) {
		int dir = Integer.parseInt(direccion2);
		if(dir >= 315 || dir <= 45) return "Norte";
		if(dir >= 45 || dir <= 135) return "Este";
		if(dir >= 135 || dir <= 225) return "Sur";
		if(dir >= 135 && dir >= 315) return "Oeste";
		return null;
	}

	protected String obtenerVelocidad(String velocidad2) {
		int v = Integer.parseInt(velocidad2);
		DecimalFormat df = new DecimalFormat("0.0");
		Double kph = (v / 0.62);
		kph = Math.floor(kph);
		return df.format(kph);
	}
	
	protected String obtenerFuente(String fuente){
		String ft = "";
		if(fuente.equals("0")) ft = "2D GPS";
		if(fuente.equals("1")) ft = "3D GPS";
		if(fuente.equals("2")) ft = "2D DGPS";
		if(fuente.equals("3")) ft = "3D DGPS";
		if(fuente.equals("6")) ft = "DR";
		if(fuente.equals("8")) ft = "DR Degradado";
		if(fuente.equals("9")) ft = "Desconocido";
		return ft;
	}
	
	protected String obtenerEdad(String edad) {
		String ed = "";
		if(edad.equals("0"))	ed = "No disponible.";
		if(edad.equals("1"))	ed = "Viejo, > 10 segundos.";
		if(edad.equals("2"))	ed = "Fresco, < 10 segundos.";
		return ed;
	}
	
	public abstract void quitarPrevios();

	public abstract void desglosarComando();
	
	public abstract String imprimir();
	
	public static void main(String[] args) {
		System.out.println("Borré lo estatico.");
	}
}
