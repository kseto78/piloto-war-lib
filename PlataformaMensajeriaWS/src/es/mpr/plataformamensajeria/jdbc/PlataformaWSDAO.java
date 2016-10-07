package es.mpr.plataformamensajeria.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.jboss.resource.adapter.jdbc.WrappedConnection;

/**
 * @Descripci�n: Clase de utilidad para establecer la conexi�n con la base de datos y manejar las conexiones abiertas
 * @author i-nercya
 * @version 1.0
 *
 */
public class PlataformaWSDAO {
	
	/**
	 * Objeto a trav�s del cual se realizar� la comunicaci�n con la base de datos 
	 */
	private Connection conn = null;
	Context initCtx = null;
	DataSource ds = null;
	public PlataformaWSDAO() /*throws PlataformaDatabaseException*/{
		//initConn("java:dsOraclePlataforma");
	}
	
	
	public void closeAll(){
		if(initCtx != null){
			try {
				initCtx.close();
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ds!=null){
			ds = null;
		}
		if(conn!=null){
	//			conn.close();
				conn = null;
		}
	}
	

	
	/**
	 * Devuelve la conexi�n
	 * @return
	 */
	public Connection getConn() {
		return conn;
	}
	/**
	 * Cierra la conexi�n de forma segura
	 * @return Devuelve verdadero si exist�a una conexi�n abierta y se ha podido cerrar. Devuelve falso si no exist�a una conexi�n abierta.
	 * @exception Devuelve una excepci�n si se produce un error al intentar cerrar una conexi�n abierta
	 */
	public boolean connClose(){
		
		if(conn!=null){
			conn=null;
		}
		return false;
	}
	/**
	 * 
	 * @param sqlObject
	 * @return Devuelve verdadero si el objeto que se pasa como par�metro no es nulo y se ha podido cerrar correctamente. Devuelve falso si el objeto es nulo o no se ha podido cerrar
	 * @exception Lanza una excepci�n si no se ha podido cerrar un objeto
	 */
	public boolean closeSQLObject(Object sqlObject){
		if(sqlObject!=null){
			if(sqlObject instanceof CallableStatement){
				CallableStatement callableStmt = (CallableStatement) sqlObject;
				try {
					callableStmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(sqlObject instanceof PreparedStatement){
				PreparedStatement pstmt = (PreparedStatement) sqlObject;
				try {
					pstmt.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(sqlObject instanceof ResultSet){
				ResultSet rs = (ResultSet) sqlObject;
				try {
					rs.close();
					return true;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return false;
		}else{
			return false;
		}
	}
	public void beginTransaction(){
		try {
			if(initCtx==null){
				initCtx = new InitialContext();
			}
			if(ds==null){
				ds = (DataSource) initCtx.lookup("java:dsOraclePlataforma");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		try {
			if(conn==null){
				conn = ds.getConnection();
				WrappedConnection wc = (WrappedConnection) conn;
				conn = wc.getUnderlyingConnection(); //Transformamos la conexi�n al tipo que indique el driver configurado en el ds
				wc.close();
				conn.setAutoCommit(false); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
		}

	}
	public void endTransaction(boolean commit){
		if(conn!=null){
			try {
				if(commit){
					conn.commit();
				}else{
					conn.rollback();
				}
				//conn.close();
				conn = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); 
			}
		}
	}	
}
