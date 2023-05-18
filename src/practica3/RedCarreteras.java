package practica3;

import java.security.InvalidParameterException;
import java.util.*;

public class RedCarreteras {
	private Map<String, Map<String, Integer>> red;

	/**
	 * Constructor
	 * 
	 * Crea una red de carreteras vacías.
	 */
	public RedCarreteras() {
		//TO DO
		red = new HashMap<>();
	}

	/**
	 * Valida que un tramo sea correcto.
	 * 
	 * Es decir, que ninguna de las dos ciudades sea null, y que la distancia sea
	 * mayor de cero. No se admiten tramos de una ciudad con sigo misma. En el
	 * caso de que un tramo no sea válido produce una excepción.
	 * 
	 * @param una,
	 *            una ciudad
	 * @param otra,
	 *            la otra ciudad
	 * @param distancia,
	 *            la distancia del tramo
	 * @throws InvalidParamenterException
	 *             si el tramo no es válido.
	 */
	private void validarTramo(String una, String otra, int distancia) {
		// TO DO
		if (una == null || otra == null || distancia <= 0)
			throw new InvalidParameterException();
		else if(una.equals(otra))
			throw new InvalidParameterException();
	}

	/**
	 * Devuelve un conjunto con todas las ciudades de la red.
	 */
	public Set<String> ciudades() {
		return red.keySet();
	}

	/**
	 * Añade un tramo a la red.
	 * 
	 * Valida que el tramo sea valido. Si alguna de las dos ciudades no existiá
	 * previamente en la red, la añade. El tramo se añadirá dos veces, una para
	 * cada ciudad. En caso de que el tramo ya existiera remplazará el valor de
	 * la distancia.
	 * 
	 * @return Distancia previa del tramo, -1 si el tramo no exitía.
	 * @throws InvalidParamenterException
	 *             si el tramo no es válido.
	 */
	public int nuevoTramo(String una, String otra, int distancia) {
		// TO DO
		Map<String, Integer> aux = new HashMap<>();
		Map<String, Integer> aux2 = new HashMap<>();
		validarTramo(una, otra, distancia);
		aux.put(otra, distancia);
		aux2.put(una, distancia);
		if (!red.containsKey(una))
			red.put(una, aux);
		else if (!red.get(una).containsKey(otra))
			red.get(una).put(otra, distancia);
		else if (red.get(una).containsKey(otra)) {
			red.get(una).put(otra, distancia);
			return distancia;
		}
		if (!red.containsKey(otra))
			red.put(otra, aux2);
		else if (!red.get(otra).containsKey(una))
			red.get(otra).put(una, distancia);
		else if (red.get(otra).containsKey(una)) {
			red.get(otra).put(una, distancia);
			return distancia;
		}
		return -1;
	}

	/**
	 * Comprueba si existe un tramo entre dos ciudades.
	 * 
	 * @return La distancia del tramo, o -1 si no existe
	 */
	public int existeTramo(String una, String otra) {
		// TO DO
		if (red.get(una).containsKey(otra))
			return red.get(una).get(otra);
		return -1;
	}

	/**
	 * Borra el tramo entre dos ciudades si existe.
	 * 
	 * @return La distancia del tramo, o -1 si no existía.
	 */
	public int borraTramo(String una, String otra) {
		// TO DO
		int distancia = -1;
		if (red.get(una).containsKey(otra)) {
			distancia = existeTramo(una, otra);
			red.get(una).remove(otra);
		}
		if (red.get(otra).containsKey(una)) {
			distancia = existeTramo(otra, una);
			red.get(otra).remove(una);
		}
		return distancia;
	}

	/**
	 * Comprueba si un camino es posible.
	 * 
	 * Un camino es una lista de ciudades. El camino es posible si existe un
	 * tramo entre cada para de ciudades consecutivas. Si dos ciudades
	 * consecutivas del camino son la misma el camino es posible y la distancia
	 * añadida es 0. Si el camino incluye una sóla ciudad de la red el resultado es 0.
	 * 
	 * @return La distancia total del camino, es decir la suma de las distancias
	 *         de los tramos, o -1 si el camino no es posible o no incluye ninguna ciudad.
	 */
	public int compruebaCamino(List<String> camino) {
		// TODO
		ListIterator<String> siguiente = camino.listIterator();
		ListIterator<String> it = camino.listIterator();
		if (camino.size() <= 0)
			return -1;
		else if (camino.size() == 1)
			return 0;
		int distancia = 0;
		siguiente.next();
		while (siguiente.hasNext()) {
			String ciudad1 = it.next();
			String ciudad2 = siguiente.next();
			if (ciudad1 == null || ciudad2 == null)
				return -1;
			if (red.get(ciudad1).containsKey(ciudad2))
				distancia += existeTramo(ciudad1, ciudad2);
			else if (ciudad1.equals(ciudad2));
			else
				return -1;
		}
		return distancia;
	}

}
