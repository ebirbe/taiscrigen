package contadorBytes;

import gps.interprete.ExcepcionComandoInvalido;
import gps.interprete.SelectorDeComandos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import log.MyLogger;

public class Contador {
	private static BufferedWriter buffWriter;
	private static BufferedReader buffReader;
	private static String id;
	private static String nombreFichero;
	private static File fichero;
	private static File dir;

	public static synchronized void contar(String s) {
		String[] v = dividirMensaje(s);
		for (int i = 0; i < v.length; i++) {
			comprobarMensaje(v[i]);
			crearCarpeta();
			abrirArchivo();
			int anterior = leerArchivo();
			int actual = numeroBytes(v[i]);
			escribirArchivo(anterior + actual);
		}
	}

	private static void escribirArchivo(int i) {
		try {
			buffWriter = new BufferedWriter(new FileWriter(fichero));
			buffWriter.append(Integer.toString(i));
			buffWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
			MyLogger.escribirLog(Contador.class.getName(), e.toString());
		}
	}

	private static void abrirArchivo() {
		nombreFichero = dir + File.separator + id;
		fichero = new File(nombreFichero.trim());
	}

	private static void crearCarpeta() {
		dir = new File("count");
		dir.mkdirs();
		dir.setWritable(true);
	}

	private static String comprobarMensaje(String s) {
		id = s;
		try {
			SelectorDeComandos sc = new SelectorDeComandos(s);
			s = sc.getTexto();
			id = s.substring(s.lastIndexOf(";ID=") + 4, s.lastIndexOf("<"));
		} catch (ExcepcionComandoInvalido e1) {
		}
		return s;
	}

	private static int leerArchivo() {
		String cont = "";
		int i = 0;
		try {
			buffReader = new BufferedReader(new FileReader(fichero));
			cont = buffReader.readLine();
			i = Integer.parseInt(cont);
			buffReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			MyLogger.escribirLog(Contador.class.getName(), e.toString());
		} catch (IOException e) {
			e.printStackTrace();
			MyLogger.escribirLog(Contador.class.getName(), e.toString());
		}
		return i;
	}

	private static int numeroBytes(String s) {
		MyLogger.escribirLog(Contador.class.getName(),
				Integer.toString(s.length()));
		return s.length();
	}

	public static String[] dividirMensaje(String s) {
		Pattern patron = Pattern.compile(">*<");
		String[] v = patron.split(s);
		for (int i = 0; i < v.length; i++) {
			if (v[i].indexOf(">") != -1) {
				v[i] = v[i].concat("<");
			}
		}
		return v;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String s = ">REV011591248634+1016377-0674499600000012;ID=3205<>REV011591248634+1016377-0674499600000012;ID=3205<3201";
		String[] v = dividirMensaje(s);
		for (int i = 0; i < v.length; i++) {
			System.out.println(v[i]);
		}
	}
}
