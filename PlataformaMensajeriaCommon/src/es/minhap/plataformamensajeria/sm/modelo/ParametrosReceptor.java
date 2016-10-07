package es.minhap.plataformamensajeria.sm.modelo;


public class ParametrosReceptor {
	
	int ReceptorId;
	String Id;
	String Telefono;
	String Texto;
	String UIM;
	String Url;
	
	public ParametrosReceptor(){}
	
	public void setReceptorId(int ReceptorId){
		this.ReceptorId = ReceptorId;
	}
	
	public int getReceptorId(){
		return this.ReceptorId;
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
		return "ParametrosReceptor [ReceptorId=" + ReceptorId + ", Id=" + Id + ", Telefono=" + Telefono + ", Texto=" + Texto + ", UIM=" + UIM + ", Url=" + Url + "]";
	}
}

