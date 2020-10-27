package Practicas1y2;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import javax.swing.*;
import java.awt.event.*;


/** Socket para versión multijugador
 */
public class SocketMultijugador {

	private static String HOST = "localhost";  // IP de conexión para la comunicación
	private static int PUERTO = 4000;          // Puerto de conexión
	
	private static VentanaServidor vs;
	private static VentanaCliente vc;
	public static void main(String[] args) {
		
		// SERVIDOR
		
		vs = new VentanaServidor();
		vs.setVisible( true );
		
		Thread hilo1 = new Thread() {
			
			@Override
			public void run() {
				vs.lanzaJuegoPrincipal();
			};
		};
		hilo1.start();
		
		// CLIENTE
		
		vc = new VentanaCliente();
		vc.setVisible( true );
		
		Thread hilo2 = new Thread() {
			
			@Override
			public void run() {
				vc.lanzaMultijugador();
			};
		};
		hilo2.start();
		
	}

	@SuppressWarnings("serial")
	public static class VentanaCliente extends JFrame {
		
		private VentanaEdicionSprites vSprites;
		
		boolean finJuego = false;
		public VentanaCliente() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 0, 0 );
			setTitle( "Ventana cliente - 'fin' acaba" );
			
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					finJuego = true;
				}
			});
		}
	    public void lanzaMultijugador() {
	        try (Socket socket = new Socket( HOST, PUERTO )) {
	        	vSprites = new VentanaEdicionSprites();
	            vSprites.setVisible( true );
	        } catch (IOException e) {
	        	e.printStackTrace();
	        }
	    }
	}
	    
	@SuppressWarnings("serial")
	public static class VentanaServidor extends JFrame {
		
		private MainEdicionSprites mainSprites;
		
        boolean finJuego = false;
        Socket socket;
		public VentanaServidor() {
			setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
			setSize( 400, 300 );
			setLocation( 400, 0 );
			setTitle( "Ventana servidor" );
			addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						socket.close();
					} catch (IOException e1) {
			    		e1.printStackTrace();
					}
					finJuego = true;
				}
			});
		}
	    public void lanzaJuegoPrincipal() {
	    	try(ServerSocket serverSocket = new ServerSocket( PUERTO )) {
	    		socket = serverSocket.accept();  // Bloqueante
	    		mainSprites = new MainEdicionSprites();
	    		mainSprites.notify();
	    		socket.close();
	    	} catch(IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	}

}