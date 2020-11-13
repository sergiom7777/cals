package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Test con una fixture de cola.
 */
public class ColaDeAmigosCajaNegraOtrosTest {

	Persona manolito;
	Persona joselito;
	Persona jorgito;
	Persona luisito;
	Persona fernandito;
	Persona robertito;
	Persona paulita;
	Persona martita;
	Persona inesita;
	Persona sandrita;
	Persona juanito;
	
	ColaDeAmigos cola;
	
	@Before
	public void setUp() {
		manolito = new Persona("Manolito");
		joselito = new Persona("Joselito");
		jorgito = new Persona("Jorgito");
		luisito = new Persona("Luisito");
		fernandito = new Persona("Fernandito");
		robertito = new Persona("Robertito");
		paulita = new Persona("Paulita");
		martita = new Persona("Martita");
		inesita = new Persona("Inesita");
		sandrita = new Persona("Sandrita");
		juanito = new Persona("Juanito");
		
		Persona.creaConocimiento(luisito, manolito);
		Persona.creaAmistad(luisito, manolito);
		Persona.creaConocimiento(fernandito, jorgito);
		Persona.creaAmistad(fernandito, jorgito);
		Persona.creaConocimiento(inesita, manolito);
		Persona.creaAmistad(inesita, manolito);
		Persona.creaConocimiento(sandrita, paulita);
		Persona.creaAmistad(sandrita, paulita);
		Persona.creaConocimiento(sandrita, manolito);
		Persona.creaAmistad(sandrita, manolito);
		Persona.creaConocimiento(sandrita, juanito);
		Persona.creaAmistad(sandrita, juanito);
		Persona.creaConocimiento(juanito, martita);
		Persona.creaAmistad(juanito, martita);
		
		cola = new ColaDeAmigos();
		
		cola.pideVez(manolito, 2);
		cola.pideVez(joselito, 0);
		cola.pideVez(jorgito, 1);
		cola.pideVez(paulita, 1);
		cola.pideVez(martita, 0);
		
	}
	
	@After
	public void tearDown() {
		manolito = null;
		joselito = null;
		jorgito = null;
		luisito = null;
		fernandito = null;
		robertito = null;
		paulita = null;
		martita = null;
		inesita = null;
		sandrita = null;
		juanito = null;
		cola = null;
	}
	
