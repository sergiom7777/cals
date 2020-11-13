package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Mas test de caja negra para Persona.
 */
@Category({Unit.class})
public class PersonaCajaNegraTest {
	
	Persona manolito;
	Persona joselito;
	
	@Before
	public void setUp() {
		manolito = new Persona("Manolito");
		joselito = new Persona("Joselito");
	}
	
	@After
	public void tearDown() {
		manolito = null;
		joselito = null;
	}

	@Test(expected=IllegalArgumentException.class)
	public void testPersonaNombreNull() {
		@SuppressWarnings("unused")
		Persona p = new Persona(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testEsConocidoNull() {
		manolito.esConocido(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreaConocimientoNullUno() {
		Persona.creaConocimiento(null, joselito);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreaConocimientoNullOtro() {
		Persona.creaConocimiento(manolito, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testEsAmigoNull() {
		manolito.esAmigo(null);
	}
	@Test(expected=IllegalStateException.class)
	public void testCreaAmistadSinConocerse() {
		Persona.creaAmistad(manolito, joselito);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCreaAmistadNullUno() {
		Persona.creaConocimiento(manolito, joselito);
		Persona.creaAmistad(null, joselito);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCreaAmistadNullOtro() {
		Persona.creaConocimiento(manolito, joselito);
		Persona.creaAmistad(manolito, null);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testRompeAmistadSinSerAmigos() {
		Persona.rompeAmistad(manolito, joselito);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testRompeAmistadNullUno() {
		Persona.creaConocimiento(manolito, joselito);
		Persona.creaAmistad(manolito, joselito);
		Persona.rompeAmistad(null, joselito);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testRompeAmistadNullOtro() {
		Persona.creaConocimiento(manolito, joselito);
//		System.out.println(manolito.esAmigo(joselito));
//		System.out.println(joselito.esAmigo(manolito));
		Persona.creaAmistad(manolito, joselito);
		Persona.rompeAmistad(manolito, null);
	}
	
	
	
}
