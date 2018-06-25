package es.minhap.plataformamensajeria.iop.services.usuariosplataformas.webpush;

import es.minhap.plataformamensajeria.iop.beans.PeticionRegistroUsuarioWebPush;
import es.minhap.plataformamensajeria.iop.beans.RespuestaRegistroWebPush;

/**
 * This class was generated by Apache CXF 2.6.3
 * Generated source version: 2.6.3
 * 
 */

public interface IRegistroUsuarioWebPushService {

	
	public RespuestaRegistroWebPush registroUsuario(PeticionRegistroUsuarioWebPush peticion);
	
	public boolean eliminarUsuario(String endpoint, String auth, String pdh);
	public boolean eliminarUsuarioServicio(PeticionRegistroUsuarioWebPush peticion);

}
