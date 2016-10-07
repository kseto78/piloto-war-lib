package es.minhap.plataformamensajeria.sm.modelo;


public class ParametrosProveedorMultiorganismo {
	
	int ProveedorId;
	String Id;
	String Telefono;
	String Texto;
	String UIM;
	String Url;
	
	public ParametrosProveedorMultiorganismo(){}
	
	public void setProveedorId(int ProveedorId){
		this.ProveedorId = ProveedorId;
	}
	
	public int getProveedorId(){
		return this.ProveedorId;
	}	
	
	public void setId(String Id){
		this.Id = Id;
	}
	
	public String getId(){
		return this.Id;
	}

	public void setTelefono(String Telefono){
		this.Telefono = Telefono;
	}
	
	public String getTelefono(){
		return this.Telefono;
	}

	public void setTexto(String Texto){
		this.Texto = Texto;
	}
	
	public String getTexto(){
		return this.Texto;
	}

	public void setUIM(String UIM){
		this.UIM = UIM;
	}
	
	public String getUIM(){
		return this.UIM;
	}

	public void setUrl(String Url) {
		this.Url = Url;
	}
	
	public String getUrl(){
		return this.Url;
	}

	@Override
	public String toString() {
		return "ParametrosProveedor [ProveedorId=" + ProveedorId + ", Id=" + Id + ", Telefono=" + Telefono + ", Texto=" + Texto + ", UIM=" + UIM + ", Url=" + Url + "]";
	}
}

