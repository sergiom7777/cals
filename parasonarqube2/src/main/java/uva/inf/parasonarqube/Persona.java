package uva.inf.parasonarqube;

import java.util.ArrayList;

/**
 * Implementacion de la clase Persona.
 * 
 */
public class Persona {
	private static final String P_NO_PUEDE_SER_NULL = "p no puede ser null";
	private static final String Q_NO_PUEDE_SER_NULL = "q no puede ser null";
	private static final String NOMBRE_NO_PUEDE_SER_NULL = "nombre no puede ser null";
	protected String nombre;
	protected ArrayList<Persona> conocidos;
	protected ArrayList<Persona> amigos;

	/**
	 * Constructor de Persona.
	 * 
	 * @param nombre
	 *            Nombre que va a tener la persona.
	 * @throws IllegalArgumentException
	 *             si nombre es null
	 */
	public Persona(String nombre) {
		if (nombre == null) {
			throw new IllegalArgumentException(NOMBRE_NO_PUEDE_SER_NULL);
		}
		this.nombre = nombre;
		conocidos = new ArrayList<>();
		amigos = new ArrayList<>();
	}

	/**
	 * Devuelve el nombre de la persona.
	 * 
	 * @return cadena de caracteres que representa el nombre de la persona.
	 */
	public String dameNombre() {
		return nombre;
	}

	/**
	 * Permite saber si dos personas se conocen entre si. Si una persona conoce a
	 * otra implica que la otra conoce a la una.
	 * 
	 * @param q
	 *            Persona de la que se quiere saber si esta tiene conocimiento.
	 * @return true si lo conoce, false si no.
	 * @throws IllegalArgumentException
	 *             si q es null.
	 */
	public boolean esConocido(Persona q) {
		if (q == null) {
			throw new IllegalArgumentException(Q_NO_PUEDE_SER_NULL);
		}
		return conocidos.indexOf(q)!=-1;
	}

	/**
	 * Permite hacer que dos personas se conozcan.
	 * 
	 * @param p
	 *            una persona.
	 * @param q
	 *            otra persona.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si q es null.
	 */
	public static void creaConocimiento(Persona p, Persona q) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (q == null) {
			throw new IllegalArgumentException(Q_NO_PUEDE_SER_NULL);
		}
		p.addConocido(q);
		q.addConocido(p);
	}

	private void addConocido(Persona r) {
		conocidos.add(r);
	}

	/**
	 * Permite saber si dos personas son amigos. Si una persona es amigo de otra
	 * implica que la otra es aimgo de la una.
	 * 
	 * @param q
	 *            Persona de la que se quiere saber si esta es amigo.
	 * @return true si son amigos, false si no lo son.
	 * @throws IllegalArgumentException
	 *             si q es null.
	 */
	public boolean esAmigo(Persona q) {
		if (q == null) {
			throw new IllegalArgumentException(Q_NO_PUEDE_SER_NULL);
		}
		return amigos.indexOf(q)!=-1;
	}

	/**
	 * Permite crear amistad entre dos personas. Ambas personas deben conocerse
	 * previamente.
	 * 
	 * @param p
	 *            una persona.
	 * @param q
	 *            otra persona.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si q es null.
	 * @throws IllegalStateException
	 *             si p y q no se conocen de antes.
	 */
	public static void creaAmistad(Persona p, Persona q) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (q == null) {
			throw new IllegalArgumentException(Q_NO_PUEDE_SER_NULL);
		}
		if (!(p.esConocido(q) && q.esConocido(p))) {
			throw new IllegalStateException("p y q deben ser conocidos");
		}
		p.addAmigo(q);
		q.addAmigo(p);
	}

	private void addAmigo(Persona r) {
		amigos.add(r);
	}

	/**
	 * Permite obtener las personas conocidas por una persona.
	 * 
	 * @return array con las personas que conoce.
	 */
	public Persona[] dameConocidos() {
		Persona[] array = new Persona[conocidos.size()];
		array = conocidos.toArray(array);
		return array;
	}

	/**
	 * Permite obtener los amigos de una persona.
	 * 
	 * @return array con los amigos de una persona.
	 */
	public Persona[] dameAmigos() {
		Persona[] array = new Persona[amigos.size()];
		array = amigos.toArray(array);
		return array;
	}

	/**
	 * Permite poner fin a la amistad entre dos personas dejandolas unicamente como
	 * conocidos.
	 * 
	 * @param p
	 *            una persona.
	 * @param q
	 *            otra persona.
	 * @throws IllegalArgumentException
	 *             si p es null.
	 * @throws IllegalArgumentException
	 *             si q es null.
	 * @throws IllegalStateException
	 *             si p y q no son amigos de antes.
	 */
	public static void rompeAmistad(Persona p, Persona q) {
		if (p == null) {
			throw new IllegalArgumentException(P_NO_PUEDE_SER_NULL);
		}
		if (q == null) {
			throw new IllegalArgumentException(Q_NO_PUEDE_SER_NULL);
		}
		if (!(p.esAmigo(q) && q.esAmigo(p))) {
			throw new IllegalStateException("p y q deben ser amigos");
		}
		p.removeAmigo(q);
		q.removeAmigo(p);
	}

	private void removeAmigo(Persona r) {
		int index = amigos.indexOf(r);
		amigos.remove(index);
	}

}
