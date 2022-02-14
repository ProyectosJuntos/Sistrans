package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;

public class AFC extends Producto implements VOAFC {
    private Timestamp fechaPactada;
    private Long dineroInicial;
    private BigDecimal interes;

    public Timestamp getFechaPactada() {
        return fechaPactada;
    }

    public void setFechaPactada(Timestamp fechaPactada) {
        this.fechaPactada = fechaPactada;
    }

    public Long getDineroInicial() {
        return dineroInicial;
    }

    public void setDineroInicial(Long dineroInicial) {
        this.dineroInicial = dineroInicial;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public AFC(BigDecimal id, Long valorActual, Timestamp fechaCrecion, Timestamp fechaUltimoMov, BigDecimal oficina,
            BigDecimal usuario, String tipoIdUsuario, LinkedList<Object[]> operaciones, String estado,
            Timestamp fechaPactada, Long dineroInicial, BigDecimal interes) {
        super(id, valorActual, fechaCrecion, fechaUltimoMov, oficina, usuario, tipoIdUsuario, operaciones, estado);
        this.fechaPactada = fechaPactada;
        this.dineroInicial = dineroInicial;
        this.interes = interes;
    }

}
