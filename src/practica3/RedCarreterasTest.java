package practica3;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RedCarreterasTest {

	@Test
	public void testConstructor() {
		System.out.println("\nValidando constructor...");
		System.out.println("NUMERO DE CIUDADES ESPERADAS: 0");
		RedCarreteras red = new RedCarreteras();
		System.out.println("NUMERO DE CIUDADES CREADAS  : " + red.ciudades().size());
		assertEquals(0, red.ciudades().size());
		
		System.out.println(" ...correcto");
	}
	
	static String[][] testValidaTramoData = {
			{null, null, "1"},
			{null, "Castellon", "1"},
			{"Castellon", null, "1"},
			{"Castellon", "Castellon", "1"},
			{"Castellon", "Valencia", "0"}
	};

	private String tramoToString(String[] s, boolean all)
	{
		return "( " + s[0] +", " + s[1] + (all ? (", " + s[2]): "") +" )";
	}
	private String tramoToString(String[] s) {
		return tramoToString(s, true);
	}
	
	@Test
	public void testValidaTramo() {
		System.out.println("\nValidando verificacion de tramos nuevos...");
		RedCarreteras red = new RedCarreteras();
		
		int cont = 0;
		for (String[] caso: testValidaTramoData )
			try {
				System.out.println("Añadiendo  - nuevoTramo" + tramoToString(caso));
				System.out.println("  RESULTADO ESPERADO: Deberia lanzar la excepcion InvalidParameteException");
				red.nuevoTramo(caso[0], caso[1], Integer.parseInt(caso[2]));
				fail("No lanza la excepación");
			} catch (InvalidParameterException e) {
				System.out.println("  RESULTADO: Lanza la excepción");
			}			
	}
	
	private String[][] testAnadeTramoData = {
		{"Bilbao", "Zaragoza", "190"},
		{"Barcelona", "Zaragoza", "260"},
		{"Barcelona", "Castellon", "300"},
		{"Zaragoza", "Castellon", "250"},
		{"Castellon", "Valencia", "60"},
		{"Zaragoza", "Madrid", "280"},
		{"Valencia", "Madrid", "350"},
		{"Valencia","Alicante", "200"},
		{"Madrid", "Sevilla", "410"},
		{"Sevilla", "Malaga", "220"},
		{"Malaga", "Alicante", "350"}
	};
	
	@Test
	public void testAñadeTramo() {
		System.out.println("\nValidando añadir tramos nuevos...");
		
		RedCarreteras red = new RedCarreteras();

		for (String[] tramo: testAnadeTramoData) {
			System.out.println("Añadiendo  - nuevoTramo" + tramoToString(tramo));
			System.out.println("  ESPERADO:  -1");
			int r = red.nuevoTramo(tramo[0], tramo[1], Integer.parseInt(tramo[2]));
			System.out.println("  RESULTADO: " + r);
			assertEquals(-1, r);
		}
		
		System.out.println("\n ...tramos repetidos");
		
		for (String[] tramo: testAnadeTramoData) {
			System.out.println("Añadiendo repetido - nuevoTramo" + tramoToString(tramo) );
			System.out.println("  ESPERADO : " + Integer.parseInt(tramo[2]));
			int r = red.nuevoTramo(tramo[0], tramo[1], Integer.parseInt(tramo[2]));
			System.out.println("  RESULTADO: " + r);
			assertEquals(Integer.parseInt(tramo[2]), r);
		}
		
		System.out.println("\n ... tramos repetidos e inversos");
		
		String[] aux = new String[3];
		for (String[] tramo: testAnadeTramoData) {
			aux[0] = tramo[1];
			aux[1] = tramo[0];
			aux[2] = tramo[2];
			System.out.println("Añadiendo inverso repetido - nuevoTramo" + tramoToString(aux));
			System.out.println("  ESPERADO : " + Integer.parseInt(tramo[2]));
			int r = red.nuevoTramo(aux[0], aux[1], Integer.parseInt(aux[2]));
			System.out.println("  RESULTADO: " + r);
			assertEquals(Integer.parseInt(aux[2]), r);
		}

	}
	
	@Test
	public void testExisteTramo() {
		System.out.println("\nValidando exsitencia de tramos ...");
		RedCarreteras red = new RedCarreteras();
		String tramo[] = null;

		
		System.out.println(" ...añadiendo tramos");
		for (int i =0; i < testAnadeTramoData.length; i += 2) {
			tramo = testAnadeTramoData[i];
			System.out.println("Añadiendo - nuevoTramo" + tramoToString(tramo));
			System.out.println("  ESPERADO : -1");
			int r = red.nuevoTramo(tramo[0], tramo[1], Integer.parseInt(tramo[2]));
			System.out.println("  RESULTADO: " + r);
			assertEquals(-1, r);
		}
		
		System.out.println(" ...comprobando que existen ");
		for (int i =0; i < testAnadeTramoData.length; i += 2) {
			tramo = testAnadeTramoData[i]; 
			System.out.println("Existe? - existeTramo" + tramoToString(tramo, false));
			System.out.println("  ESPERADO : " + Integer.parseInt(tramo[2]));
			int r = red.existeTramo(tramo[0], tramo[1]);
			System.out.println("  RESULTADO: " + r);
			assertEquals(Integer.parseInt(tramo[2]), r);
		}
		
		System.out.println(" ...comprobando que existen invertidos");
		tramo = new String[3];
		for (int i =0; i < testAnadeTramoData.length; i += 2) {
			tramo[0] = testAnadeTramoData[i][1];
			tramo[1] = testAnadeTramoData[i][0];
			tramo[2] = testAnadeTramoData[i][2];

			System.out.println("Existe Invertido? - existeTramo" + tramoToString(tramo, false));
			System.out.println("  ESPERADO : " + Integer.parseInt(tramo[2]));
			int r = red.existeTramo(tramo[0], tramo[1]);
			System.out.println("  RESULTADO: " + r);
			assertEquals(Integer.parseInt(tramo[2]), r);
		}

		System.out.println(" ...comprobando que no existen ");
		for (int i=1; i < testAnadeTramoData.length; i += 2) {
			tramo = testAnadeTramoData[i];
			System.out.println("Existe tramos ? existeTRamo( " + tramoToString(tramo, false));
			System.out.println("  ESPERADO : -1" );
			int r = red.existeTramo(tramo[0], tramo[1]);
			System.out.println("  RESULTADO: " + r);
			assertEquals(-1, r);
		}
	}
	
	@Test
	public void testBorraTramo() {
		System.out.println("\nValidando borrado de tramos ...");
		RedCarreteras red = new RedCarreteras();
		String tramo[] = null;
		
		for (int i =0; i < testAnadeTramoData.length; i++) {
			tramo = testAnadeTramoData[i];
			System.out.println("Añadiendo - nuevoTramo" + tramoToString(tramo));
			System.out.println("  ESPERADO:  " + -1);
			int r = red.nuevoTramo(tramo[0], tramo[1], Integer.parseInt(tramo[2]));
			System.out.println("  RESULTADO: " + r);
			assertEquals(-1, r);
		}
		
		tramo = new String[3];
		for (int i =0; i < testAnadeTramoData.length; i++) {
			tramo[0] = testAnadeTramoData[i][1];
			tramo[1] = testAnadeTramoData[i][0];
			tramo[2] = testAnadeTramoData[i][2];
			System.out.println("Borrando - borraTramo" + tramoToString(tramo, false));
			System.out.println("  ESPERADO:  " + Integer.parseInt(tramo[2]));
			int r = red.borraTramo(tramo[0], tramo[1]);
			System.out.println("  RESULTADO: " + r);
			assertEquals(Integer.parseInt(tramo[2]), r);
		}
		
		for (int i =0; i < testAnadeTramoData.length; i++) {
			tramo = testAnadeTramoData[i];
			System.out.println("Verificando el borrado - borraTramo"+ tramoToString(tramo, false));
			System.out.println("  ESPERADO:  -1");
			int r = red.borraTramo(tramo[0], tramo[1]);
			System.out.println("  RESULTADO: " + r);
			assertEquals(-1, r);
		}		
	}
	
	private static String[][] testCaminoData = {
			{},
			{"Castellon"},
			{"Castellon", "Valencia", "Castellon"},
			{"Valencia", "Valencia", "Alicante"},
			{"Malaga", "Sevilla", "Zaragoza"},
			
			{"Bilbao", null, "Zaragoza", "Madrid"},
			{"Barcelona", "Castellon", "Zaragoza", "Bilbao", "Bilbao", "Zaragoza", "Madrid", "Valencia", "Castellon"},
			{"Castellon", "Zaragoza", "Pamplona", "Bilbao"},
			{"Sevilla", "Sevilla"},
			{null, "Alicante", "Alicante"},
			
			{"Castellon", "Castellon", "Segorbe"}
	};
	
	private static int testCaminoResultsData[] = {
			-1, 0, 120, 200, -1,
			-1, 1620, -1, 0, -1, 
			-1
	};
	
	@Test
	public void testCompruebaCamino() {
		System.out.println("\nValidando comprobación de caminos ...");
		RedCarreteras red = new RedCarreteras();
		
		System.out.println("\n ... añadiendo tramos a la red");
		for (String[] tramo: testAnadeTramoData) {
			System.out.println("Añadiendo - nuevoTramo" + tramoToString(tramo));
			System.out.println("  ESPERADO:  -1");
			int r = red.nuevoTramo(tramo[0], tramo[1], Integer.parseInt(tramo[2]));
			System.out.println("  RESULTADO: " + r);
			assertEquals(-1, r);
		}
		System.out.println("\n ... probando caminos");
		for(int i=0; i < testCaminoData.length; i++) {
			List<String> camino= Arrays.asList(testCaminoData[i]);
			System.out.println("compruebaCamino(" + camino + ")");
			System.out.println("  ESPERADO:  " + testCaminoResultsData[i]);
			int r = red.compruebaCamino(camino);
			System.out.println("  RESULTADO: " + r);
			assertEquals(testCaminoResultsData[i], r);
		}
	}
			
}
