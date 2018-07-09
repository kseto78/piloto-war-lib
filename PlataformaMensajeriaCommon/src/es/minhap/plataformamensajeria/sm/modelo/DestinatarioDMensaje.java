package es.minhap.plataformamensajeria.sm.modelo;

public class DestinatarioDMensaje implements Comparable<String> {
	public String email;
	public Integer idDestinatarioMensaje;

	public DestinatarioDMensaje() {
		this.email = "";
		this.idDestinatarioMensaje = null;
	}

	@Override
	public int compareTo(String o) {
		if (this.email.equals(o))
			return 1;
		else
			return 0;
	}

}
