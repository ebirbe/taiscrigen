import java.awt.Frame;
import java.awt.Window;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import assembler.quickScript.ui.MainUI;

import creator.FileCreator;
import data.Comment;
import data.LineVector;
import data.Sentence;


public class StartConsole {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			try {
				UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(LogicalOperator.AND);
		LineVector lines = new LineVector();
		lines.add(new Comment("Probando la generacion de Comentarios"));
		lines.add(new Comment("Con varias lineas para ello"));
		lines.add(new Comment("Y Sentencias"));
		lines.add(new Sentence("SNV000010011+SND00120012"));
		lines.add(new Sentence("COOL"));
		
		FileCreator creator = new FileCreator();
		try {
			creator.createFile(lines);
			System.out.println("Archivo Creado.");
		} catch (IOException e) {
			System.err.println("Ocurrio un problema al guardar el archivo.");
			//e.printStackTrace();
		}
	}

}
