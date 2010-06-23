package gps.interprete;

public class ExcepcionComandoInvalido extends Exception {
	private static final long serialVersionUID = -4800735192418216920L;
	String comando;
	String causa;
	
	public String getCausa() {
		return causa;
	}
	public String getComando() {
		return comando;
	}
	
	// Constructors
	public ExcepcionComandoInvalido(String comando, String causa) {
		this.comando = comando;
		this.causa = causa;
	}
	
	@Override
	public String getMessage() {
		return "Comando TAIP no reconocido";
	}
}
