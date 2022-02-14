package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface VOPuestoAtencion {
    public BigDecimal getId();

    public String getTipoPuesto();

    public String getLocalizacion();

    public Oficina getOficina();

    public BigDecimal getIdCajero();

    public String getTipoIdCajero();

    public LinkedList<Object[]> getOperaciones();

}
