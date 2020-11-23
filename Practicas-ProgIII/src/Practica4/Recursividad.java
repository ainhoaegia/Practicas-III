package Practica4;

import java.util.ArrayList;
import java.util.Collections;

import uk.co.caprica.vlcj.binding.support.size_t;

public class Recursividad {

	public static void main(String[] args) {
		
		// ARRAYLIST PARA LOS METODOS 4.4, 4.5, 4.6
		
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
		System.out.println( "Invirtiendo: " + String.valueOf( invertirPalabras( "Segundo método a  ejecutar".toCharArray() ) ) );
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
		System.out.println( "Al derecho: " + listaPalabras);
		System.out.println( "Al reves: " + sacaPalabras( listaPalabras ) );
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
		System.out.println( listaPalabras );
		System.out.println( "Palabra a buscar: Egia" );
		System.out.println( buscaPalabra( listaPalabras, "Egia" ) );
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
	public static char[] invertirPalabras( char[]  palabras ) { 
		
		if (palabras == "".toCharArray()) { // Caso base
			
			return "".toCharArray();
		
		} else { // Caso recursivo

			int inicio = 0;
			for (int fin = 0; fin < palabras.length; fin++) {
				if (palabras[fin] == ' ') 
				{
					invertir(palabras, inicio, fin);
					inicio = fin + 1;
				}
			}

			invertir(palabras, inicio, palabras.length - 1);
			invertir(palabras, 0, palabras.length - 1);
			char[] alReves = palabras;
			return alReves;
		}

	}

	static void invertir(char str[], int inicio, int fin) {

		char temp;

		while (inicio <= fin) {

			temp = str[inicio];
			str[inicio] = str[fin];
			str[fin] = temp;
			inicio++;
			fin--;
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
	public static ArrayList<String> ordenaQuick( ArrayList<String> lista) { // TODO recursividad
		
		if(lista.size() <= 1) {
		
			return lista;
		
		} else {
		
			Collections.sort(lista);
			return lista;
		
		}
	}



	/* Recibe un arraylist de Strings ordenado y una palabra, y devuelve la 
	 * posición en la que se encuentra esa palabra (si está repetida, la 
	 * posición de la última ocurrencia). De forma recursiva y utilizando 
	 * un proceso de coste logarítmico.
	 */
	private static int buscaPalabra( ArrayList<String> palabrasOrdenadas, String palabraABuscar ) { // TODO recursividad
		
		if(palabrasOrdenadas.size() < 1) {
		
			return 0;
		
		} else {
		
			int n = 0;
			n = palabrasOrdenadas.indexOf(palabraABuscar) + 1;
			return n;
		}
	}
}
