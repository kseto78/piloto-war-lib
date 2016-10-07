package es.minhap.plataformamensajeria.sm.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import es.minhap.plataformamensajeria.sm.modelo.Historico;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosProveedor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosReceptor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidor;
import es.minhap.plataformamensajeria.sm.modelo.ParametrosServidorPush;

public class JDBCAccessTest {

	private static JDBCAccess jdbc = new JDBCAccess();

	public Connection conn = null;
	private Boolean stopService;
	private String connectionString;
	private String bbddUser;
	private String bbddPassword;
	private String urlSMS;

	private Integer maxSecondsRetryBBDD = 60;

	public void initializeVariables() {
		try {

			Properties configFile = new Properties();
			InputStream in = JDBCAccessTest.class.getResourceAsStream("configuration.properties");
			configFile.load(in);
			in.close();

			connectionString = configFile.getProperty("DatabaseConectionString");
			bbddUser = configFile.getProperty("DatabaseUser");
			bbddPassword = configFile.getProperty("DatabasePassword");
			urlSMS = configFile.getProperty("UrlSMS");

			try {
				maxSecondsRetryBBDD = Integer.parseInt(configFile.getProperty("DatabaseMaxRetry"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Abrir Conexion
			openConnection();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// METODO QUE ABRE LA CONEXION CON LA BBDD
	private void openConnection() throws InterruptedException {
		boolean existeConexion = false;
		while (!existeConexion) {
			try {
				if (null == conn)
					conn = DriverManager.getConnection(connectionString, bbddUser, bbddPassword);
				existeConexion = true;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private void test_GetServidores() {

		try {

			System.out.println("GetServidores");
			ArrayList<ParametrosServidor> servidores = jdbc.GetServidores(24292, conn);
			ParametrosServidor ps;
			Iterator<ParametrosServidor> it = servidores.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
			System.out.println("");

			System.out.println("__old_GetServidoresGetServidores");
			servidores = jdbc.__old_GetServidores(24292, conn);
			it = servidores.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\r");

	}

	private void test_GetProveedores() {

		try {

			System.out.println("GetProveedores");
			ArrayList<ParametrosProveedor> Proveedores = jdbc.GetProveedores(24292, conn);
			ParametrosProveedor ps;
			Iterator<ParametrosProveedor> it = Proveedores.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
			System.out.println("");

			System.out.println("__old_GetProveedoresGetProveedores");
			Proveedores = jdbc.__old_GetProveedores(24292, conn);
			it = Proveedores.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\r");

	}

	private void test_GetReceptores() {

		try {

			System.out.println("GetReceptores");
			ArrayList<ParametrosReceptor> Receptores = jdbc.GetReceptores(28261, conn);
			ParametrosReceptor ps;
			Iterator<ParametrosReceptor> it = Receptores.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
			System.out.println("");

			System.out.println("__old_GetReceptoresGetReceptores");
			Receptores = jdbc.__old_GetReceptores(28261, conn);
			it = Receptores.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\r");

	}

	private void test_GetServidorPushes() {

		try {

			System.out.println("GetServidoresPush");
			ArrayList<ParametrosServidorPush> ServidorPushes = jdbc.GetServidoresPush(28638,1, conn);
			ParametrosServidorPush ps;
			Iterator<ParametrosServidorPush> it = ServidorPushes.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
			System.out.println("");

			System.out.println("__old_GetServidoresPush");
			ServidorPushes = jdbc.__old_GetServidoresPush(28638, conn);
			it = ServidorPushes.iterator();
			while (it.hasNext()) {
				ps = it.next();
				System.out.println(ps.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n\r");

	}

	private void test_SetHistorico() {

		try {
			System.out.println("ejecutando test_SetHistorico");
			Historico h = new Historico();
			h.setMensajeid(BigDecimal.valueOf(28409));
			h.setEstadoid(BigDecimal.valueOf(1));			
			h.setServidorid(BigDecimal.valueOf(522));
			h.setSubestadoid(BigDecimal.valueOf(40));
			h.setDescripcion("Historico Test");		
			jdbc.setHistorico(h, conn);
			
			System.out.println("");
			
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\r");

	}
	
	private void test_SetLogError() {

		try {

			System.out.println("SetLogError");
			jdbc.SetLogError("SendMailService.postSMS", 0, "Error: (test_SetLogError)", conn);
			
			System.out.println("");
			
			System.out.println("SetLogError");
			jdbc.__old_SetLogError("SendMailService.postSMS", 0, "Error: (test_SetLogError)", conn);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\r");

	}

	public static void main(String[] args) throws InterruptedException {

		JDBCAccessTest jt = new JDBCAccessTest();
		jt.initializeVariables();
		jt.openConnection();

		// SIM-106 Modificación SendMail y RefreshStatus para módulo de interoperabilidad y sacar lógica BBDD a código
		// Compara los resultados entre los viejos metodos y los nuevos que los sustituyen
		
		// test comprobables por consola
		jt.test_GetServidores();
		jt.test_GetProveedores();
		jt.test_GetReceptores();
		jt.test_GetServidorPushes();

		// test comprobables en bbdd
		jt.test_SetLogError();
			
		// test CRITICOS
		// SetMessageStatus
		
		
		// test para funcionalidad nueva traida desde el proyecto SMS
		jt.test_SetHistorico();
		

	}

}
