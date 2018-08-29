package es.mpr.template.pool;

import org.apache.commons.pool.ObjectPool;

//
/**
 * Clase estatica donde se almacena el pool de objetos.
 */

public class staticPool {
	
	/**  pool. */
	public static ObjectPool pool=null;

	/**
	 * Obtener pool.
	 *
	 * @return pool
	 */
	public static ObjectPool getPool() {
		return pool;
	}

	/**
	 * Modificar pool.
	 *
	 * @param pool new pool
	 */
	public static void setPool(ObjectPool pool) {
		staticPool.pool = pool;
	}
}
