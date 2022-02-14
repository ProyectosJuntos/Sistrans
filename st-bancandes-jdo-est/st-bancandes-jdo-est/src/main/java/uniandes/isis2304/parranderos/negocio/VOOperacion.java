package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface VOOperacion {
    public BigDecimal getId();

    public String getTipoOperacion();

    public Long getValor();

    public Timestamp getFecha();

    public BigDecimal getIdPuestoAtencion();

    public BigDecimal getIdEmpleado();

    public String getTipoIdEmpleado();

    public BigDecimal getIdUsuario();

    public String getTipoIdUsuario();

    public BigDecimal getCuenta_origen();

    public BigDecimal getCuenta_destino();
}
