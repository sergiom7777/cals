package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.easymock.Mock;
import org.junit.*;
import org.junit.experimental.categories.Category;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.*;
import org.easymock.Mock;
/**
 * Test Aislamiento.
 *
 */
@Category({Isolation.class})
public class ColaDeAmigosAislamientoTest {
	
	@Mock
	private Persona manolito;
	@Mock
	private Persona luisito;
	@Mock
	private Persona inesita;
	@Mock
	private Persona falsoAmigo;
	
	private ColaDeAmigos cola;
	@Before
	public void setUp() {
		manolito = createMock(Persona.class);
		luisito = createMock(Persona.class);
		inesita = createMock(Persona.class);
		
		falsoAmigo = createMock(Persona.class);
		
		expect(manolito.esAmigo(inesita)).andReturn(true).anyTimes();
		expect(manolito.esAmigo(luisito)).andReturn(true).anyTimes();
		expect(inesita.esAmigo(manolito)).andReturn(true).anyTimes();
		expect(luisito.esAmigo(manolito)).andReturn(true).anyTimes();
		expect(inesita.esAmigo(luisito)).andReturn(false).anyTimes();
		expect(luisito.esAmigo(inesita)).andReturn(false).anyTimes();
		expect(manolito.esAmigo(falsoAmigo)).andReturn(true).anyTimes();
		expect(falsoAmigo.esAmigo(manolito)).andReturn(false).anyTimes(); //tu no eres su amigo, pero el si es amigo tuyo
		replay(manolito, luisito, inesita);
		
		cola = new ColaDeAmigos();

	}
	
	@After
	public void tearDown() {
		manolito = null;
		luisito = null;
		inesita=null;
		cola=null;
	}
	
	//BuscaAmigos*************************************************************************
	@Test
	public void testBuscarAmigosConHueco() {
		assertTrue(inesita.esAmigo(manolito));
		cola.pideVez(manolito, 2);
		Persona[] amigos = cola.buscaAmigosConHueco(inesita);
		Persona[] expected = {manolito};
		assertArrayEquals(amigos, expected);
	}
	@Test
	public void testBuscarAmigosConHuecoSinHueco() {
		ColaDeAmigos cola = new ColaDeAmigos();
		cola.pideVez(manolito, 1);
		cola.seCuelaCon(luisito, manolito);
		Persona[] amigos = cola.buscaAmigosConHueco(inesita);
		Persona[] expected = {};
		assertArrayEquals(amigos, expected);
	}
	
	//SeCuelaCon*************************************************************************
	@Test
	public void testSeCuelaConElPrimeroEnLaCola() {
		
		cola.pideVez(manolito, 1);
		cola.pideVez(inesita, 1);
		cola.seCuelaCon(luisito, manolito);
		assertEquals(cola.primero(),luisito);
		assertEquals(cola.longitud(),3);
	}
	@Test
	public void testSeCuelaConPorElMedio() {
		
		cola.pideVez(inesita, 1);
		cola.pideVez(manolito, 1);
		cola.seCuelaCon(luisito, manolito);
		assertEquals(cola.primero(),inesita);
		assertEquals(cola.longitud(),3);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConNoSonAmigos() {
		cola.pideVez(inesita, 1);
		cola.pideVez(manolito, 1);
		cola.seCuelaCon(luisito, inesita);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSeCuelaConColado() {
		cola.pideVez(inesita, 1);
		cola.seCuelaCon(manolito, inesita);
		cola.seCuelaCon(luisito, manolito);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSeCuelaConNoTieneHueco() {
		cola.pideVez(manolito,1);
		cola.seCuelaCon(luisito, manolito);
		cola.seCuelaCon(inesita, manolito);
	}
	
	//Este test solo se puede probar con mocks
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConFalsoAmigoColado() {
		cola.pideVez(manolito, 1);
		cola.seCuelaCon(falsoAmigo, manolito);
	}

	//Este test solo se puede probar con mocks
	@Test(expected=IllegalArgumentException.class)
	public void testSeCuelaConFalsoAmigoColador() {
		cola.pideVez(falsoAmigo, 1);
		cola.seCuelaCon(manolito, falsoAmigo);
	}
	
	
	

}
