package Practica0;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame{
	
	public static void main(String[] args) {
		
		VentanaJuego ventana = new VentanaJuego( "PRÁCTICA 0" );
		ventana.setVisible(true);
		
	}
	
	private JPanel panelCentro;
	private JPanel panelSur;
	private JButton botonAcelera;
	private JButton botonFrena;
	private JButton botonGiraIzq;
	private JButton botonGiraDrc;
	
	public VentanaJuego (String titulo) {
		
		super(titulo);

		// VENTANA

		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		this.setSize( 900, 300 );
		this.setLocation( 260, 120 );
		this.setLocationRelativeTo( null );
		getContentPane().revalidate();

		// CONTENEDORES Y COMPONENTES
		
		panelCentro = new JPanel();
		panelSur = new JPanel();
		
		// PANEL CENTRO
		
		this.setLayout( null ); // LayOut nulo
		this.add( panelCentro, BorderLayout.CENTER );
		
		// PANEL SUR --> BOTONERA
		
		botonAcelera = new JButton( "Acelera" );
		botonFrena = new JButton( "Frena" );
		botonGiraIzq = new JButton( "Gira Izquierda" );
		botonGiraDrc = new JButton( "Gira Derecha" );

		botonAcelera.setFocusable( true );
		botonFrena.setFocusable( true );
		botonGiraIzq.setFocusable( true );
		botonGiraDrc.setFocusable( true );

		panelCentro.add( botonAcelera );
		panelCentro.add( botonFrena );
		panelCentro.add( botonGiraIzq );
		panelCentro.add( botonGiraDrc );
		
		this.setLayout( null );
		this.add( panelSur, BorderLayout.SOUTH );
	}
}
