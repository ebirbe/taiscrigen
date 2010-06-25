package essenger.sonido;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReproductorWAV {

	public ReproductorWAV() {
		File sf=new File("/home/desarrollo/click.wav");
		AudioFileFormat aff;
		AudioInputStream ais;
		try
		{
			aff=AudioSystem.getAudioFileFormat(sf);
			ais=AudioSystem.getAudioInputStream(sf);
			AudioFormat af=aff.getFormat();
			DataLine.Info info = new DataLine.Info(
					Clip.class,
					ais.getFormat(),
					((int) ais.getFrameLength() *
							af.getFrameSize())
			);

			Clip ol = (Clip) AudioSystem.getLine(info);
			ol.open(ais);
			ol.loop(0);
			//Clip.LOOP_CONTINUOUSLY
			System.out.println("reprodución empezada, apretar CTRL-C para interrumpir");

		}
		catch(UnsupportedAudioFileException ee){}
		catch(IOException ea){}
		catch(LineUnavailableException LUE){};
	}
	public static void main(String[] args) {
		new ReproductorWAV();
	}
}
