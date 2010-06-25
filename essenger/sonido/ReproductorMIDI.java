package essenger.sonido;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class ReproductorMIDI {
	public ReproductorMIDI() {
		try
		{

			File f2=new File("/home/desarrollo/TheMatrix.mid");
			Sequence S=MidiSystem.getSequence(f2);
			Sequencer seq=MidiSystem.getSequencer();
			seq.open();
			seq.setSequence(S);
			seq.start();
			System.out.println("Sorpresa para todos mis coetáneos");
			System.out.println("Apretar CTRL-C para interrumpir");



		}
		catch(MidiUnavailableException ecc){
			ecc.printStackTrace();
		}
		catch(InvalidMidiDataException ecc2){
			ecc2.printStackTrace();
		}
		catch(IOException ecc3){
			ecc3.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ReproductorMIDI();
	}
}
