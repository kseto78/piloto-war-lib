/**
 * 
 */
package es.mpr.template.web.action.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author carlos.ruiz
 *
 */
public class UsuariosForm implements Serializable {

    private static final long serialVersionUID = 2132524211;
    private String tipoUsuario;
    private String uid;
    private String id;
    private String username;
    private String nombre;
    private String apellidos;
    private String description;
    private String password;
    private String checkPassword;
    private String organismo = null;
    private String unidadOrganizacional = null;
    private Boolean eliminado;
    private String telefono;
    private String telefono2;
    private String movil;
    private String direccion;
    private String numero;
    private String piso;
    private String puerta;
    private String escalera;
    private String codigoPostal;
    private String ciudad;
    private String provincia;
    private String pais;
    private String nif;
    private String cif;
    private String email;
    private String email2;
    private String mailNoticias;
    private String mailNovedades;
    private String mailContenidos;
    private String mailServicios;
    private String mailCampanias;
    private String fax;
    private String puntosContacto;
    private String despacho;
    private ArrayList<String> checkBoxGrupos = new ArrayList<String>();
   
    private ArrayList<String> checkBoxGruposSeleccionados = new ArrayList<String>();
    
    private List<String> listTipoUsuario = null;

    /**
     * @return the tipoUsuario
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipoUsuario(String tipo) {
        this.tipoUsuario = tipo;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String idUsuario) {
        this.username = idUsuario;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellidos
     */
    public String getApellidos() {
       
        return apellidos;
    }

    /**
     * @param apellidos the apellidos to set
     */
    public void setApellidos(String apellidos) {
      
        this.apellidos = apellidos;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String descripcion) {
        this.description = descripcion;
    }

    /**
     * @return the organismo
     */
    public String getOrganismo() {
        return organismo;
    }

    /**
     * @param organismo the organismo to set
     */
    public void setOrganismo(String organismo) {
        this.organismo = organismo;
    }

    /**
     * @return the unidadOrganizacional
     */
    public String getUnidadOrganizacional() {
        return unidadOrganizacional;
    }

    /**
     * @param unidadOrganizacional the unidadOrganizacional to set
     */
    public void setUnidadOrganizacional(String unidadOrganizacional) {
        this.unidadOrganizacional = unidadOrganizacional;
    }

    /**
     * @return the eliminado
     */
    public Boolean getEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono1) {
        this.telefono = telefono1;
    }

    /**
     * @return the telefono2
     */
    public String getTelefono2() {
        return telefono2;
    }

    /**
     * @param telefono2 the telefono2 to set
     */
    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    /**
     * @return the movil
     */
    public String getMovil() {
        return movil;
    }

    /**
     * @param movil the movil to set
     */
    public void setMovil(String movil) {
        this.movil = movil;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the piso
     */
    public String getPiso() {
        return piso;
    }

    /**
     * @param piso the piso to set
     */
    public void setPiso(String piso) {
        this.piso = piso;
    }

    /**
     * @return the puerta
     */
    public String getPuerta() {
        return puerta;
    }

    /**
     * @param puerta the puerta to set
     */
    public void setPuerta(String puerta) {
        this.puerta = puerta;
    }

    /**
     * @return the escalera
     */
    public String getEscalera() {
        return escalera;
    }

    /**
     * @param escalera the escalera to set
     */
    public void setEscalera(String escalera) {
        this.escalera = escalera;
    }

    /**
     * @return the codigoPostal
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * @param codigoPostal the codigoPostal to set
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * @param provincia the provincia to set
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the nif
     */
    public String getNif() {
        return nif;
    }

    /**
     * @param nif the nif to set
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * @return the cif
     */
    public String getCif() {
        return cif;
    }

    /**
     * @param cif the cif to set
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the email2
     */
    public String getEmail2() {
        return email2;
    }

    /**
     * @param email2 the email2 to set
     */
    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    /**
     * @return the mailNoticias
     */
    public String getMailNoticias() {
        return mailNoticias;
    }

    /**
     * @param mailNoticias the mailNoticias to set
     */
    public void setMailNoticias(String mailNoticias) {
        this.mailNoticias = mailNoticias;
    }

    /**
     * @return the mailNovedades
     */
    public String getMailNovedades() {
        return mailNovedades;
    }

    /**
     * @param mailNovedades the mailNovedades to set
     */
    public void setMailNovedades(String mailNovedades) {
        this.mailNovedades = mailNovedades;
    }

    /**
     * @return the mailContenidos
     */
    public String getMailContenidos() {
        return mailContenidos;
    }

    /**
     * @param mailContenidos the mailContenidos to set
     */
    public void setMailContenidos(String mailContenidos) {
        this.mailContenidos = mailContenidos;
    }

    /**
     * @return the mailCampanias
     */
    public String getMailCampanias() {
        return mailCampanias;
    }

    /**
     * @param mailCampanias the mailCampanias to set
     */
    public void setMailCampanias(String mailCampanias) {
        this.mailCampanias = mailCampanias;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the puntosContacto
     */
    public String getPuntosContacto() {
        return puntosContacto;
    }

    /**
     * @param puntosContacto the puntosContacto to set
     */
    public void setPuntosContacto(String puntosContacto) {
        this.puntosContacto = puntosContacto;
    }

    /**
     * @return the despacho
     */
    public String getDespacho() {
        return despacho;
    }

    /**
     * @param despacho the despacho to set
     */
    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the checkPassword
     */
    public String getCheckPassword() {
        return checkPassword;
    }

    /**
     * @param checkPassword the checkPassword to set
     */
    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    /**
     * @return the listTipoUsuario
     */
    public List<String> getListTipoUsuario() {
        return listTipoUsuario;
    }

    /**
     * @param listTipoUsuario the listTipoUsuario to set
     */
    public void setListTipoUsuario(List<String> listTipoUsuario) {
        this.listTipoUsuario = listTipoUsuario;
    }

    /**
     * @return the checkBoxGrupos
     */
    public ArrayList<String> getCheckBoxGrupos() {
       
        return checkBoxGrupos;
    }

    /**
     * @param checkBoxGrupos the checkBoxGrupos to set
     */
    public void setCheckBoxGrupos(ArrayList<String> checkBoxGrupos) {
       
        this.checkBoxGrupos = checkBoxGrupos;
    }

   
    /**
     * @return the checkBoxGruposSeleccionados
     */
    public ArrayList<String> getCheckBoxGruposSeleccionados() {
        return checkBoxGruposSeleccionados;
    }

    /**
     * @param checkBoxGruposSeleccionados the checkBoxGruposSeleccionados to set
     */
    public void setCheckBoxGruposSeleccionados(ArrayList<String> checkBoxGruposSeleccionados) {
        this.checkBoxGruposSeleccionados = checkBoxGruposSeleccionados;
    }

  

    /**
     * @return the mailServicios
     */
    public String getMailServicios() {
        return mailServicios;
    }

    /**
     * @param mailServicios the mailServicios to set
     */
    public void setMailServicios(String mailServicios) {
        this.mailServicios = mailServicios;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Devuelve el nombre completo (Nombre y Apellidos)
     * @return
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
