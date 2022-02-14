package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface VOOficina {
    public BigDecimal getId();

    public String getNombre();

    public String getDireccion();

    public Integer getNumPAtencion();

    public BigDecimal getidGerente();

    public LinkedList<Object[]> getPuestosAtencion();
}
