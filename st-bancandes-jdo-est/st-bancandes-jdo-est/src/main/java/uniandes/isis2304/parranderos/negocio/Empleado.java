package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Empleado extends Usuario implements VOEmpleado {
    private String tipoEmpleado;
    private BigDecimal idPuestoAtencion;
    private BigDecimal idOficina;

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public BigDecimal getIdOficina() {
        return idOficina;
    }

    public void setIdOficina(BigDecimal idOficina) {
        this.idOficina = idOficina;
    }

    public BigDecimal getIdPuestoAtencion() {
        return idPuestoAtencion;
    }

    public void setIdPuestoAtencion(BigDecimal idPuestoAtencion) {
        this.idPuestoAtencion = idPuestoAtencion;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public Empleado(String tipoUsuario, String login, String clave, String tipoId, BigDecimal numId, String nombre,
            String nacionalidad, String dirFisica, String dirElectronica, String telefono, String ciudad,
            String departamento, Integer codigoPostal, String tipoEmpleado, BigDecimal idPuestoAtencion, BigDecimal idOficina) {
        super(tipoUsuario, login, clave, tipoId, numId, nombre, nacionalidad, dirFisica, dirElectronica, telefono,
                ciudad, departamento, codigoPostal);
        this.tipoEmpleado = tipoEmpleado;
        this.idPuestoAtencion = idPuestoAtencion;
        this.idOficina = idOficina;
    }

}
