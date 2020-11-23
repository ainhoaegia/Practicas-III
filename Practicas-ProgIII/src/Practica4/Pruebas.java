package Practica4;

import java.util.ArrayList;
import java.util.Collections;

public class Pruebas {

	public static void main(String[] args) {
		ArrayList<String> listaPalabras = new ArrayList<>();
		listaPalabras.add( "Ainhoa" );
		listaPalabras.add( "Bilbao" );
		listaPalabras.add( "Egia" );
		listaPalabras.add( "Programacion" );
		listaPalabras.add( "Telematica" );
		listaPalabras.add( "Estadistica" );
		listaPalabras.add( "Ecuaciones" );
		listaPalabras.add( "Bases" );
		listaPalabras.add( "Expresion" );
		
		System.out.println( "Al derecho: " + listaPalabras);
		System.out.println( "Al reves: " + sacaPalabras( listaPalabras ) );
	}
	
	public static ArrayList<String> sacaPalabras( ArrayList<String> lista) { // TODO recursividad y repasar
		
		Collections.reverse(lista);
		return lista;
		
	}
}
