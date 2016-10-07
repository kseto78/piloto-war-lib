package es.minhap.plataformamensajeria.sm.modelo;


public class ParametrosServidor {

	int ServidorId;
	String IP;
	String Usuario;
	String Contrasena;
	String Conexion;
	String Puerto;
	String Autentificacion;
	String TimeOut;
	
	public ParametrosServidor(){}
	
	public ParametrosServidor(int ServidorId){
		
		this.ServidorId = ServidorId;
	}
	
	public void setServidor(int ServidorId){
		this.ServidorId = ServidorId;
	}

	public void setIP(String IP){
		this.IP = IP;
	}

	public void setUsuario(String Usuario){
		this.Usuario = Usuario;
	}

	public void setContrasena(String Contrasena){
		this.Contrasena = Contrasena;
	}
	
	public void setConexion(String Conexion){
		this.Conexion = Conexion;
	}
	
	public void setPuerto(String Puerto){
		this.Puerto = Puerto;
	}
	
	public void setAutentificacion(String Autentificacion){
		this.Autentificacion = Autentificacion;
	}
	
	public void setTimeOut(String TimeOut){
		this.TimeOut = TimeOut;
	}
	
	public int getServidor(){
		return this.ServidorId;
	}
	
	public String getIP(){
		return this.IP;
	}

	public String getUsuario(){
		return this.Usuario;
	}
	
	public String getContrasena(){
		return this.Contrasena;
	}
	
	public String getConexion(){
		return this.Conexion;
	}
	
	public String getPuerto(){
		return this.Puerto;
	}
	
	public String getAutentificacion(){
		return this.Autentificacion;
	}
	
	public String getTimeOut(){
		return this.TimeOut;
	}

	@Override
	public String toString() {
		return "ParametrosServidor [ServidorId=" + ServidorId + ", IP=" + IP + ", Usuario=" + Usuario + ", Contrasena=" + Contrasena + ", Conexion=" + Conexion + ", Puerto=" + Puerto + ", Autentificacion=" + Autentificacion + ", TimeOut=" + TimeOut + "]";
	}
	
}

