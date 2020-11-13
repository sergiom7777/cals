package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Tests para crear el stub de persona.
 */
@Category({TDD.class})
public class ColaDeAmigosInicialTest {

	Persona manolito;
	Persona joselito;
	
	@Before
	public void setUp() {
		manolito = new Persona("Manolito");
		joselito = new Persona("Joselito");
		Persona.creaConocimiento(manolito, joselito);
		Persona.creaAmistad(manolito, joselito);
	}
	
	@After
	public void tearDown() {
		manolito = null;
		joselito = null;
	}
	
	@Category({Unit.class})
	@Test
	public void testColaDeAmigos() {
		ColaDeAmigos cola = new ColaDeAmigos();
		assertNotNull(cola);
		assertEquals(cola.longitud(),0); //coincide y pasa en verde
	}

	@Category({Unit.class})
	@Test
	public void testlongitud() {
		ColaDeAmigos cola = new ColaDeAmigos();
		assertNotNull(cola);
		assertEquals(cola.longitud(),0); //coincide y pasa en verde
	}

	@Category({Unit.class})
	@Test
	public void testPrimero() {
		ColaDeAmigos cola = new ColaDeAmigos();
		assertNotNull(cola);
		cola.pideVez(manolito, 0);
		Persona pri = cola.primero();
		assertEquals(manolito,pri);	
	}

	@Category({Unit.class})
	@Test
	public void testUltimo() {
		ColaDeAmigos cola = new ColaDeAmigos();
		assertNotNull(cola);
		cola.pideVez(manolito, 0);
		Persona ult = cola.ultimo();
		assertEquals(manolito,ult);	
	}

	@Category({Unit.class})
	@Test
	public void testPideVez() {
		ColaDeAmigos cola = new ColaDeAmigos();
		assertNotNull(cola);
		assertEquals(cola.longitud(),0);
		cola.pideVez(manolito,0);
		assertEquals(cola.longitud(),1);
		Persona ult = cola.ultimo();
		assertEquals(manolito,ult);		
	}

	@Category({Integration.class})
	@Test
	public void testBuscaAmigosConHueco() {
		ColaDeAmigos cola = new ColaDeAmigos();
		Persona[] amigosDeJoselitoEnCola = cola.buscaAmigosConHueco(joselito);
		Persona[] expected = {};
		assertArrayEquals(amigosDeJoselitoEnCola,expected);
	}

	
	@Category({Integration.class})
	@Test
	public void testSeCuelaCon() {
		ColaDeAmigos cola = new ColaDeAmigos();
		cola.pideVez(manolito, 1);
		cola.seCuelaCon(joselito,manolito);
		assertEquals(cola.longitud(),2);
		assertEquals(joselito,cola.primero());
		assertEquals(manolito,cola.ultimo());
	}

	@Category({Unit.class})
	@Test
	public void testDameNumeroReservasInicial() {
		ColaDeAmigos cola = new ColaDeAmigos();
		cola.pideVez(manolito, 1);
		int amigosDeManolitoReservados = cola.dameNumeroReservasInicial(manolito);
		assertEquals(amigosDeManolitoReservados,1);
	}

	@Category({Unit.class})
	@Test
	public void testDameNumeroReservasActual() {
		ColaDeAmigos cola = new ColaDeAmigos();
		cola.pideVez(manolito,1);
		int amigosDeManolitoReservadosActualmente = cola.dameNumeroReservasActual(manolito);
		assertEquals(cola.dameNumeroReservasActual(manolito),1);
		cola.seCuelaCon(joselito, manolito);
		amigosDeManolitoReservadosActualmente = cola.dameNumeroReservasActual(manolito);
		assertEquals(cola.dameNumeroReservasActual(manolito),0);
	}

	@Category({Unit.class})
	@Test
	public void testDameAmigosColadosDe() {
		ColaDeAmigos cola = new ColaDeAmigos();
		cola.pideVez(manolito, 1);
		cola.seCuelaCon(joselito, manolito);
		Persona[] amigosColados = cola.dameAmigosColadosDe(manolito);
		Persona[] expected = {joselito};
		assertArrayEquals(amigosColados,expected);
	}

	@Category({Unit.class})
	@Test
	public void testAvanza() {
		ColaDeAmigos cola =new ColaDeAmigos();
		cola.pideVez(manolito, 0);
		assertEquals(cola.longitud(),1);
		assertEquals(cola.primero(),manolito);
		cola.avanza();
		assertEquals(cola.longitud(),0);
		assertEquals(cola.primero(),null);
		
	}

	@Category({Unit.class})
	@Test
	public void testDameNumeroAmigosColados() {
		ColaDeAmigos cola = new ColaDeAmigos();
		cola.pideVez(manolito, 1);
		int amigosDeManolitoColados = cola.dameNumeroAmigosColados(manolito);
		assertEquals(amigosDeManolitoColados,0);
		cola.seCuelaCon(joselito, manolito);
		amigosDeManolitoColados = cola.dameNumeroAmigosColados(manolito);
		assertEquals(amigosDeManolitoColados,1);
		assertEquals(cola.dameAmigosColadosDe(manolito).length,amigosDeManolitoColados); //da error porque quiero mirar la length de un null
	}
}
