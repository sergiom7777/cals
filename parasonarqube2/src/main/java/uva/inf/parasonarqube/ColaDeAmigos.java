package uva.inf.parasonarqube;

import java.util.ArrayList;

/**
 * Implemantacion de la clase ColaDeAmigos.
 * 
 */
public class ColaDeAmigos {
	private static final String P_NO_ESTA_INCLUIDA_EN_LA_COLA = "p no esta incluida en la cola";
	private static final String P_HA_SIDO_COLADA_Y_NO_HA_RESERVADO = "p ha sido colada y no ha reservado";
	private static final String P_YA_ESTA_INCLUIDA = "p ya esta incluida";
	private static final String P_NO_PUEDE_SER_NULL = "p no puede ser null";
	protected ArrayList<Persona> fila;
	protected ArrayList<Integer> huecos;

	/**
	 * Constructor de ColaDeAmigos
	 */
	public ColaDeAmigos() {
		fila = new ArrayList<>();
		huecos = new ArrayList<>();
	}

	/**
	 * Permite conocer la longitud de la cola.
	 * 
	 * @return Entero positivo.
	 */
	public int longitud() {
		if (fila.isEmpty()) {
			return 0;
		}

		int contador = 0;
		for (int i = 0; i < fila.size(); i++) {
			if (fila.get(i) != null) {
				contador++;
			}
		}
		return contador;
	}

