package es.minhap.plataformamensajeria.iop.services.usuariosplataformas;


/**
 * This class was generated by Apache CXF 2.6.3
 * Generated source version: 2.6.3
 * 
 */

public interface IRegistroUsuarioService {

	public RegistroUsuarioResponse registroUsuario(String nombreUsuario, String servicioId, String usuario, String password,
			String plataformaId, String tokenUsuario, String dispositivoId);
}
