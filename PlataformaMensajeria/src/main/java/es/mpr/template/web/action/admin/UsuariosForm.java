/**
 * 
 */
package es.mpr.template.web.action.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class UsuariosForm.
 *
 * @author carlos.ruiz
 */
public class UsuariosForm implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 2132524211;
    
    /**  tipo usuario. */
    private String tipoUsuario;
    
    /**  uid. */
    private String uid;
    
    /**  id. */
    private String id;
    
    /**  username. */
    private String username;
    
    /**  nombre. */
    private String nombre;
    
    /**  apellidos. */
    private String apellidos;
    
    /**  description. */
    private String description;
    
    /**  password. */
    private String password;
    
    /**  check password. */
    private String checkPassword;
    
    /**  organismo. */
    private String organismo = null;
    
    /**  unidad organizacional. */
    private String unidadOrganizacional = null;
    
    /**  eliminado. */
    private Boolean eliminado;
    
    /**  telefono. */
    private String telefono;
    
    /**  telefono 2. */
    private String telefono2;
    
    /**  movil. */
    private String movil;
    
    /**  direccion. */
    private String direccion;
    
    /**  numero. */
    private String numero;
    
    /**  piso. */
    private String piso;
    
    /**  puerta. */
    private String puerta;
    
    /**  escalera. */
    private String escalera;
    
    /**  codigo postal. */
    private String codigoPostal;
    
    /**  ciudad. */
    private String ciudad;
    
    /**  provincia. */
    private String provincia;
    
    /**  pais. */
    private String pais;
    
    /**  nif. */
    private String nif;
    
    /**  cif. */
    private String cif;
    
    /**  email. */
    private String email;
    
    /**  email 2. */
    private String email2;
    
    /**  mail noticias. */
    private String mailNoticias;
    
    /**  mail novedades. */
    private String mailNovedades;
    
    /**  mail contenidos. */
    private String mailContenidos;
    
    /**  mail servicios. */
    private String mailServicios;
    
    /**  mail campanias. */
    private String mailCampanias;
    
    /**  fax. */
    private String fax;
    
    /**  puntos contacto. */
    private String puntosContacto;
    
    /**  despacho. */
    private String despacho;
    
    /**  check box grupos. */
    private ArrayList<String> checkBoxGrupos = new ArrayList<>();
   
    /**  check box grupos seleccionados. */
    private ArrayList<String> checkBoxGruposSeleccionados = new ArrayList<>();
    
    /**  list tipo usuario. */
    private List<String> listTipoUsuario = null;

    /**
     * Obtener tipo usuario.
     *
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Modificar tipo usuario.
     *
     * @param tipo the tipo to set
     */
    public void setTipoUsuario(String tipo) {
        this.tipoUsuario = tipo;
    }

    /**
     * Obtener uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Modificar uid.
     *
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * Obtener username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Modificar username.
     *
     * @param idUsuario new username
     */
    public void setUsername(String idUsuario) {
        this.username = idUsuario;
    }

    /**
     * Obtener nombre.
     *
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modificar nombre.
     *
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtener apellidos.
     *
     * @return the apellidos
     */
    public String getApellidos() {
       
        return apellidos;
    }

    /**
     * Modificar apellidos.
     *
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
      
        this.apellidos = apellidos;
    }

    /**
     * Obtener description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Modificar description.
     *
     * @param descripcion new description
     */
    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    /**
     * Obtener organismo.
     *
     * @return the organismo
     */
    public String getOrganismo() {
        return organismo;
    }

    /**
     * Modificar organismo.
     *
     * @param organismo the organismo to set
     */
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    /**
     * Obtener unidad organizacional.
     *
     * @return the unidadOrganizacional
     */
    public String getUnidadOrganizacional() {
        return unidadOrganizacional;
    }

    /**
     * Modificar unidad organizacional.
     *
     * @param unidadOrganizacional the unidadOrganizacional to set
     */
    public void setUnidadOrganizacional(String unidadOrganizacional) {
        this.unidadOrganizacional = unidadOrganizacional;
    }

    /**
     * Obtener eliminado.
     *
     * @return the eliminado
     */
    public Boolean getEliminado() {
        return eliminado;
    }

    /**
     * Modificar eliminado.
     *
     * @param eliminado the eliminado to set
     */
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * Obtener telefono.
     *
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modificar telefono.
     *
     * @param telefono1 new telefono
     */
    public void setTelefono(String telefono1) {
        this.telefono = telefono1;
    }

    /**
     * Obtener telefono 2.
     *
     * @return the telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * Modificar telefono 2.
     *
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * Obtener movil.
     *
     * @return the movil
     */
    public String getMovil() {
        return movil;
    }

    /**
     * Modificar movil.
     *
     * @param movil the movil to set
     */
    public void setMovil(String movil) {
        this.movil = movil;
    }

    /**
     * Obtener direccion.
     *
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modificar direccion.
     *
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtener numero.
     *
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Modificar numero.
     *
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtener piso.
     *
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * Modificar piso.
     *
     * @param piso the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * Obtener puerta.
     *
     * @return the puerta
     */
    public String getPuerta() {
        return puerta;
    }

    /**
     * Modificar puerta.
     *
     * @param puerta the puerta to set
     */
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    /**
     * Obtener escalera.
     *
     * @return the escalera
     */
    public String getEscalera() {
        return escalera;
    }

    /**
     * Modificar escalera.
     *
     * @param escalera the escalera to set
     */
    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    /**
     * Obtener codigo postal.
     *
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Modificar codigo postal.
     *
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Obtener ciudad.
     *
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Modificar ciudad.
     *
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtener provincia.
     *
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Modificar provincia.
     *
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtener pais.
     *
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * Modificar pais.
     *
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtener nif.
     *
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * Modificar nif.
     *
     * @param nif the nif to set
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Obtener cif.
     *
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * Modificar cif.
     *
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Obtener email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Modificar email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtener email 2.
     *
     * @return the email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * Modificar email 2.
     *
     * @param email2 the email2 to set
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * Obtener mail noticias.
     *
     * @return the mailNoticias
     */
    public String getMailNoticias() {
        return mailNoticias;
    }

    /**
     * Modificar mail noticias.
     *
     * @param mailNoticias the mailNoticias to set
     */
    public void setMailNoticias(String mailNoticias) {
        this.mailNoticias = mailNoticias;
    }

    /**
     * Obtener mail novedades.
     *
     * @return the mailNovedades
     */
    public String getMailNovedades() {
        return mailNovedades;
    }

    /**
     * Modificar mail novedades.
     *
     * @param mailNovedades the mailNovedades to set
     */
    public void setMailNovedades(String mailNovedades) {
        this.mailNovedades = mailNovedades;
    }

    /**
     * Obtener mail contenidos.
     *
     * @return the mailContenidos
     */
    public String getMailContenidos() {
        return mailContenidos;
    }

    /**
     * Modificar mail contenidos.
     *
     * @param mailContenidos the mailContenidos to set
     */
    public void setMailContenidos(String mailContenidos) {
        this.mailContenidos = mailContenidos;
    }

    /**
     * Obtener mail campanias.
     *
     * @return the mailCampanias
     */
    public String getMailCampanias() {
        return mailCampanias;
    }

    /**
     * Modificar mail campanias.
     *
     * @param mailCampanias the mailCampanias to set
     */
    public void setMailCampanias(String mailCampanias) {
        this.mailCampanias = mailCampanias;
    }

    /**
     * Obtener fax.
     *
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * Modificar fax.
     *
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * Obtener puntos contacto.
     *
     * @return the puntosContacto
     */
    public String getPuntosContacto() {
        return puntosContacto;
    }

    /**
     * Modificar puntos contacto.
     *
     * @param puntosContacto the puntosContacto to set
     */
    public void setPuntosContacto(String puntosContacto) {
        this.puntosContacto = puntosContacto;
    }

    /**
     * Obtener despacho.
     *
     * @return the despacho
     */
    public String getDespacho() {
        return despacho;
    }

    /**
     * Modificar despacho.
     *
     * @param despacho the despacho to set
     */
    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    /**
     * Obtener password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Modificar password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtener check password.
     *
     * @return the checkPassword
     */
    public String getCheckPassword() {
        return checkPassword;
    }

    /**
     * Modificar check password.
     *
     * @param checkPassword the checkPassword to set
     */
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    /**
     * Obtener list tipo usuario.
     *
     * @return the listTipoUsuario
     */
    public List<String> getListTipoUsuario() {
        return listTipoUsuario;
    }

    /**
     * Modificar list tipo usuario.
     *
     * @param listTipoUsuario the listTipoUsuario to set
     */
    public void setListTipoUsuario(List<String> listTipoUsuario) {
        this.listTipoUsuario = listTipoUsuario;
    }

    /**
     * Obtener check box grupos.
     *
     * @return the checkBoxGrupos
     */
    public ArrayList<String> getCheckBoxGrupos() {
       
        return checkBoxGrupos;
    }

    /**
     * Modificar check box grupos.
     *
     * @param checkBoxGrupos the checkBoxGrupos to set
     */
    public void setCheckBoxGrupos(ArrayList<String> checkBoxGrupos) {
       
        this.checkBoxGrupos = checkBoxGrupos;
    }

   
    /**
     * Obtener check box grupos seleccionados.
     *
     * @return the checkBoxGruposSeleccionados
     */
    public ArrayList<String> getCheckBoxGruposSeleccionados() {
        return checkBoxGruposSeleccionados;
    }

    /**
     * Modificar check box grupos seleccionados.
     *
     * @param checkBoxGruposSeleccionados the checkBoxGruposSeleccionados to set
     */
    public void setCheckBoxGruposSeleccionados(ArrayList<String> checkBoxGruposSeleccionados) {
        this.checkBoxGruposSeleccionados = checkBoxGruposSeleccionados;
    }

  

    /**
     * Obtener mail servicios.
     *
     * @return the mailServicios
     */
    public String getMailServicios() {
        return mailServicios;
    }

    /**
     * Modificar mail servicios.
     *
     * @param mailServicios the mailServicios to set
     */
    public void setMailServicios(String mailServicios) {
        this.mailServicios = mailServicios;
    }

    /**
     * Obtener id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Modificar id.
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Devuelve el nombre completo (Nombre y Apellidos).
     *
     * @return nombre completo
     */
    public String getNombreCompleto(){
    	StringBuffer sbf = new StringBuffer();
    	if(this.nombre != null){
    		sbf.append(this.nombre).append(" ");
    	}
    	if(this.apellidos != null){
    		sbf.append(this.apellidos);
    	}
    	return sbf.toString();
    }
}
