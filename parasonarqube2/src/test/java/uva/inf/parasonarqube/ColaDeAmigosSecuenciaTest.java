package uva.inf.parasonarqube;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.experimental.categories.Category;
/**
 * Test de secuencia
 *
 */
@Category({Sequence.class, Integration.class})
public class ColaDeAmigosSecuenciaTest {
	
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
	
	@Test
	public void testSecuenciaValida() {
		//Llega manolito a la cola y reserva para 3 amigos
		cola.pideVez(manolito, 3);
		
		//Llega fernandito y pide vez reservando para el y un amigo
		cola.pideVez(fernandito, 1);
		
		//Llega inesita y busca amigos en la cola
		Persona[] amigosDeInesita = cola.buscaAmigosConHueco(inesita);
		Persona[] comprobarAmigosInesita = {manolito};
		assertArrayEquals(amigosDeInesita,comprobarAmigosInesita);
		
		//Inesita pide colarse con manolito quedando al principio de la cola
		cola.seCuelaCon(inesita, manolito);
		assertEquals(cola.primero(),inesita);
		
		//Llega jorgito y se cuela con fernandito
		cola.seCuelaCon(jorgito, fernandito);
		//La longitud de la cola asciende a 4 personas y fernandito sigue siendo el ultimo
		assertEquals(cola.longitud(),4);
		assertEquals(cola.ultimo(),fernandito);
		
		//La cola avanza e Inesita es atendida
		cola.avanza();

		//Manolito queda al frente de la cola
		assertEquals(cola.primero(),manolito);
		
		//La cola avanza de nuevo y manolito pasa sin haber colado a sus otros dos amigos
		cola.avanza();
		assertEquals(cola.primero(),jorgito);
		assertEquals(cola.longitud(),2);
		
		//Llega luisito y busca algun amigo con quien colarse (Busca a manolito)
		Persona[] amigosDeLuisito = cola.buscaAmigosConHueco(luisito);
		Persona[] comprobarAmigosLuisito = {};
		assertArrayEquals(amigosDeLuisito,comprobarAmigosLuisito);
		
		//Luisito pide vez
		cola.pideVez(luisito, 0);
		assertEquals(cola.ultimo(),luisito);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSecuenciaNoValida1() {
		cola.pideVez(manolito, 2);
		cola.pideVez(fernandito, 1);
		cola.seCuelaCon(inesita, manolito);
		cola.seCuelaCon(sandrita, manolito);
		cola.seCuelaCon(jorgito, fernandito);
		
		//Juanito intenta colarse con manolito, que ha cubierto su cupo de amigos
		cola.seCuelaCon(luisito, manolito);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSecuenciaNoValida2() {
		cola.pideVez(manolito, 2);
		cola.pideVez(fernandito, 1);
		cola.seCuelaCon(inesita, manolito);
		cola.seCuelaCon(sandrita, manolito);
		
		//Juanito intenta colarse con fernandito, que no es su amigo
		cola.seCuelaCon(juanito, fernandito);
	}
	
	@Test(expected=IllegalStateException.class)
	public void testSecuenciaNoValida3() {
		cola.pideVez(manolito, 2);
		cola.pideVez(fernandito, 1);
		cola.seCuelaCon(inesita, manolito);
		cola.seCuelaCon(sandrita, manolito);
		cola.seCuelaCon(jorgito, fernandito);
		
		//Juanito intenta colarse con sandrita, que ha sido colada por manolito
		cola.seCuelaCon(juanito, sandrita);
	}

	@Test(expected=IllegalStateException.class)
	public void testSecuenciaNoValida4() {
		cola.pideVez(manolito, 2);
		cola.pideVez(fernandito, 1);
		cola.seCuelaCon(inesita, manolito);
		cola.seCuelaCon(sandrita, manolito);
		cola.seCuelaCon(jorgito, fernandito);
		assertEquals(cola.longitud(),5);
		
		cola.avanza();
		cola.avanza();
		cola.avanza();
		cola.avanza();
		cola.avanza();
		assertEquals(cola.longitud(),0);
		//La cola avanza cuando esta vacia
		cola.avanza();
	}
}
