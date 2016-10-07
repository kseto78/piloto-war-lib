package es.minhap.plataformamensajeria.iop.jdbc;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import oracle.jdbc.driver.OracleDriver;



/**
 * @Descripci�n: Clase de utilidad para establecer la conexi�n con la base de
 *               datos y manejar las conexiones abiertas
 * @author i-nercya
 * @version 1.0
 * 
 */
public class PlataformaMensajeriaIOPDAO {
	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * Objeto a través del cual se realizar� la comunicaci�n con la base de
	 * datos
	 */
	private Connection conn = null;

	Driver driver = null;
	
	private String USER_NAME="USER_NAME";
	private String PASSWORD="PASSWORD";
	private String URL="URL";

	public static PlataformaMensajeriaIOPDAO instance = null;

	public static PlataformaMensajeriaIOPDAO getInstance() {

		if (instance == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException ex) {
				System.exit(1);
			}
			
			instance = new PlataformaMensajeriaIOPDAO();
			
		}

		return instance;
	}

	public PlataformaMensajeriaIOPDAO() {
	
		try {
			loadProperties();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.exit(0);			
		}
		driver = new OracleDriver();
		try {
			DriverManager.registerDriver( driver );
		} catch (SQLException e) {
			System.exit(1);
		}
	}

	public void closeAll() {
		
		if (conn != null) {
			try {
				logger.info("Closing SQL Connection for " + getClass());
				conn.close();
			} catch (SQLException e) {
				logger.warn("Could not close SQL Connection", e);
			}
			conn = null;
		}
	}

	/**
	 * Devuelve la conexi�n
	 * 
	 * @return
	 */
	public Connection getConn() {
		beginTransaction();
		return conn;
	}

	/**
	 * Cierra la conexi�n de forma segura
	 * 
	 * @return Devuelve verdadero si exist�a una conexi�n abierta y se ha podido
	 *         cerrar. Devuelve falso si no exist�a una conexi�n abierta.
	 * @exception Devuelve
	 *                una excepci�n si se produce un error al intentar cerrar
	 *                una conexi�n abierta
	 */
	public boolean connClose() {

		if (conn != null) {
			try {
				logger.info("Closing SQL Connection for " + getClass());
				conn.close();
			} catch (SQLException e) {
				logger.warn("Could not close SQL Connection", e);
			}
			conn = null;
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param sqlObject
	 * @return Devuelve verdadero si el objeto que se pasa como par�metro no es
	 *         nulo y se ha podido cerrar correctamente. Devuelve falso si el
	 *         objeto es nulo o no se ha podido cerrar
	 * @exception Lanza
	 *                una excepci�n si no se ha podido cerrar un objeto
	 */
	public boolean closeSQLObject(Object sqlObject) {
		if (sqlObject != null) {
			if (sqlObject instanceof CallableStatement) {
				CallableStatement callableStmt = (CallableStatement) sqlObject;
				try {
					callableStmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sqlObject instanceof PreparedStatement) {
				PreparedStatement pstmt = (PreparedStatement) sqlObject;
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sqlObject instanceof ResultSet) {
				ResultSet rs = (ResultSet) sqlObject;
				try {
					rs.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (sqlObject instanceof Connection) {
				Connection con = (Connection) sqlObject;
				try {
					logger.debug("Closing SQL Connection for " + getClass());
					con.close();
				} catch (SQLException e) {
					logger.warn("Could not close SQL Connection", e);
				}
				if (con == conn) {
					conn = null;
				}
				return true;
			}

			return false;
		} else {
			return false;
		}
	}

	public void beginTransaction() {

		try {
			if (conn == null) {
				logger.info("Opening SQL Connection for " + getClass());
				conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
				conn.setAutoCommit(false);
//				DataSource ds = (DataSource) new InitialContext().lookup("java:jboss/datasources/dsMsgplt");
//			    conn = ds.getConnection();
//			    conn.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

	}

	public void endTransaction(boolean commit) {
		if (conn != null) {
			try {
				if (commit) {
					conn.commit();
				} else {
					conn.rollback();
				}
				try {
					logger.info("Closing SQL Connection for " + getClass());
					conn.close();
				} catch (SQLException e) {
					logger.warn("Could not close SQL Connection", e);
				}
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void loadProperties() throws Exception {
		   Properties p = new Properties();
		   InputStream is = PlataformaMensajeriaIOPDAO.class.getResourceAsStream("/properties/dataconnection.properties");
		   //NO SUBIR ESTA LINEA DESCOMENTADA NUNCA, LA RUTA CORRECTA ES "/properties/dataconnection.properties"
//		   InputStream is = PlataformaMensajeriaIOPDAO.class.getResourceAsStream("dataconnection.properties");
		   p.load(is);
		 
		   
		   USER_NAME = p.getProperty(USER_NAME);
		   PASSWORD = p.getProperty(PASSWORD);
		   URL = p.getProperty(URL);
		   
		  
		   System.out.flush();
		   
		   is.close();
	 		   
		 
		}

	// Por seguridad, hacemos una liberacion al destruir el objeto
	@Override
	protected void finalize() throws Throwable {
		if (conn != null) {
			logger.warn("Se va a limpiar la conexion de " + getClass() + " en el destructor");
			try {
				logger.info("Closing SQL Connection for " + getClass());
				conn.close();
			} catch (SQLException e) {
				// Ya se ha pintado una traza, se ignora la excepcion;
			}
		}
		super.finalize();
	}
	
}
