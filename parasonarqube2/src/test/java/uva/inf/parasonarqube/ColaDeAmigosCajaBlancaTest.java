package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ColaDeAmigosCajaBlancaTest {

	Persona manolito;
	Persona inesita;
	Persona sandrita;
	
	ColaDeAmigos cola;
	
	@Before
	public void setUp() {
		manolito = new Persona("Manolito");
		inesita = new Persona("Inesita");
		sandrita = new Persona("Sandrita");
		
		Persona.creaConocimiento(inesita, manolito);
		Persona.creaAmistad(inesita, manolito);
		Persona.creaConocimiento(sandrita, manolito);
		Persona.creaAmistad(sandrita, manolito);

		
		cola = new ColaDeAmigos();
		
		cola.pideVez(manolito, 2);
		
	}
	
	@After
	public void tearDown() {
		manolito = null;
		inesita = null;
		sandrita = null;
		cola = null;
	}
	
	@Category({Integration.class})
	@Test
	public void testDameAmigosColadosAvanzandoColaSinHuecos() {
		cola.seCuelaCon(inesita,manolito);
		cola.seCuelaCon(sandrita,manolito);
		cola.avanza();
		Persona[] amigos = cola.dameAmigosColadosDe(manolito);
		Persona[] expect = {sandrita};
		assertArrayEquals(amigos,expect);
	}
	
	@Category({Integration.class})
	@Test
	public void testDameAmigosColadosAvanzandoColaConHuecos() {
		cola.seCuelaCon(inesita,manolito);
		cola.avanza();
		Persona[] amigos = cola.dameAmigosColadosDe(manolito);
		Persona[] expect = {};
		assertArrayEquals(amigos,expect);
	}
}
