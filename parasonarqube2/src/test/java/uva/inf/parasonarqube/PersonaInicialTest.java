package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * Test iniciales para crear el stub de Persona.
 */
@Category({Unit.class,TDD.class})
public class PersonaInicialTest {

	@Test
	public void testPersona() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		assertEquals(p.dameNombre(),"Pepito");
	}
	
	@Test
	public void testDameNombre() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		String nombre = p.dameNombre();
		assertNotNull(nombre);
		assertEquals(nombre, "Pepito");
	}
	
	@Test
	public void testEsConocido() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esConocido(q));
		assertFalse(q.esConocido(p));
		
		Persona.creaConocimiento(p,q);
		
		assertTrue(p.esConocido(q));
		assertTrue(q.esConocido(p));
	}
	
	@Test
	public void testEsAmigo() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esConocido(q));
		assertFalse(q.esConocido(p));

		assertFalse(p.esAmigo(q));
		assertFalse(q.esAmigo(p));
		
		Persona.creaConocimiento(p,q);
		
		assertTrue(p.esConocido(q));
		assertTrue(q.esConocido(p));
		
		assertFalse(p.esAmigo(q));
		assertFalse(q.esAmigo(p));
		
		Persona.creaAmistad(p,q);
		
		assertTrue(p.esConocido(q));
		assertTrue(q.esConocido(p));
		
		assertTrue(p.esAmigo(q));
		assertTrue(q.esAmigo(p));	
	}
		
	@Test
	public void testCreaConocimiento() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedAntes = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedAntes = {};
		assertArrayEquals(conocidosDePepitoObtainedAntes,conocidosDePepitoObtainedAntes);
		assertFalse(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedAntes = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedAntes = {};
		assertArrayEquals(conocidosDeJuanitoObtainedAntes,conocidosDeJuanitoObtainedAntes);
		
		Persona.creaConocimiento(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues,conocidosDePepitoObtainedDespues);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues,conocidosDeJuanitoObtainedDespues);
	}
	
	@Test
	public void testCreaAmistad() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedAntes = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedAntes = {};
		assertArrayEquals(conocidosDePepitoObtainedAntes,conocidosDePepitoObtainedAntes);
		assertFalse(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedAntes = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedAntes = {};
		assertArrayEquals(conocidosDeJuanitoObtainedAntes,conocidosDeJuanitoObtainedAntes);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes = {};
		assertArrayEquals(amigosDePepitoObtainedAntes,amigosDePepitoObtainedAntes);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes = {};
		assertArrayEquals(conocidosDeJuanitoObtainedAntes,conocidosDeJuanitoObtainedAntes);
		
		Persona.creaConocimiento(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues,conocidosDePepitoObtainedDespues);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues,conocidosDeJuanitoObtainedDespues);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes2 = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes2 = {};
		assertArrayEquals(amigosDePepitoObtainedAntes2,amigosDePepitoObtainedAntes2);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes2 = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes2 = {};
		assertArrayEquals(amigosDeJuanitoObtainedAntes2,amigosDeJuanitoObtainedAntes2);
		
		Persona.creaAmistad(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues2 = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues2 = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues2,conocidosDePepitoObtainedDespues2);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues2 = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues2 = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues2,conocidosDeJuanitoObtainedDespues2);
		
		assertTrue(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedDespues = p.dameAmigos();
		Persona[] amigosDePepitoExpectedDespues = {q};
		assertArrayEquals(amigosDePepitoObtainedDespues,amigosDePepitoObtainedDespues);
		assertTrue(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedDespues = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedDespues = {p};
		assertArrayEquals(amigosDeJuanitoObtainedDespues,amigosDeJuanitoObtainedDespues);
	}
	
	@Test
	public void testRompeAmistad() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedAntes = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedAntes = {};
		assertArrayEquals(conocidosDePepitoObtainedAntes,conocidosDePepitoObtainedAntes);
		assertFalse(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedAntes = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedAntes = {};
		assertArrayEquals(conocidosDeJuanitoObtainedAntes,conocidosDeJuanitoObtainedAntes);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes = {};
		assertArrayEquals(amigosDePepitoObtainedAntes,amigosDePepitoObtainedAntes);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes = {};
		assertArrayEquals(conocidosDeJuanitoObtainedAntes,conocidosDeJuanitoObtainedAntes);
		
		Persona.creaConocimiento(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues,conocidosDePepitoObtainedDespues);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues,conocidosDeJuanitoObtainedDespues);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes2 = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes2 = {};
		assertArrayEquals(amigosDePepitoObtainedAntes2,amigosDePepitoObtainedAntes2);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes2 = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes2 = {};
		assertArrayEquals(amigosDeJuanitoObtainedAntes2,amigosDeJuanitoObtainedAntes2);
		
		Persona.creaAmistad(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues2 = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues2 = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues2,conocidosDePepitoObtainedDespues2);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues2 = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues2 = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues2,conocidosDeJuanitoObtainedDespues2);
		
		assertTrue(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes3 = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes3 = {q};
		assertArrayEquals(amigosDePepitoObtainedAntes3,amigosDePepitoObtainedAntes3);
		assertTrue(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes3 = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes3 = {p};
		assertArrayEquals(amigosDeJuanitoObtainedAntes3,amigosDeJuanitoObtainedAntes3);
		
		Persona.rompeAmistad(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues3 = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues3 = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues3,conocidosDePepitoObtainedDespues3);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues3 = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues3 = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues3,conocidosDeJuanitoObtainedDespues3);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedDespues = p.dameAmigos();
		Persona[] amigosDePepitoExpectedDespues = {};
		assertArrayEquals(amigosDePepitoObtainedDespues,amigosDePepitoObtainedDespues);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedDespues = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedDespues = {};
		assertArrayEquals(amigosDeJuanitoObtainedDespues,amigosDeJuanitoObtainedDespues);	
	}
	
	@Test
	public void testDameConocidos() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedAntes = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedAntes = {};
		assertArrayEquals(conocidosDePepitoObtainedAntes,conocidosDePepitoObtainedAntes);
		assertFalse(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedAntes = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedAntes = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedAntes,conocidosDeJuanitoObtainedAntes);
		
		Persona.creaConocimiento(p,q);
		
		assertTrue(p.esConocido(q));
		Persona[] conocidosDePepitoObtainedDespues = p.dameConocidos();
		Persona[] conocidosDePepitoExpectedDespues = {q};
		assertArrayEquals(conocidosDePepitoObtainedDespues,conocidosDePepitoObtainedDespues);
		assertTrue(q.esConocido(p));
		Persona[] conocidosDeJuanitoObtainedDespues = q.dameConocidos();
		Persona[] conocidosDeJuanitoExpectedDespues = {p};
		assertArrayEquals(conocidosDeJuanitoObtainedDespues,conocidosDeJuanitoObtainedDespues);
	}
	
	@Test
	public void testDameAmigos() {
		Persona p = new Persona("Pepito");
		assertNotNull(p);
		Persona q = new Persona("Juanito");
		assertNotNull(q);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes = {};
		assertArrayEquals(amigosDePepitoObtainedAntes,amigosDePepitoObtainedAntes);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes = {};
		assertArrayEquals(amigosDeJuanitoObtainedAntes,amigosDeJuanitoObtainedAntes);
		
		Persona.creaConocimiento(p,q);
		
		assertFalse(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedAntes2 = p.dameAmigos();
		Persona[] amigosDePepitoExpectedAntes2 = {};
		assertArrayEquals(amigosDePepitoObtainedAntes2,amigosDePepitoObtainedAntes2);
		assertFalse(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedAntes2 = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedAntes2 = {};
		assertArrayEquals(amigosDeJuanitoObtainedAntes2,amigosDeJuanitoObtainedAntes2);
		
		Persona.creaAmistad(p,q);
		
		assertTrue(p.esAmigo(q));
		Persona[] amigosDePepitoObtainedDespues = p.dameAmigos();
		Persona[] amigosDePepitoExpectedDespues = {q};
		assertArrayEquals(amigosDePepitoObtainedDespues,amigosDePepitoObtainedDespues);
		assertTrue(q.esAmigo(p));
		Persona[] amigosDeJuanitoObtainedDespues = q.dameAmigos();
		Persona[] amigosDeJuanitoExpectedDespues = {p};
		assertArrayEquals(amigosDeJuanitoObtainedDespues,amigosDeJuanitoObtainedDespues);
	}

}
