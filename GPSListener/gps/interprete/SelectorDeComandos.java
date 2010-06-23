package gps.interprete;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SelectorDeComandos {
	private String texto;
	private boolean valido = false;

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) throws ExcepcionComandoInvalido {
		this.texto = texto;
		comprobarComando();
	}

	public boolean isValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

	// Constructores
	public SelectorDeComandos() {
		texto = "";
	}

	/**
	 * Crea el objeto SelectorDeComando.
	 * @param texto
	 * @throws ExcepcionComandoInvalido 
	 */
	public SelectorDeComandos(String texto) throws ExcepcionComandoInvalido {
		this.texto = texto.trim();
		comprobarComando();
	}

	/**
	 * Verifica que sea un comando TAIP valido
	 * @throws ExcepcionComandoInvalido
	 */
	private void comprobarComando() throws ExcepcionComandoInvalido {
		if(texto.indexOf(">") == -1 && texto.indexOf("<") ==-1){
			throw new ExcepcionComandoInvalido(texto, "Comandos TAIP deben contener > y <");
		}else if(texto.indexOf(">") != 0 && texto.indexOf("<") != texto.length()-2){
			throw new ExcepcionComandoInvalido(texto, "> o < estan mal ubicados");
		}else{
			valido = true;
		}
	}

	public Comando seleccionarComando() {

		if(texto.indexOf(">RCP") != -1){
			// CP
			return new ComandoCP(texto);
		}else if(texto.indexOf(">REV") != -1){
			// EV
			return new ComandoEV(texto);
		}else if(texto.indexOf(">RXADM1") != -1){
			// XADM1
			return new ComandoXADM1(texto);
		}else{
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Escribe la respuesta emitida por el GPS:");
			String línea = br.readLine(); 
			SelectorDeComandos s = new SelectorDeComandos(línea);
			Comando c = s.seleccionarComando();
			
			if(c != null)
				System.out.println(c.imprimir());
			else
				System.out.println("El mensaje no es reconocido.");
			
			System.out.println();
			System.out.println("-------");
			System.out.println("Version de Prueba.");
			System.out.println("-------");
		} catch (ExcepcionComandoInvalido e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
