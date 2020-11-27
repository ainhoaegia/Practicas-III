package Practica4;

import java.util.ArrayList;
import java.util.Collections;

public class Recursividad {

	public static void main(String[] args) {
		
		// ARRAYLIST PARA LOS METODOS 4.4, 4.5, 4.6
		
		ArrayList<String> listaPalabrasM3 = new ArrayList<>();
		listaPalabrasM3.add( "Ainhoa" );
		listaPalabrasM3.add( "Bilbao" );
		listaPalabrasM3.add( "Egia" );
		listaPalabrasM3.add( "Programacion" );
		listaPalabrasM3.add( "Telematica" );
		listaPalabrasM3.add( "Estadistica" );
		listaPalabrasM3.add( "Ecuaciones" );
		listaPalabrasM3.add( "Bases" );
		listaPalabrasM3.add( "Expresion" );
		
		ArrayList<String> listaPalabras = new ArrayList<>();
		listaPalabras.add( "Ainhoa" );
		listaPalabras.add( "Bilbao" );
		listaPalabras.add( "Egia" );
		listaPalabras.add( "Programacion" );
		listaPalabras.add( "Telematica" );
		listaPalabras.add( "Estadistica" );
		listaPalabras.add( "Ecuaciones" );
		listaPalabras.add( "Bases" );
		listaPalabras.add( "Bases" );
		listaPalabras.add( "Bases" );
		listaPalabras.add( "Bases" );
		listaPalabras.add( "Expresion" );
		

		// METODO 4.1

		System.out.println( "INVIERTE FRASE:" );
		System.out.println( "Sin invertir: Primer metodo");
		System.out.println( "Invirtiendo: " + invertirFrase( "Primer metodo" ) );
		System.out.println( " " );
		System.out.println( "----------------------------------" );
		System.out.println( " " );

		// METODO 4.2

		System.out.println( "INVIERTE PALABRAS:" );
		System.out.println( "Sin invertir: Segundo método a ejecutar");
		System.out.println( "Invertido:");
		invertirPalabras( "Segundo método a ejecutar", 0, "" );
		System.out.println( " " );
		System.out.println( "----------------------------------" );
		System.out.println( " " );

		// METODO 4.3

		System.out.println( "LONG A HEXADECIMAL:");
		System.out.println( "Long: 123L");
		System.out.println( "Hexadecimal: " + longAHexa( 123L ) );
		System.out.println( " " );
		System.out.println( "----------------------------------" );
		System.out.println( " " );

		// METODO 4.4

		System.out.println( "SACA PALABRAS:");
		System.out.println( "Al derecho: " + listaPalabrasM3 );
		System.out.println( "Al reves: " + sacaPalabras( listaPalabrasM3 ) );
		System.out.println( " " );
		System.out.println( "----------------------------------" );
		System.out.println( " " );
		

		// METODO 4.5

		System.out.println( "ORDENA QUICK:");
		System.out.println( "Sin ordenar: " + listaPalabras );
		System.out.println( "Ordenada: " + ordenaQuick( listaPalabras ) );
		System.out.println( " " );
		System.out.println( "----------------------------------" );
		System.out.println( " " );

		// METODO 4.6

		System.out.println( "BUSCA PALABRA:");
		Collections.sort( listaPalabras );
		System.out.println( "Lista: " + listaPalabras );
		System.out.println( "Palabra a buscar: Bases" );
		System.out.println( "Posicion: " + buscaPalabra( listaPalabras, "Bases", 0, listaPalabras.size()-1 ) );
		System.out.println( " " );
		System.out.println( "----------------------------------" );
		System.out.println( " " );
	}


	/* Recibe un String y lo devuelve invertido letra a letra, de forma recursiva.
	 */
	public static String invertirFrase( String frase ) {

		if (frase.isEmpty()) { // Caso base

			return "";

		} else { // Caso recursivo

			return invertirFrase(frase.substring(1)) + frase.charAt(0);

		}

	}



