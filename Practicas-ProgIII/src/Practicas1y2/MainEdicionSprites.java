package Practicas1y2;

import java.io.*;

import java.util.*;
import java.util.logging.*;

import javax.swing.JOptionPane;
import javax.swing.UIManager;  // Para usar look and feels distintos al estándar

/** Clase principal de edición de sprites<br/>
 * Enlace a un zip con gráficos para sprites de ejemplo:
 * <a href="https://drive.google.com/file/d/1UhqJT1zh_aYzcCgKa_6eRUdQvnqP8k0v/view?usp=sharing">link a fichero comprimido</a>
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class MainEdicionSprites {
	
	static Logger log; // Sin private, se puede acceder desde cualquier clase del mismo paquete
	static Logger logNomCarpeta;
	static Logger logVelocidad;
	
	/** Método principal, crea una ventana de edición y la lanza 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// log
		try {
			log = Logger.getLogger( "prueba_logger" );
			Handler h = new FileHandler( "EdicionSprites.xml" );
			log.addHandler( h );
			log.setLevel( Level.FINEST );
			h.setLevel( Level.FINEST );
			
		} catch (Exception e) {}
		
		log.log( Level.INFO, "Inicio Edicion Sprites " + (new Date() ));
		
		// logNomCarpeta
		try {
			logNomCarpeta = Logger.getLogger( "logger_Carpeta" );
			Handler hnc = new FileHandler( "nombrecarpeta.xml" );
			logNomCarpeta.addHandler( hnc );
			logNomCarpeta.setLevel( Level.FINEST );
			hnc.setLevel( Level.FINEST );

		} catch (Exception e) {}

		logNomCarpeta.log( Level.INFO, "Nombres de carpetas buscadas: " + (new Date() ));
		
		// logVelocidad
		try {
			logVelocidad = Logger.getLogger( "logger_Velocidad" );
			Handler hv = new FileHandler( "velocidad.xml" );
			logVelocidad.addHandler( hv );
			logVelocidad.setLevel( Level.FINEST );
			hv.setLevel( Level.FINEST );

		} catch (Exception e) {}

		logVelocidad.log( Level.INFO, "Velocidades: " + (new Date() ));
		
		try {
			try { // Cambiamos el look and feel (se tiene que hacer antes de crear la GUI
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (Exception e) { } // Si Nimbus no está disponible, se usa el l&f por defecto
			VentanaEdicionSprites v = new VentanaEdicionSprites();

			// TODO Sentencias de prueba
			// Estas tres líneas inicializan la secuencia con tres gráficos de ejemplos (sustituir los paths por los gráficos que se deseen)
			// (Para hacer pruebas en cualquier ventana a veces es conveniente inicializar componentes a mano
			// y así se pueden probar cosas sin tener que hacer todos los pasos. Luego se quitan cuando las 
			// pruebas se han acabado)
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__000.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__001.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__002.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__003.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__004.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__005.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__006.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__007.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__008.png" ) );
			v.getController().anyadirSpriteASecuencia( new java.io.File( "src/Practicas1y2/img/ninja/png/Attack__009.png" ) );


			v.setVisible( true );

		} catch (Exception e) {
			log.log( Level.SEVERE, "Error en main", e ); // Registrar el error
			logNomCarpeta.log( Level.SEVERE, "Error en main", e ); // Registrar el error
			logVelocidad.log( Level.SEVERE, "Error en main", e ); // Registrar el error
			e.printStackTrace(); // Sacar el error a consola
			JOptionPane.showMessageDialog( null, "Error grave: Contacta con Informatica", "ERROR", JOptionPane.ERROR_MESSAGE );
		}
	}

}