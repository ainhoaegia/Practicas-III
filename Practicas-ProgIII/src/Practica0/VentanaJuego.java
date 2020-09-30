package Practica0;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VentanaJuego extends JFrame{
	
	public static void main(String[] args) {
		VentanaJuego ventana = new VentanaJuego("PRÁCTICA 0");
		ventana.setVisible( true );
		
		// Datos del Coche
		Coche coche = new Coche( "Lewis Hamilton", 150.0, 100.0, 10.0, 0.0 );
		System.out.println( coche.toString() );
	}
	
	Coche coche = new Coche( "Lewis Hamilton", 150.0, 100.0, 10.0, 0.0 );
	
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
		
		
		// PANEL CENTRAL
		
		JLabelCoche jlabel = new JLabelCoche( "src/Practica0/coche.png", 100, 100 );
		panelCentral.add( jlabel );
		
		this.add( panelCentral, BorderLayout.CENTER );
		
		// PANEL INFERIOR
		
		panelInferior.add(botonAcelera);
		panelInferior.add(botonFrena);
		panelInferior.add(botonGiraI);
		panelInferior.add(botonGiraD);
		
		panelInferior.setLayout(new GridLayout(1,4));
		
		this.add( panelInferior, BorderLayout.SOUTH );
		
		
		// PARA QUE SEAN ACCESIBLES CON EL TABULADOR

		botonAcelera.setFocusable( true );
		botonFrena.setFocusable( true );
		botonGiraI.setFocusable( true );
		botonGiraD.setFocusable( true );
		
		botonAcelera.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double vel = coche.getMiVelocidad();
				vel = vel + 5;
				coche.setMiVelocidad( vel );
				System.out.println( "Velocidad (acelerando): " + vel );
			}
				
		});
		
		botonFrena.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				double vel = coche.getMiVelocidad();
				
				if( vel>5 ) { // si la velocidad es mayor que 5, se puede frenar de 5 en 5 pixel/seg
					vel = vel-5;
				} else { // si la velocidad es menor que 5, está quieto o le queda poco para estarlo, por lo que se frena
					vel = 0;
				}
				coche.setMiVelocidad( vel );
				System.out.println( "Velocidad (frenando): " + vel );
			}
				
		});
	}
}
