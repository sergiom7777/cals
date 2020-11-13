package uva.inf.parasonarqube;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ColaDeAmigosInicialTest.class,
	ColaDeAmigosCajaNegraNullTest.class,
	ColaDeAmigosCajaNegraOtrosTest.class,
	ColaDeAmigosCajaNegraColaVaciaTest.class,
	ColaDeAmigosSecuenciaTest.class,
	ColaDeAmigosCajaBlancaTest.class,
	ColaDeAmigosAislamientoTest.class
    })

/**
 * Suite que contiene al resto de test de ColaDeAmigos.
 *
 */
public class ColaDeAmigosTest {

}
