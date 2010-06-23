package data;

import java.util.Collection;
import java.util.Vector;

public class LineVector extends Vector<String> {

	
	private static final long serialVersionUID = -7864386623594770658L;

	public LineVector() {
	}

	public LineVector(int initialCapacity) {
		super(initialCapacity);
	}

	public LineVector(Collection<? extends String> c) {
		super(c);
	}

	public LineVector(int initialCapacity, int capacityIncrement) {
		super(initialCapacity, capacityIncrement);
	}
	
	/**
	 * Agrega una linea individual
	 * @param line
	 * @return
	 */
	public synchronized boolean add(Line line) {
		return super.add(line.text  + "\n");
	}
	
	/**
	 * Agrega una colección de líneas
	 * @param Lines
	 * @return
	 */
	public synchronized boolean add(LineVector Lines) {
		return super.addAll(Lines);
	}
	/**
	 * Agrega un comentario
	 * @param comment
	 */
	public void addComment(String comment){
		this.add(new Comment(comment));
	}
	
	/**
	 * Agrega una sentencia
	 * @param sentence
	 */
	public void addSentence(String sentence){
		this.add(new Sentence(sentence));
	}
	
	/**
	 * Agrega una linea en blanco
	 */
	public void addNewLine(){
		this.add(new Line(""));
	}
}
