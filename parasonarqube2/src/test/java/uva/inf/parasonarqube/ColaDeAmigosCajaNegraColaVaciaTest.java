package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Test con una fixture de cola vacia.
 *
 */
@Category({Unit.class})
public class ColaDeAmigosCajaNegraColaVaciaTest {
	ColaDeAmigos cola;
	
	@Before
	public void setUp() {
		cola = new ColaDeAmigos();	
	}
	
	@After
	public void tearDown() {
		cola = null;
	}
	@Test
	public void testPrimeroColaVacia() {
		assertEquals(cola.primero(),null);
	}

	@Test
	public void testUltimoColaVacia() {
		assertEquals(cola.ultimo(),null);
	}
	@Test(expected=IllegalStateException.class)
	public void testAvanzaColaVacia() {
		cola.avanza();
	}
}
