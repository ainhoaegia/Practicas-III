package Practica0;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame{
	
	public static void main(String[] args) {
		VentanaJuego ventana = new VentanaJuego("PRÁCTICA 0");
		ventana.setVisible( true );
		
		Coche coche = new Coche( "Lewis Hamilton", 150.0, 100.0, 10.0, 0.0 );
		System.out.println( coche.toString() );
	}
	
	private JButton botonAcelera = new JButton("Acelera");
	private JButton botonFrena = new JButton("Frena");
	private JButton botonGiraI = new JButton("Gira Izquierda");
	private JButton botonGiraD = new JButton("Gira Derecha");
	
	private JPanel panelCentral = new JPanel();
	private JPanel panelInferior = new JPanel();
	
	public boolean cierreAceptar;
	
	public VentanaJuego(String titulo) {
		
		super(titulo);
		
		this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );  
		this.setSize( 900, 300 );  
		this.setLocationRelativeTo( null ); // CENTRAR LA VENTANA
		
		
		panelInferior.add(botonAcelera);
		panelInferior.add(botonFrena);
		panelInferior.add(botonGiraI);
		panelInferior.add(botonGiraD);
		
		
		panelInferior.setLayout(new GridLayout(1,4));
		
		this.add( panelCentral, BorderLayout.CENTER );
		this.add( panelInferior, BorderLayout.SOUTH );
		
		
		// PARA QUE SEAN ACCESIBLES CON EL TABULADOR

		botonAcelera.setFocusable( true );
		botonFrena.setFocusable( true );
		botonGiraI.setFocusable( true );
		botonGiraD.setFocusable( true );
		
	}
}
