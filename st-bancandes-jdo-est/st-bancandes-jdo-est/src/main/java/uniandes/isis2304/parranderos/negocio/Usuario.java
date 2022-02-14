package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Usuario implements VOUsuario{
    private String tipoId;
    private BigDecimal id;
    private String tipoUsuario;
    private String login;
    private String clave;
    private String nombre;
    private String nacionalidad;
    private String dirFisica;
    private String dirElectronica;
    private String telefono;
    private String ciudad;
    private String departamento;
    private BigDecimal codigoPostal;
    private LinkedList<Object[]> operaciones;
    private LinkedList<Object[]> productos;
    private LinkedList<Object[]> acciones;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getDirFisica() {
        return dirFisica;
    }

    public void setDirFisica(String dirFisica) {
        this.dirFisica = dirFisica;
    }

    public String getDirElectronica() {
        return dirElectronica;
    }

    public void setDirElectronica(String dirElectronica) {
        this.dirElectronica = dirElectronica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(BigDecimal codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public LinkedList<Object[]> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(LinkedList<Object[]> operaciones) {
        this.operaciones = operaciones;
    }

    public LinkedList<Object[]> getProductos() {
        return productos;
    }

    public void setProductos(LinkedList<Object[]> productos) {
        this.productos = productos;
    }

    public LinkedList<Object[]> getAcciones() {
        return acciones;
    }

    public void setAcciones(LinkedList<Object[]> acciones) {
        this.acciones = acciones;
    }


    public Usuario() {
        this.tipoUsuario = "";
        this.login = "";
        this.clave = "";
        this.tipoId = "";
        this.id = null;
        this.nombre = "";
        this.nacionalidad = "";
        this.dirFisica = "";
        this.dirElectronica = "";
        this.telefono = "";
        this.ciudad = "";
        this.departamento = "";
        this.codigoPostal = null;

    }


    public Usuario(String tipoUsuario, String login, String clave, String tipoId, BigDecimal id, String nombre,
            String nacionalidad, String dirFisica, String dirElectronica, String telefono, String ciudad,
            String departamento, BigDecimal codigoPostal) {
        this.tipoUsuario = tipoUsuario;
        this.login = login;
        this.clave = clave;
        this.tipoId = tipoId;
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.dirFisica = dirFisica;
        this.dirElectronica = dirElectronica;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.codigoPostal = codigoPostal;
        this.operaciones = new LinkedList<>();
        this.productos = new LinkedList<>();
        this.acciones = new LinkedList<>();
    }
    @Override
    public String toString() {
        String resul = "";
        resul += "Tipo Usuario: ";
        resul += tipoUsuario;
        resul += "\t Login: ";
        resul += login;
        resul += "\t Clave: ";
        resul += clave;
        resul += "\t Tipo id: ";
        resul += tipoId;
        resul += "\t Id: ";
        resul += id;
        resul += "\t Nombre: ";
        resul += nombre;
        resul += "\t Nacionalidad: ";
        resul += nacionalidad;
        resul += "\t Direccion fisica: ";
        resul += dirFisica;
        resul += "\t Correo: ";
        resul += dirElectronica;
        resul += "\t Telefono: ";
        resul += telefono;
        resul += "\t Ciudad: ";
        resul += ciudad;
        resul += "\t Departamento: ";
        resul += departamento;
        resul += "\t Codigo postal: ";
        resul += codigoPostal;
        return resul;
    }

}
