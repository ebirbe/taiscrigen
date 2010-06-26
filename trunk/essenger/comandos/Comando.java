package essenger.comandos;

public class Comando {
	public static final String CABECERA = ">CMD<";
	public static final String MENSAJE = "MENSAJE=";
	public static final String NOMBRE = "NOMBRE=";
	public static final String LOGIN = "LOGIN=";
	public static final String CONECTADOS = "CONECTADOS=";
	public static final String NOMBRE_ACEPTADO = "NOMBRE_ACEPTADO";
	public static final String NOMBRE_RECHAZADO = "NOMBRE_RECHAZADO";
	
	public static String hacerMensaje(String msg){
		return CABECERA+MENSAJE+msg;
	}
	public static String hacerNombre(String msg){
		return CABECERA+NOMBRE+msg;
	}
	public static String hacerLogin() {
		return CABECERA+LOGIN;
	}
	public static String hacerConectados() {
		return CABECERA+CONECTADOS;
	}
}
