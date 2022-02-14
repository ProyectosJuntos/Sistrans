package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

public class CDT extends Producto implements VOCDT {
    private Timestamp fechaPactada;

    public Timestamp getFechaPactada() {
        return fechaPactada;
    }

    public void setFechaPactada(Timestamp fechaPactada) {
        this.fechaPactada = fechaPactada;
    }

    public CDT(BigDecimal id, Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov, BigDecimal oficina,
            BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones, String estado,
            Timestamp fechaPactada) {
        super(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones, estado);
        this.fechaPactada = fechaPactada;
    }

}
