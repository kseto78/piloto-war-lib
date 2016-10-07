package es.mpr.template.pool;

import org.apache.commons.pool.ObjectPool;
//
/**
 * Clase estatica donde se almacena el pool de objetos
 */

public class staticPool {
	public static ObjectPool pool=null;

	public static ObjectPool getPool() {
		return pool;
	}

	public static void setPool(ObjectPool pool) {
		staticPool.pool = pool;
	}
}