	/**
	 * Permite a una persona situarse al final de la cola y reservar sitio para sus
	 * amigos.
	 * 
	 * @param p
	 *            Persona que pide la vez.
	 * @param amigos
	 *            Numero de amigos para los que reserva sitio. Comprendido en un
	 *            rango entre 0 y 10 ambos inclusive.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si amigos es menor que cero.
	 * @throws IllegalArgumentException
	 *             si amigos es mayor que diez.
	 * @throws IllegalArgumentException
	 *             si p ya esta en la cola.
	 */
	public void pideVez(Persona p, int amigos) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)!=-1) {
			throw new IllegalArgumentException(P_YA_ESTA_INCLUIDA);
		}
		if (amigos < 0) {
			throw new IllegalArgumentException("amigos no puede ser negativo");
		}
		if (amigos > 10) {
			throw new IllegalArgumentException("amigos no puede ser mayor que diez");
		}

		for (int i = 0; i < amigos; i++) {
			fila.add(null);
			huecos.add(null);
		}
		fila.add(p);
		huecos.add(amigos);

	}

	/**
	 * Permite conocer quien es el ultimo en la cola.
	 * 
	 * @return Persona situada al final de la cola.
	 */
	public Persona ultimo() {
		if (fila.isEmpty()) {
			return null;
		}
		return fila.get(fila.size() - 1);
	}

	/**
	 * Proporciona las personas amigas a otra persona que estan aguardando cola.
	 * 
	 * @param p
	 *            Persona que quiere saber que amigos tiene en la cola.
	 * @return Array con los amigos en la cola de la persona que pregunta.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si p esta ya en la cola.
	 */
	public Persona[] buscaAmigosConHueco(Persona p) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)!=-1) {
			throw new IllegalArgumentException(P_YA_ESTA_INCLUIDA);
		}

		ArrayList<Persona> amigos = new ArrayList<>();
		for (int i = 0; i < fila.size(); i++) {
			Persona q = fila.get(i);
			if (q != null) {
				amigoConHueco(p, amigos, q);
			}
		}

		Persona[] array = new Persona[amigos.size()];
		array = amigos.toArray(array);
		return array;
	}

	private void amigoConHueco(Persona p, ArrayList<Persona> amigos, Persona q) {
		if (q.esAmigo(p) && numeroHuecosDisponibles(q) > 0) {
			amigos.add(q);
		}
	}

	/**
	 * Permite a una persona que llega a la cola colarse junto a otro amigo.
	 * 
	 * @param p
	 *            Persona que se cuela (Colado).
	 * @param q
	 *            Persona que cuela (Colador).
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si p esta ya en la cola.
	 * @throws IllegalArgumentException
	 *             si q es null.
	 * @throws IllegalArgumentException
	 *             si q no esta en la cola.
	 * @throws IllegalArgumentException
	 *             si p y q no son amigos.
	 * @throws IllegalStateException
	 *             si q no tiene hueco para colar a p.
	 * @throws IllegalStateException
	 *             si q es una persona que ha sido colada previemante.
	 */
	public void seCuelaCon(Persona p, Persona q) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)!=-1) {
			throw new IllegalArgumentException(P_YA_ESTA_INCLUIDA);
		}
		if (q == null) {
			throw new IllegalArgumentException("q no puede ser null");
		}
		if (fila.indexOf(q)==-1) {
			throw new IllegalArgumentException("q no esta incluida");
		}
		if (!(p.esAmigo(q) && q.esAmigo(p))) {
			throw new IllegalArgumentException("p y q deben ser amigos");
		}
		if (haSidoColada(q)) {
			throw new IllegalStateException("q ha sido colado y no puede colar");
		}
		if (numeroHuecosDisponibles(q) == 0) {
			throw new IllegalStateException("q no tiene hueco para colar a nadie mas");
		}

		int huecosDeQ = numeroHuecosDisponibles(q);
		int posicionDeQ = fila.indexOf(q);
		fila.add(posicionDeQ - huecosDeQ, p);
		fila.remove(posicionDeQ - huecosDeQ + 1);

	}

	private int numeroHuecosDisponibles(Persona p) {
		int index = fila.indexOf(p) - 1;
		int contador = 0;
		while (index >= 0 && fila.get(index) == null) {
			contador++;
			index--;
		}
		return contador;
	}

	private boolean haSidoColada(Persona p) {
		int index = fila.indexOf(p);
		return huecos.get(index) == null;
	}

	/**
	 * Permite conocer quien es el primero de la cola.
	 * 
	 * @return Persona situada al principio de la cola.
	 */
	public Persona primero() {
		if (fila.isEmpty()) {
			return null;
		}
		int index = 0;
		while (fila.get(index) == null) {
			index++;
		}

		return fila.get(index);
	}

	/**
	 * Permite conocer el numero de amigos que dijo una persona cuando se situo al
	 * final de la cola.
	 * 
	 * @param p
	 *            Persona de la que se quiere saber los amigos iniciales.
	 * @return numero entero entre cero y diez.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si p no esta en la cola.
	 * @throws IllegalArgumentException
	 *             si p ha sido colado.
	 */
	public int dameNumeroReservasInicial(Persona p) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)==-1) {
			throw new IllegalArgumentException(P_NO_ESTA_INCLUIDA_EN_LA_COLA);
		}
		if (haSidoColada(p)) {
			throw new IllegalArgumentException(P_HA_SIDO_COLADA_Y_NO_HA_RESERVADO);
		}

		return (int) huecos.get(fila.indexOf(p));
	}

	/**
	 * Permite conocer el numero de amigos que una persona puede colar aun.
	 * 
	 * @param p
	 *            Persona de la que se quiere saber los amigos que le quedan por
	 *            colar.
	 * @return numero entero entre cero y diez.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si p no esta en la cola.
	 * @throws IllegalArgumentException
	 *             si p ha sido colado.
	 */
	public int dameNumeroReservasActual(Persona p) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)==-1) {
			throw new IllegalArgumentException(P_NO_ESTA_INCLUIDA_EN_LA_COLA);
		}
		if (haSidoColada(p)) {
			throw new IllegalArgumentException(P_HA_SIDO_COLADA_Y_NO_HA_RESERVADO);
		}

		return numeroHuecosDisponibles(p);
	}

	/**
	 * Proporciona los amigos que ha colado una persona.
	 * 
	 * @param p
	 *            Persona que cuela
	 * @return array con los amigos colados.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si p no esta en la cola.
	 * @throws IllegalArgumentException
	 *             si p ha sido colado.
	 */
	public Persona[] dameAmigosColadosDe(Persona p) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)==-1) {
			throw new IllegalArgumentException(P_NO_ESTA_INCLUIDA_EN_LA_COLA);
		}
		if (haSidoColada(p)) {
			throw new IllegalArgumentException(P_HA_SIDO_COLADA_Y_NO_HA_RESERVADO);
		}

		ArrayList<Persona> amigos = new ArrayList<>();
		int index = fila.indexOf(p);
		int back = index - huecos.get(index);
		if (back < 0) {
			for (int i = 0; i < index; i++) {
				if (fila.get(i) != null) {
					amigos.add(fila.get(i));
				}
			}
		} else {
			index--;
			while (index >= back) {
				if (fila.get(index) != null) {
					amigos.add(fila.get(index));
				}
				index--;
			}
		}

		Persona[] array = new Persona[amigos.size()];
		array = amigos.toArray(array);
		return array;
	}

	/**
	 * Simula el avance de la cola. El primero será atendido y eliminado de la cola,
	 * avanzado una posicion el resto de personas en la cola
	 * 
	 * @throws IllegalStateException
	 *             si la cola esta vacia
	 */
	public void avanza() {
		if (fila.isEmpty()) {
			throw new IllegalStateException("No se puede avanzar sobre una cola vacia");
		}

		Persona primero = primero();
		int index = fila.indexOf(primero);
		for (int i = 0; i <= index; i++) {
			fila.remove(0);
			huecos.remove(0);
		}

	}

	/**
	 * Devuelve el numero de amigos que p ha colado.
	 * 
	 * @param p
	 *            Persona de los que se quiere conocer la cantidad de amigos que ha
	 *            colado.
	 * @return Entero entre cero y diez.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si p no esta en la cola.
	 * @throws IllegalArgumentException
	 *             si p ha sido colado.
	 */
	public int dameNumeroAmigosColados(Persona p) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (fila.indexOf(p)==-1) {
			throw new IllegalArgumentException(P_NO_ESTA_INCLUIDA_EN_LA_COLA);
		}
		if (haSidoColada(p)) {
			throw new IllegalArgumentException(P_HA_SIDO_COLADA_Y_NO_HA_RESERVADO);
		}

		return dameAmigosColadosDe(p).length;
	}

}
