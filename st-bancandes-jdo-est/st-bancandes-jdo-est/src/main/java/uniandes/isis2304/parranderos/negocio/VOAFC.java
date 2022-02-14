package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;

public interface VOAFC extends VOProducto{
    public Timestamp getFechaPactada();

    public Long getDineroInicial();

    public BigDecimal getInteres();
}
