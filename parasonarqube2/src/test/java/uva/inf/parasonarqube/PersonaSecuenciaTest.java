package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Test de secuencia.
 *
 */
@Category({Sequence.class,Unit.class})
public class PersonaSecuenciaTest {

	Persona manolito;
	Persona luisito;
	Persona sandrita;
	
	@Before
	public void setUp() {
		manolito = new Persona("Manolito");
		luisito = new Persona("Luisito");
		sandrita = new Persona("Sandrita");
	}
	
	@After
	public void tearDown() {
		manolito = null;
		luisito = null;
		sandrita = null;
	}
	
	@Test
	public void testSecuenciaValida() {
		//Creo conocimiento entre Manolito y Luisito
		Persona.creaConocimiento(manolito, luisito);
		assertTrue(manolito.esConocido(luisito));
		assertTrue(luisito.esConocido(manolito));
		
		//Creo amistad entre manolito y luisito
		
		Persona.creaAmistad(manolito, luisito);
		assertTrue(manolito.esAmigo(luisito));
		assertTrue(luisito.esAmigo(manolito));
		
		//Creo conocimiento entre Sandrita y Manolito
		Persona.creaConocimiento(manolito, sandrita);
		assertTrue(manolito.esConocido(sandrita));
		assertTrue(sandrita.esConocido(manolito));
		
		//Conocer los conocidos de manolito
		Persona[] conocidosManolito = manolito.dameConocidos();
		Persona[] comprobarConocidos = {luisito,sandrita};
		assertArrayEquals(conocidosManolito,comprobarConocidos);
		
		//Conocer los amigos de manolito
		Persona[] amigosManolito = manolito.dameAmigos();
		Persona[] comprobarAmigos= {luisito};
		assertArrayEquals(amigosManolito,comprobarAmigos);
		
		//Saber el nombre de los amigos
		String nombre = comprobarAmigos[0].dameNombre();
		assertEquals(nombre,"Luisito");
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSecuenciaNoValida() {
		//Creo conocimiento entre Manolito y Luisito
		Persona.creaConocimiento(manolito, luisito);
		assertTrue(manolito.esConocido(luisito));
		assertTrue(luisito.esConocido(manolito));
		
		//Creo amistad entre manolito y luisito
		
		Persona.creaAmistad(manolito, luisito);
		assertTrue(manolito.esAmigo(luisito));
		assertTrue(luisito.esAmigo(manolito));
		
		//Creo conocimiento entre Sandrita y Manolito
		Persona.creaConocimiento(manolito, sandrita);
		assertTrue(manolito.esConocido(sandrita));
		assertTrue(sandrita.esConocido(manolito));
		
		//Creo amistad entre Sandrita y luisito
		Persona.creaAmistad(sandrita, luisito);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSecuenciaNoValida2() {
		//Creo conocimiento entre Manolito y Luisito
		Persona.creaConocimiento(manolito, luisito);
		assertTrue(manolito.esConocido(luisito));
		assertTrue(luisito.esConocido(manolito));
		
		//Creo amistad entre manolito y luisito
		
		Persona.creaAmistad(manolito, luisito);
		assertTrue(manolito.esAmigo(luisito));
		assertTrue(luisito.esAmigo(manolito));
		
		//Creo conocimiento entre Sandrita y Manolito
		Persona.creaConocimiento(manolito, sandrita);
		assertTrue(manolito.esConocido(sandrita));
		assertTrue(sandrita.esConocido(manolito));
		
		//Rompo amistad entre Sandrita y Manolito
		Persona.rompeAmistad(manolito, sandrita);
	}

}
