package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOAccion {
    public BigDecimal getId();

    public String getCompania();

    public BigDecimal getValorAccion();

    public BigDecimal getCliente();

    public String getTipoIdCliente();

    public BigDecimal getCuentaRegistrada();
}
