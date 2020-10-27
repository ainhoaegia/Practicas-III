package PruebasPracticas1y2.conPng;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//import Practicas1y2.MainEdicionSprites;
//import Practicas1y2.ControladorVentanaSprites;
//import Practicas1y2.JLabelGraficoAjustado;
import Practicas1y2.VentanaEdicionSprites;


public class cargaFicherosGraficosOrdenadosConPng {
	
	private VentanaEdicionSprites ventana;
	private int lanza = 100; // 1 decima de segundo
	
	@Before
    public void setUp() throws Exception{
		ventana = new VentanaEdicionSprites();
	}
    
    @After
    public void tearDown() throws Exception{
    	ventana.dispose();
    }
    
    @Test // Crea y abre una ventana
    public void lanzaVentana() {
    	ventana.setVisible( true );
    	try {
			Thread.sleep( lanza ); // Lanzar la ventana en 1 decima de segundo
		} catch (Exception e) {  }
    }

}
