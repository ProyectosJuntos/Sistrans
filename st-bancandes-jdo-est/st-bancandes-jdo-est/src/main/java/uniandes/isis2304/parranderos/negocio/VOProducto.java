package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

public interface VOProducto {
    public BigDecimal getId();

    public BigDecimal getIdOficina();

    public Long getValorActual();

    public Timestamp getFechaCreacion();

    public Timestamp getFechaUltimoMov();

    public BigDecimal getIdUsuario();

    public String getTipoIdUsuario();

    public LinkedList<Object[]> getOperaciones();

    public String getEstado();
}