	//PideVez*************************************************************************
	@Category({Unit.class})
	@Test
	public void testPideVezCeroAmigos() {
		cola.pideVez(juanito, 0);
		assertEquals(juanito,cola.ultimo());
		assertEquals(cola.longitud(),6);
	}
	@Category({Unit.class})
	@Test
	public void testPideVezDiezAmigos() {
		cola.pideVez(juanito, 10);
		assertEquals(juanito,cola.ultimo());
		assertEquals(cola.longitud(),6);	
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testPideVezMenosUnAmigos() {
		cola.pideVez(juanito, -1);
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testPideVezOnceAmigos() {
		cola.pideVez(juanito, 11);
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testPideVezAlguienYaEnCola() {
		cola.pideVez(manolito, 0);
	}
	
	//BuscaAmigos*************************************************************************
	@Category({Integration.class})
	@Test
	public void testBuscaAmigosConHuecoSiTieneHueco() {
		Persona[] amigos = cola.buscaAmigosConHueco(inesita);
		Persona[] expect = {manolito};
		assertArrayEquals(amigos, expect);
	}
	@Category({Integration.class})
	@Test
	public void testBuscaAmigosConHuecoNoTieneHueco() {
		Persona[] amigos = cola.buscaAmigosConHueco(juanito);
		Persona[] expect = {};
		assertArrayEquals(amigos, expect);
	}
	@Category({Integration.class})
	@Test(expected=IllegalArgumentException.class)
	public void testBuscaAmigosConHuecoYaEnLaCola() {
		@SuppressWarnings("unused")
		Persona[] amigos = cola.buscaAmigosConHueco(manolito);
	}
	
	//SeCuelaCon*************************************************************************
	@Category({Integration.class})
	@Test
	public void testSeCuelaConElPrimeroEnLaCola() {
		cola.seCuelaCon(luisito, manolito);
		Persona[] amigos = {luisito};
		assertArrayEquals(cola.dameAmigosColadosDe(manolito),amigos);
		assertEquals(cola.dameNumeroReservasActual(manolito),1);
		assertEquals(cola.ultimo(),martita);
		assertEquals(cola.primero(),luisito);
		assertEquals(cola.longitud(),6);
	}
	@Category({Integration.class})
	@Test
	public void testSeCuelaConPorElMedio() {
		cola.seCuelaCon(fernandito, jorgito);
		Persona[] amigos = {fernandito};
		assertArrayEquals(cola.dameAmigosColadosDe(jorgito),amigos);
		assertEquals(cola.dameNumeroReservasActual(jorgito),0);
		assertEquals(cola.ultimo(),martita);
		assertEquals(cola.primero(),manolito);
		assertEquals(cola.longitud(),6);
	}
	@Category({Integration.class})
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConColadoYaEnCola() {
		cola.seCuelaCon(joselito, manolito);
	}
	@Category({Integration.class})
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConColadorNoEnCola() {
		cola.seCuelaCon(sandrita,juanito);
	}
	@Category({Integration.class})
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConNoSonAmigos() {
		cola.seCuelaCon(fernandito,manolito);
	}
	@Category({Integration.class})
	@Test(expected=IllegalStateException.class)
	public void testSeCuelaConColadorNoTieneHueco() {
		cola.seCuelaCon(juanito,martita);
	}
	@Category({Integration.class})
	@Test(expected=IllegalStateException.class)
	public void testSeCuelaConColado() {
		cola.seCuelaCon(sandrita, paulita);
		cola.seCuelaCon(juanito, sandrita);
	}
	
	//DameNumeroReservasInicial*************************************************************************
	@Category({Unit.class})
	@Test
	public void testDameNumeroReservasInicial() {
		assertEquals(cola.dameNumeroReservasInicial(manolito),2);
	}
	@Category({Unit.class})
	@Test
	public void testDameNumeroReservasInicialDespuesDeColar() {
		cola.seCuelaCon(luisito, manolito);
		assertEquals(cola.dameNumeroReservasInicial(manolito),2);	
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroReservasInicialNoEnCola() {
		cola.dameNumeroReservasInicial(juanito);
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroReservasInicialColado() {
		cola.seCuelaCon(luisito, manolito);
		cola.dameNumeroReservasInicial(luisito);
	}
	
	//DameNumeroReservasActual*************************************************************************
	
	@Category({Integration.class})
	@Test
	public void testDameNumeroReservasActual() {
		assertEquals(2,cola.dameNumeroReservasActual(manolito));
		cola.seCuelaCon(luisito, manolito);
		assertEquals(1,cola.dameNumeroReservasActual(manolito));
	}
	@Category({Unit.class})
	@Test
	public void testDameNumeroReservasActualSinColar() {
		assertEquals(cola.dameNumeroReservasInicial(manolito),cola.dameNumeroReservasActual(manolito));
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroReservasActualNoEnCola() {
		cola.dameNumeroReservasActual(juanito);
	}
	@Category({Integration.class})
	@Test(expected=IllegalArgumentException.class)
	public void testDameNumeroReservasActualColado() {
		cola.seCuelaCon(luisito, manolito);
		cola.dameNumeroReservasActual(luisito);
	}
	
	//DameAmigosColadosDe*************************************************************************
	@Category({Unit.class})
	@Test
	public void testDameAmigosColadosDeSinHaberColado() {
		Persona[] amigos = cola.dameAmigosColadosDe(paulita);
		Persona[] expect = {};
		assertArrayEquals(amigos,expect);
	}
	@Category({Unit.class})
	@Test
	public void testDameAmigosColadosDeHabiendoColado() {
		cola.seCuelaCon(sandrita, paulita);
		Persona[] amigos = cola.dameAmigosColadosDe(paulita);
		Persona[] expect = {sandrita};
		assertArrayEquals(amigos,expect);
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testDameAmigosColadosDeNoEnCola() {
		cola.dameAmigosColadosDe(juanito);
	}
	@Category({Integration.class})
	@Test(expected=IllegalArgumentException.class)
	public void testDameAmigosColadosDeColado() {
		cola.seCuelaCon(luisito, manolito);
		cola.dameAmigosColadosDe(luisito);
	}
	
	//DameAmigosColadosDe*************************************************************************
	@Category({Unit.class})
	@Test
	public void testNumeroDameAmigosColadosDeSinHaberColado() {
		int amigos = cola.dameNumeroAmigosColados(paulita);
		assertEquals(amigos,0);
	}
	@Category({Unit.class})
	@Test
	public void testNumeroDameAmigosColadosDeHabiendoColado() {
		cola.seCuelaCon(sandrita, paulita);
		int amigos = cola.dameNumeroAmigosColados(paulita);
		assertEquals(amigos,1);
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testNumeroDameAmigosColadosDeNoEnCola() {
		cola.dameNumeroAmigosColados(juanito);
	}
	@Category({Unit.class})
	@Test(expected=IllegalArgumentException.class)
	public void testNumeroDameAmigosColadosDeColado() {
		cola.seCuelaCon(luisito, manolito);
		cola.dameNumeroAmigosColados(luisito);
	}
	
	//Avanza*************************************************************************
	@Category({Unit.class})
	@Test
	public void atestAvanza() {
		assertEquals(cola.primero(),manolito);
		assertEquals(cola.longitud(),5);
		cola.avanza();
		assertEquals(cola.primero(),joselito);
		assertEquals(cola.longitud(),4);
	}
	
}
