package Practicas3y4;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.*;
import java.util.regex.*;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/** Clase para crear instancias como listas de reproducci�n,
 * que permite almacenar listas de ficheros con posici�n de �ndice
 * (al estilo de un array / arraylist)
 * con marcas de error en los ficheros y con m�todos para cambiar la posici�n
 * de los elementos en la lista, borrar elementos y a�adir nuevos.
 * @author Andoni Egu�luz Mor�n
 * Facultad de Ingenier�a - Universidad de Deusto
 */
public class ListaDeReproduccion implements ListModel<String>, Serializable {
	
	/**Serializable
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<File> ficherosLista;        // ficheros de la lista de reproducci�n
	ArrayList<Boolean> ficherosErroneos;  // ficheros de esa lista que son err�neos (true-false para cada uno)
	int ficheroEnCurso = -1;   // Fichero seleccionado (-1 si no hay ninguno seleccionado)

	private static final boolean ANYADIR_A_FIC_LOG = false;  // poner true para hacer append en cada ejecuci�n
	
	// Logger de la clase
	private static Logger logger = Logger.getLogger( ListaDeReproduccion.class.getName() );
	static {
		try {
			logger.setLevel( Level.FINEST );
			Formatter f = new SimpleFormatter() {
				@Override
				public synchronized String format(LogRecord record) {
					// return super.format(record);  // Si no queremos el formateador con tanta informaci�n
					if (record.getLevel().intValue()<Level.CONFIG.intValue())
						// Si es menor que CONFIG lo sacamos muy tabulado a la derecha
						return "\t\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					if (record.getLevel().intValue()<Level.WARNING.intValue())
						// Si es menor que WARNING lo sacamos tabulado a la derecha
						return "\t(" + record.getLevel() + ") " + record.getMessage() + "\n";
					return "(" + record.getLevel() + ") " + record.getMessage() + "\n";
				}
			};
			FileOutputStream fLog = new FileOutputStream( ListaDeReproduccion.class.getName()+".log" , ANYADIR_A_FIC_LOG );
			Handler h = new StreamHandler( fLog, f );
			h.setLevel( Level.FINEST );
			logger.addHandler( h );  // Saca todos los errores a out
			logger.addHandler( new FileHandler( ListaDeReproduccion.class.getName()+".log.xml", ANYADIR_A_FIC_LOG ));
		} catch (SecurityException | IOException e) {
			logger.log( Level.SEVERE, "No se ha podido crear fichero de log en clase ListaDeReproduccion" );
		}
		logger.log( Level.INFO, "" );
		logger.log( Level.INFO, DateFormat.getDateTimeInstance( DateFormat.LONG, DateFormat.LONG ).format( new Date() ) );
	}
	
	/** Crea una lista de reproducci�n vac�a
	 */
	public ListaDeReproduccion() {
		ficherosLista = new ArrayList<File>();
		ficherosErroneos = new ArrayList<Boolean>();
	}

	/** A�ade a la lista de reproducci�n todos los ficheros que haya en la 
	 * carpeta indicada, que cumplan el filtro indicado.
	 * Si hay cualquier error, la lista de reproducci�n queda solo con los ficheros
	 * que hayan podido ser cargados de forma correcta.
	 * @param carpetaFicheros	Path de la carpeta donde buscar los ficheros
	 * @param filtroFicheros	Filtro del formato que tienen que tener los nombres de
	 * 							los ficheros para ser cargados.
	 * 							String con cualquier letra o d�gito. Si tiene un asterisco
	 * 							hace referencia a cualquier conjunto de letras o d�gitos.
	 * 							Por ejemplo p*.* hace referencia a cualquier fichero de nombre
	 * 							que empiece por p y tenga cualquier extensi�n.
	 * @return	N�mero de ficheros que han sido a�adidos a la lista
	 */
	public int add(String carpetaFicheros, String filtroFicheros) {
		int ficsAnyadidos = 0;
		if (carpetaFicheros!=null) {
			logger.log( Level.INFO, "A�adiendo ficheros con filtro " + filtroFicheros );
			try {
				filtroFicheros = filtroFicheros.replaceAll( "\\.", "\\\\." );  // Pone el s�mbolo de la expresi�n regular \. donde figure un .
				filtroFicheros = filtroFicheros.replaceAll( "\\*", ".*" );  // Pone el s�mbolo de la expresi�n regular .* donde figure un *
				logger.log( Level.INFO, "expresi�n regular del filtro: " + filtroFicheros );
				anyadeFicheros( carpetaFicheros, filtroFicheros );
			} catch (PatternSyntaxException e) {
				logger.log( Level.SEVERE, "Error en patr�n de expresi�n regular ", e );
			}
		}
		logger.log( Level.INFO, "ficheros a�adidos: " + ficsAnyadidos );
		return ficsAnyadidos;
	}
	
