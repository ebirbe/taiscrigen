package essenger.sonido;
import java.io.File;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;


import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReproductorWAV {
	File temp;
	private Clip ol;
	private AudioInputStream ais;
	private AudioFileFormat aff;
	private AudioFormat af;
	private DataLine.Info info;
	public ReproductorWAV() {

		crearArchivoTemporal();
	}

	private void crearArchivoTemporal() {
		//Crear Archivo temporal
		try{
			String rutaCarpetaTemp = System.getProperty("java.io.tmpdir")+File.separator+"essenger"+File.separator;
			File directorio = new File(rutaCarpetaTemp); //Creas un nuevo directorio en la carpeta temporal.
			directorio.mkdirs();
			directorio.setWritable(true);

			//copias la direccion final del archivo
			String archivo = directorio.getCanonicalPath() + File.separator + "click";
			//nuevo archivo en esa direccion
			temp = new File(archivo);

			InputStream tmpImpStr = ReproductorWAV.class.getClassLoader().getResourceAsStream("essenger/sonido/click.wav");
			FileOutputStream archivoDestino = new FileOutputStream(temp);
			FileWriter fw = new FileWriter(temp);
			byte[] buffer = new byte[512*1024];
			//lees el archivo hasta que se acabe...
			int nbLectura;
			while ((nbLectura = tmpImpStr.read(buffer)) != -1)
				archivoDestino.write(buffer, 0, nbLectura);
			//cierras el archivo,el inputS y el FileW
			fw.close();
			archivoDestino.close();
			tmpImpStr.close();
			//abres el archivo temporal
			//Desktop.getDesktop().open(temp);
			////////////////////////////////////////
		}
		catch(IOException ea){ea.printStackTrace();}
	}
	public synchronized void reproducir(){
		try {
			aff=AudioSystem.getAudioFileFormat(temp);
			ais=AudioSystem.getAudioInputStream(temp);
			af=aff.getFormat();
			info = new DataLine.Info(
					Clip.class,
					ais.getFormat(),
					((int) ais.getFrameLength() * af.getFrameSize())
			);
			ol = (Clip) AudioSystem.getLine(info);
			ol.open(ais);
			ol.loop(0);
			//Clip.LOOP_CONTINUOUSLY
		}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();}
	}
	public static void main(String[] args) {
		ReproductorWAV a = new ReproductorWAV();
		a.reproducir();
	}
}
