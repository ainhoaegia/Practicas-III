package Practica4;

public class Pruebas {

	public static void main(String[] args) {
		System.out.println( longAHexa( 123L ) );
	}
	
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
}
