package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface VOUsuario {
    public String getTipoUsuario();

    public String getLogin();

    public String getClave();

    public String getTipoId();

    public BigDecimal getId();

    public String getNombre();

    public String getNacionalidad();

    public String getDirFisica();

    public String getDirElectronica();

    public String getTelefono();

    public String getCiudad();

    public String getDepartamento();

    public BigDecimal getCodigoPostal();

    // public LinkedList<Object[]> getOperaciones();

    // public LinkedList<Object[]> getProductos();

    // public LinkedList<Object[]> getAcciones();

}
