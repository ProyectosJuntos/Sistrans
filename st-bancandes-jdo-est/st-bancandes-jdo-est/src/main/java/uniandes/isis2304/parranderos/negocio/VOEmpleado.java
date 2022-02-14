package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOEmpleado extends VOUsuario{
    public String getTipoEmpleado();
    public BigDecimal getIdOficina();
    public BigDecimal getIdPuestoAtencion();
}