	/** Devuelve uno de los ficheros de la lista
	 * @param posi	Posici�n del fichero en la lista (de 0 a size()-1)
	 * @return	Devuelve el fichero en esa posici�n
	 * @throws IndexOutOfBoundsException	Si el �ndice no es v�lido
	 */
	public File getFic( int posi ) throws IndexOutOfBoundsException {
		return ficherosLista.get( posi );
	}
	
	public FicheroMultimedia getFicMM( int posi ) throws IndexOutOfBoundsException {
		File f = new File("fichero");
		FicheroMultimedia fMM = new FicheroMultimedia( f );
		return fMM;
	}
	
	/** Intercambia los dos ficheros indicados de la lista. 
	 * Si alguna de las posiciones es incorrecta, no hace nada.
	 * @param posi1	Posici�n en la lista de primer fichero (0 a size()-1)
	 * @param posi2	Posici�n en la lista de primer fichero (0 a size()-1)
	 */
	public void intercambia( int posi1, int posi2 ) {
		if (posi1<0 || posi2<0 || posi1>=ficherosLista.size() || posi2>ficherosLista.size())
			return;
		File temp = ficherosLista.get(posi1);
		ficherosLista.set( posi1, ficherosLista.get(posi2) );
		ficherosLista.set( posi2, temp );
		boolean tempB = ficherosErroneos.get(posi1);
		ficherosErroneos.set( posi1, ficherosErroneos.get(posi2) );
		ficherosErroneos.set( posi2, tempB );
	}
	
	/** Devuelve el n�mero de ficheros de la lista.
	 * @return	N�mero de ficheros, 0 si est� vac�a.
	 */
	public int size() {
		return ficherosLista.size();
	}
	
	/** A�ade un fichero al final de la lista
	 * @param f	Fichero a a�adir
	 */
	public void add( File f ) {
		ficherosLista.add( f );
		ficherosErroneos.add( false );
		avisarAnyadido( ficherosLista.size()-1 );
	}
	
	/** Elimina un fichero de la lista, dada su posici�n
	 * @param posi	Posici�n del elemento a borrar
	 */
	public void removeFic( int posi ) throws IndexOutOfBoundsException {
		ficherosLista.remove( posi );
		ficherosErroneos.remove( posi );
	}
	
	/** Borra los datos de la lista de reproducci�n
	 */
	public void clear() {
		ficherosLista.clear();
		ficherosErroneos.clear();
	}
	
	//
	// M�todos de selecci�n
	//
	
	/** Seleciona el primer fichero no err�neo de la lista de reproducci�n
	 * @return	true si la selecci�n es correcta, false si hay error y no se puede seleccionar
	 */
	public boolean irAPrimero() {
		ficheroEnCurso = 0;  // Inicia
		while (ficheroEnCurso<ficherosLista.size() && ficherosErroneos.get(ficheroEnCurso))
			ficheroEnCurso++;  // Y si es err�neo busca el siguiente
		if (ficheroEnCurso>=ficherosLista.size()) {
			ficheroEnCurso = -1;  // Si no se encuentra, no hay selecci�n
			return false;  // Y devuelve error
		}
		return true;
	}
	
	/** Seleciona el �ltimo fichero no err�neo de la lista de reproducci�n
	 * @return	true si la selecci�n es correcta, false si hay error y no se puede seleccionar
	 */
	public boolean irAUltimo() {
		ficheroEnCurso = ficherosLista.size()-1;  // Inicia al final
		while (ficheroEnCurso>=0 && ficherosErroneos.get(ficheroEnCurso))
			ficheroEnCurso--;  // Y si es err�neo busca el anterior
		if (ficheroEnCurso==-1) {  // Si no se encuentra, no hay selecci�n
			return false;  // Y devuelve error
		}
		return true;
	}

	/** Seleciona el anterior fichero no err�neo de la lista de reproducci�n
	 * @return	true si la selecci�n es correcta, false si hay error y no se puede seleccionar
	 */
	public boolean irAAnterior() {
		if (ficheroEnCurso>=0) ficheroEnCurso--;
		while (ficheroEnCurso>=0 && ficherosErroneos.get(ficheroEnCurso))
			ficheroEnCurso--;  // Si es err�neo busca el anterior
		if (ficheroEnCurso==-1) {  // Si no se encuentra, no hay selecci�n
			return false;  // Y devuelve error
		}
		return true;
	}