	/* Recibe un String y lo devuelve invertido palabra a palabra 
	 * (considerando los separadores habituales espacio, tabulador, 
	 * salto de línea, símbolos de puntuación), de forma recursiva.
	 */
	private static void invertirPalabras( String frase, int numLetra, String palabra) {
		if(numLetra == frase.length() ) {
			System.out.println( palabra );
		} else {
			char car = frase.charAt( numLetra );
			if(car==' ') {
				invertirPalabras( frase, numLetra + 1, "" );
				System.out.println( palabra );
			} else {
				invertirPalabras(frase, numLetra + 1, palabra + car );
			}
		}
	}
	
	/* Recibe un long y devuelve la conversión de ese long a 
	 * hexadecimal, de forma recursiva.
	 */
	public static String longAHexa( long numeroLong ) {

		if(numeroLong == 0 ) { // Caso base

			String numero = Long.toString( numeroLong );
			return numero;

		} else { // Caso recursivo

			String hexadecimal="";
			int num = Integer.parseInt( String.valueOf( numeroLong ) );
			int resto = num % 16;
			char hexa[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			
			hexadecimal = hexa [ resto ] + hexadecimal;
			num = num / 16;
			return longAHexa( Long.valueOf( num ) ) + hexadecimal;
		}

	}



	/* Recibe un ArrayList y devuelve un ArrayList de Strings con
	 * todas las palabras del fichero de texto en orden inverso a como 
	 * aparecen, de forma recursiva.
	 */
	public static ArrayList<String> sacaPalabras( ArrayList<String> lista) {
		
		ArrayList<String> ListaSacaPalabras = new ArrayList<>();
		
		if(lista.size() <= 1) {
			
			return lista;
		
		} else {
			
			String elemento = lista.get( lista.size() - 1 );
			ListaSacaPalabras.add( elemento );
			lista.remove( elemento );
			
			ListaSacaPalabras.addAll( sacaPalabras( lista ) );
			return ListaSacaPalabras;
		}
		
	}



	/* Recibe un arraylist de Strings (por ejemplo las palabras del 4.4.)
	 * y devuelve ese arraylist ordenado alfabéticamente por el método quicksort,
	 * de forma recursiva (observa que puede haber palabras repetidas).
	 */
	public static ArrayList<String> ordenaQuick( ArrayList<String> lista) { 
		
		if (lista.isEmpty()) { // Caso base
			return lista;
		} else {
			ArrayList<String> letraMenor = new ArrayList<String>();
			ArrayList<String> letraMayor = new ArrayList<String>();
			
			String primeraLetra = lista.get(0);
			String letra;
			int i;
			
			for (i=1;i<lista.size();i++) {
				
				letra=lista.get(i);
				if (letra.compareTo(primeraLetra)<0) {
					letraMenor.add(letra);
				} else {
					letraMayor.add(letra);
				}
			}
			
			letraMenor=ordenaQuick(letraMenor);
			letraMayor=ordenaQuick(letraMayor); 
			letraMenor.add(primeraLetra);        
			letraMenor.addAll(letraMayor);    
			lista = letraMenor;            

			return lista;
		}
	}



	/* Recibe un arraylist de Strings ordenado y una palabra, y devuelve la 
	 * posición en la que se encuentra esa palabra (si está repetida, la 
	 * posición de la última ocurrencia). De forma recursiva y utilizando 
	 * un proceso de coste logarítmico.
	 */
	private static int buscaPalabra( ArrayList<String> palabrasOrdenadas, String palabraABuscar, int ini, int fin ) {
//		System.out.println( ini + ", " + fin );
		
		if ( ini > fin) {
			return -1;
		}
		
		if ( ini == fin) {
			if (palabrasOrdenadas.get(ini).equals( palabraABuscar )) {
				return ini;
			} else {
				return -1;
			}
		}
		
		int medio = (ini+fin+1)/2;
		
		if(palabraABuscar.compareTo(palabrasOrdenadas.get( medio )) >= 0) {
			
			return buscaPalabra(palabrasOrdenadas, palabraABuscar, medio, fin);
		
		} else {
			
			return buscaPalabra(palabrasOrdenadas, palabraABuscar, ini, medio-1);
		
		}
	}
}
