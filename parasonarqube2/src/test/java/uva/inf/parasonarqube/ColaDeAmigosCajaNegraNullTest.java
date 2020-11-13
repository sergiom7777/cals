package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Test donde pongo nulls.
 */
@Category({Unit.class})
public class ColaDeAmigosCajaNegraNullTest {

	Persona manolito;
	Persona joselito;
	ColaDeAmigos cola;
	
	@Before
	public void setUp() {
		manolito = new Persona("Manolito");
		joselito = new Persona("Joselito");
		Persona.creaConocimiento(manolito, joselito);
		Persona.creaAmistad(manolito, joselito);
		
		cola = new ColaDeAmigos();
		cola.pideVez(manolito, 1);
	}
	
	@After
	public void tearDown() {
		manolito = null;
		joselito = null;
		cola = null;
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testPideVez() {
		cola.pideVez(null, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBuscaAmigosConHueco() {
		cola.buscaAmigosConHueco(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConColadoNull() {
		cola.seCuelaCon(null, manolito);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConColadorNull() {
		cola.seCuelaCon(joselito,null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroReservasInicial() {
		cola.dameNumeroReservasInicial(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroReservasActual() {
		cola.dameNumeroReservasActual(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDameAmigosColadosDe() {
		cola.dameAmigosColadosDe(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroAmigosColados() {
		cola.dameNumeroAmigosColados(null);
	}

}