	/** Seleciona el siguiente fichero no err�neo de la lista de reproducci�n
	 * @return	true si la selecci�n es correcta, false si hay error y no se puede seleccionar
	 */
	public boolean irASiguiente() {
		ficheroEnCurso++;
		while (ficheroEnCurso<ficherosLista.size() 
				&& ficherosErroneos.get(ficheroEnCurso))
			ficheroEnCurso++;  // Si es err�neo busca el siguiente
		if (ficheroEnCurso>=ficherosLista.size()) {
			ficheroEnCurso = -1;  // Si no se encuentra, no hay selecci�n
			return false;  // Y devuelve error
		}
		return true;
	}

	/** Seleciona el fichero indicado de la lista de reproducci�n
	 * @return	true si la selecci�n es correcta, false si hay error y no se puede seleccionar
	 */
	public boolean irA( int posi ) {
		ficheroEnCurso = posi;
		while (ficheroEnCurso<ficherosLista.size() && ficherosErroneos.get(ficheroEnCurso))
			ficheroEnCurso++;  // Si es err�neo busca el siguiente
		if (ficheroEnCurso>=ficherosLista.size()) {
			ficheroEnCurso = -1;  // Si no se encuentra, no hay selecci�n
			return false;  // Y devuelve error
		}
		return true;
	}
	
	/** Devuelve el fichero seleccionado de la lista
	 * @return	Posici�n del fichero seleccionado en la lista de reproducci�n (0 a n-1), -1 si no lo hay
	 */
	public int getFicSeleccionado() {
		return ficheroEnCurso;
	}
	
		private static Random genAleat = new Random();
	/** Selecciona un fichero aleatorio de la lista de reproducci�n.
	 * @return	true si la selecci�n es correcta, false si hay error y no se puede seleccionar
	 */
	public boolean irARandom() {
		if (ficherosLista.size()==0) {
			ficheroEnCurso = -1;
			return false;   // Error
		}
		for(int i=0; i<500; i++) {  // Como m�ximo lo hace 500 veces (para evitar bucles infinitos por aleatoriedad o por muchos o todos los ficheros err�neos)
			ficheroEnCurso = genAleat.nextInt( ficherosLista.size() );
			if (!ficherosErroneos.get(ficheroEnCurso))
				return true;  // Si no es err�neo, se va a esta selecci�n. Si lo es, se vuelve a intentar
		}
		return false;
	}
	
	//
	// M�todos de ficheros err�neos
	//
	
	/** Marca el fichero como err�neo
	 * @param posi	Posici�n del fichero (0 - size()-1)
	 * @param erroneo	Indicaci�n de si es err�neo (true) o no (false)
	 * @throws IndexOutOfBoundsException	Error si el �ndice no est� en el rango correcto
	 */
	public void setFicErroneo( int posi, boolean erroneo ) throws IndexOutOfBoundsException {
		ficherosErroneos.set( posi, erroneo );
	}
	
	/** Devuelve la informaci�n de si es o no err�neo el fichero indicado de la lista
	 * @param posi	Posici�n del fichero (0 - size()-1)
	 * @return	true si es err�neo, false si no
	 * @throws IndexOutOfBoundsException	Error si el �ndice no est� en el rango correcto
	 */
	public boolean isErroneo( int posi ) throws IndexOutOfBoundsException {
		return ficherosErroneos.get(posi);
	}

	//
	// M�todos de ListModel
	//
	
	@Override
	public int getSize() {
		return ficherosLista.size();
	}

	@Override
	public String getElementAt(int index) {
		return ficherosLista.get(index).getName();
	}

		// Escuchadores de datos de la lista
		transient ArrayList<ListDataListener> misEscuchadores = initDataListeners();
		public ArrayList<ListDataListener> initDataListeners() {
			misEscuchadores = new ArrayList<>();
			return misEscuchadores;
		}
	@Override
	public void addListDataListener(ListDataListener l) {
		misEscuchadores.add( l );
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		misEscuchadores.remove( l );
	}

	// Llamar a este m�todo cuando se a�ada un elemento a la lista
	// (Utilizado para avisar a los escuchadores de cambio de datos de la lista)
	private void avisarAnyadido( int posi ) {
		for (ListDataListener ldl : misEscuchadores) {
			System.out.println( "A�ADIDO: " + posi );
			ldl.intervalAdded( new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, posi, posi+1 ));
		}
	}
	
	public void anyadeFicheros( String carpetaFicheros, String filtroFicheros ) {
		Pattern pFics = Pattern.compile( filtroFicheros, Pattern.CASE_INSENSITIVE );
		File fInic = new File(carpetaFicheros); 
		if (fInic.isDirectory()) {
			for( File f : fInic.listFiles() ) {
				logger.log( Level.FINE, "Procesando fichero " + f.getName() );
				if (f.isFile() && pFics.matcher(f.getName()).matches() ) { // Si cumple el patr�n, se a�ade
					logger.log( Level.INFO, "A�adido v�deo a lista de reproducci�n: " + f.getName() );
					add( f );
				}
			}
		}
	}
}
