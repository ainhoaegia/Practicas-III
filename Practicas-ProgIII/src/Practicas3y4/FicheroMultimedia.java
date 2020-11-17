package Practicas3y4;

import java.io.File;
import java.io.Serializable;

/** Clase para gestionar objetos con la informaci�n completa de un fichero multimedia
 * para nuestro reproductor-catalogador.
 * Usa campos p�blicos en lugar de set/gets.
 * @author Andoni Egu�luz Mor�n
 * Facultad de Ingenier�a - Universidad de Deusto
 */
public class FicheroMultimedia implements Serializable{
	
	/**Serializable
	 */
	private static final long serialVersionUID = 1L;
	public File file;          // Fichero
	public boolean erroneo;    // �No se puede reproducir?
	public String titulo;      // T�tulo de la canci�n (si procede)
	public String cantante;    // Cantante(s) de la canci�n (si procede)
	public String comentarios; // Comentarios al fichero multimedia

	/** Constructor de fichero multimedia
	 * @param file	Fichero
	 * @param erroneo	true si no se puede reproducir
	 * @param titulo	T�tulo de la canci�n ("" por defecto)
	 * @param cantante	Cantante de la canci�n ("" por defecto)
	 * @param comentarios	Comentarios al fichero multimedia ("" por defecto)
	 */
	public FicheroMultimedia(File file, boolean erroneo, String titulo,
			String cantante, String comentarios) {
		super();
		this.file = file;
		this.erroneo = erroneo;
		this.titulo = (titulo==null?"":titulo);
		this.cantante = (cantante==null?"":cantante);
		this.comentarios = (comentarios==null?"":comentarios);
	}
	
	/** Constructor de fichero multimedia, con error a falso y resto de atributos a ""
	 * @param file	Fichero
	 */
	public FicheroMultimedia(File file) {
		this( file, false, "", "", "" );
	}

}
