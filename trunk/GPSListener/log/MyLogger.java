package log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class MyLogger {
	private static boolean inicializado = false;
	private static String nombreDirecotrio = "logs";
	private static File directorio;
	private static Date d;
	private static String nombreFichero;
	private static File fichero;
	private static BufferedWriter buffWriter;


	@SuppressWarnings("deprecation")
	private synchronized static void inicializarLog(){
		if (inicializado) return;
		try {
			directorio = new File(nombreDirecotrio); //Creas un nuevo directorio
			directorio.mkdirs();
			directorio.setWritable(true);
			// This block configure the logger with handler and formatter
			d = new Date();
			nombreFichero = directorio.toString()+File.separator+(1900+d.getYear())+"-"+(d.getMonth()+1)+"-"+d.getDate()+".log";
			fichero = new File(nombreFichero);
			buffWriter = new BufferedWriter(new FileWriter(fichero,true));
			inicializado = true;
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	private static void comprobarFecha() {
		if(d == null) return;
		if(
				d.getDate() != new Date().getDate() ||
				d.getMonth() != new Date().getMonth() ||
				d.getYear() != new Date().getYear()
		){
			try {
				if(buffWriter != null){
					buffWriter.close();
				}
				inicializado = false;
			} catch (IOException e) {e.printStackTrace();}
		}
	}

	public synchronized static void escribirLog(String origen, String msg){
		comprobarFecha();
		inicializarLog();
		String escribir;
		
		try {
			if(!msg.endsWith("\n")) msg += "\n";
			escribir = "["+new Date()+":"+origen+"]\t"+msg;
			System.out.print(escribir);
			buffWriter.write(escribir);
			buffWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try{
			int t = 0/0;
			System.out.println(t);
		}catch (Exception e) {
			System.err.println("HOLA");
			e.printStackTrace();
			MyLogger.escribirLog(MyLogger.class.getName(),e.toString());
			MyLogger.escribirLog(MyLogger.class.getName(),"ERICK");
			MyLogger.escribirLog(MyLogger.class.getName(),"ERICK\n");
			MyLogger.escribirLog(MyLogger.class.getName(),"YO");
		}
	}
}
