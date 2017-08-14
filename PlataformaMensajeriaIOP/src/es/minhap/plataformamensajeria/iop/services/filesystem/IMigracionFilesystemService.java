package es.minhap.plataformamensajeria.iop.services.filesystem;

import java.util.Date;


public interface IMigracionFilesystemService {


    /**
     * 
     * @param idMensaje
     * @param fechaInicio
     * @param fechaFin
     * @param historicos
     * @return 
     */
    Boolean modificarMensajes(Long idMensaje, Date fechaInicio, Date fechaFin, Boolean historicos);
    
 
}
